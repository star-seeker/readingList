package com.manning.readinglist.web;

import com.manning.readinglist.base.RestResult;
import com.manning.readinglist.entity.Reader;
import com.manning.readinglist.exception.ReaderNotFoundException;
import com.manning.readinglist.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    private DataService dataService;

    @GetMapping("/reader")
    public RestResult retrieveReader() {
        return RestResult.success(RestResult.resultMap.get(RestResult.OK), dataService.retrieveReader());
    }

    @DeleteMapping("/reader")
    public RestResult deleteReader() {
        return RestResult.success(RestResult.resultMap.get(RestResult.OK));
    }
}
