package com.manning.readinglist.web;

import com.manning.readinglist.config.AmazonProperties;
import com.manning.readinglist.entity.Book;
import com.manning.readinglist.entity.Reader;
import com.manning.readinglist.exception.ReaderNotFoundException;
import com.manning.readinglist.repository.ReadingListRepository;
import com.manning.readinglist.service.ReadingListService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.apache.commons.lang3.StringUtils.join;

@Controller
@RequestMapping("/")
public class ReadingListController {

    @Resource
    private AmazonProperties amazonProperties;

    @Resource
    private ReadingListRepository readingListRepository;

    @Resource
    private ReadingListService readingListService;

    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);

        CompletableFuture.supplyAsync(() -> Integer.parseInt("ILLEGAL"))
                .thenApply(r -> r * 2 * Math.PI)
                .thenApply(s ->"apply>>" + s)
                .exceptionally(ex -> "Error:" + ex.getMessage());

    }

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

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        DigestUtils.sha256Hex("test");
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

}
