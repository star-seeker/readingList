package com.manning.readinglist.web;

import com.manning.readinglist.config.AmazonProperties;
import com.manning.readinglist.entity.Book;
import com.manning.readinglist.repository.ReadingListRepository;
import com.manning.readinglist.service.ReadingListService;
import com.manning.readinglist.utils.ToolCaptchaImage;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/reader")
public class ReadingListController {

    @Resource
    private AmazonProperties amazonProperties;

    @Resource
    private ReadingListRepository readingListRepository;

    @Resource
    private ReadingListService readingListService;

    @Resource
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);

        CompletableFuture.supplyAsync(() -> Integer.parseInt("ILLEGAL"))
                .thenApply(r -> r * 2 * Math.PI)
                .thenApply(s -> "apply>>" + s)
                .exceptionally(ex -> "Error:" + ex.getMessage());

    }

    @ApiIgnore
    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return "readingList";
    }

    @ApiIgnore
    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        DigestUtils.sha256Hex("test");
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

    @ApiIgnore
    @GetMapping("/Image")
    public void getImage(HttpServletRequest request, HttpServletResponse response, String flag) {
        ByteArrayOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            // 生成验证码
            String captchaCode = ToolCaptchaImage.generateTextCode(ToolCaptchaImage.TYPE_ALL_MIXED, 4, null);
            // 生成验证码图片
            BufferedImage img = ToolCaptchaImage.generateImageCode(captchaCode, 100, 34, 3, true, Color.WHITE, Color.BLACK, Color.GRAY,
                    ToolCaptchaImage.FONT_TYPE_TIMES_NEW_ROMAN);
            // 验证码缓存：5分钟
            redisTemplate.opsForValue().set(MessageFormat.format("CAPTCHA:{0}", flag), captchaCode, 300L);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "JPEG", outputStream);

            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment; filename=captcha.jpg");
            // 文件流返回
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(outputStream.toByteArray());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
