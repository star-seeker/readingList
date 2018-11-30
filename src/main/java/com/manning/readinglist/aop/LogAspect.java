package com.manning.readinglist.aop;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * request log record
 */

@Component
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    @Pointcut(value = "execution(* com.manning.readinglist.web..*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        timeThreadLocal.set(System.currentTimeMillis());
        // 接收请求，获取请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        logger.info("request begin, method: {}", method.getDeclaringClass() + "." + methodName + "()");
        // 获取请求参数
        String parameterJson = getParameterJson(request);
        logger.info("request url: {}", request.getRequestURL().toString());
        logger.info("request method: {}", request.getMethod());
        logger.info("request uri: {}", request.getRequestURI());
        logger.info("request parameters: {}", parameterJson);
    }

    @After("log()")
    public void after() {
        logger.info("request end.");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public Object afterReturning(Object result) {
        logger.info("return result: {}", result.toString());
        long startTime = timeThreadLocal.get();
        long callTime = System.currentTimeMillis() - startTime;
        logger.info("cost time: {}ms", callTime);
        return result;
    }

    public Map<String, Object> getParameterMap(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, Object> parameterMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            parameterMap.put(key, value);
        }
        return parameterMap;
    }

    @Contract("null -> null")
    private String getParameterJson(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Enumeration<String> parameterNames = request.getParameterNames();

        JsonArray jsonArray = new JsonArray();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(key, value);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
