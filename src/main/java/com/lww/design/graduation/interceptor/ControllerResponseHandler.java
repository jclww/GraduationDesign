package com.lww.design.graduation.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControllerResponseHandler implements ResponseBodyAdvice {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ModelAndView exception(Exception exception) {
        ModelAndView mav = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", 400);
        attributes.put("msg", exception.getMessage());
        log.info("exception:{}", exception);
//
//        view.setAttributesMap(attributes);
//        mav.setView(view);
        return new ModelAndView("error", attributes);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        JSONObject json = JSON.parseObject(JSON.toJSONString(body));
//        json.put("msg", message);
//        json.remove("message");
//        body = json;
//        if (BaseResult.class.isAssignableFrom(returnType.getParameterType())) {
//            BaseResult result = (BaseResult) body;
//            String message = result.getMessage();
//            JSONObject json = JSON.parseObject(JSON.toJSONString(result));
//            json.put("msg", message);
//            json.remove("message");
//            body = json;
//        }
        return body;
    }
}
