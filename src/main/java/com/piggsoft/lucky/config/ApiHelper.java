package com.piggsoft.lucky.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <br>Created by fire pigg on 2016/01/19.
 *
 * @author piggsoft@163.com
 */
@Component
public class ApiHelper implements ApplicationContextAware {

    Map<String, ApiInvoker> cache = new LinkedHashMap<String, ApiInvoker>();
    private ApplicationContext applicationContext;

    void add(String api, Class<?> clazz, Method method) {
        cache.put(api, new ApiInvoker(clazz, method));
    }

    public Object invoke(String api, Object... args) {
        return cache.get(api).invoke(applicationContext, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
