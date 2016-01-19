package com.piggsoft.lucky.config;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * <br>Created by fire pigg on 2016/01/19.
 *
 * @author piggsoft@163.com
 */
public class ApiInvoker {

    public Class<?> clazz;
    public Method method;

    public ApiInvoker(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public Object invoke(ApplicationContext applicationContext, Object... args) {
        Object target = applicationContext.getBean(clazz);
        return ReflectionUtils.invokeMethod(this.method, target, args);
    }

}
