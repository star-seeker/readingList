package com.manning.readinglist.web;

import com.manning.readinglist.base.RestResult;
import com.manning.readinglist.entity.Reader;
import com.manning.readinglist.exception.ReaderNotFoundException;
import com.manning.readinglist.service.DataService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    private DataService dataService;

    @ApiOperation(value = "获取读者列表", notes = "获取所有读者信息")
    @ApiImplicitParam(name = "id", value = "读者id", required = true, dataType = "String")
    @GetMapping("/reader/{id}")
    public RestResult retrieveReader(@PathVariable String id) {
        return RestResult.success(RestResult.resultMap.get(RestResult.OK), dataService.retrieveReader(id));
    }

    @ApiOperation(value = "删除读者", notes = "删除指定的读者信息")
    @DeleteMapping("/reader")
    public RestResult deleteReader() {
        return RestResult.success(RestResult.resultMap.get(RestResult.OK));
    }
}
