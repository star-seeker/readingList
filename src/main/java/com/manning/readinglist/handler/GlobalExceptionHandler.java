package com.manning.readinglist.handler;

import com.google.gson.Gson;
import com.manning.readinglist.base.RestResult;
import com.manning.readinglist.exception.ReaderNotFoundException;
import javassist.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Exception Handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ReaderNotFoundException.class)
    public RestResult readerNotFoundExceptionHandler(HttpServletRequest request, ReaderNotFoundException
            e) {
        return handleErrorInfo(RestResult.READER_NOT_FOUND, RestResult.resultMap.get(RestResult.READER_NOT_FOUND),
                request, e);
    }

    @ExceptionHandler(NullPointerException.class)
    public RestResult nullPointerExceptionHandler(HttpServletRequest request, NullPointerException e) {
        return handleErrorInfo(RestResult.NULL_POINTER, RestResult.resultMap.get(RestResult.NULL_POINTER), request, e);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public RestResult classNotFountExceptionHandler(HttpServletRequest request, ClassNotFoundException e) {
        return handleErrorInfo(RestResult.NOT_FOUND, RestResult.resultMap.get(RestResult.NOT_FOUND), request, e);
    }

    @ExceptionHandler(Exception.class)
    public RestResult defaultExceptionHandler(HttpServletRequest request, Exception e) {
        return handleErrorInfo(RestResult.ERROR, RestResult.resultMap.get(RestResult.ERROR), request, e);
    }

    @NotNull
    private RestResult handleErrorInfo(Integer code, String message, HttpServletRequest request, Exception e) {
        logger.error(message, e);
        return RestResult.error(code, message, request.getRequestURL().toString(), e.getMessage());
    }

}
