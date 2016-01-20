package com.piggsoft.lucky.api_config;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * <br>按照约定，注解有@Api的方法的参数
 * <br>必须为 (String api, String json)
 * <br>
 * <br>Created by fire pigg on 2016/01/19.
 * @author piggsoft@163.com
 */
@Inherited
@Retention(RUNTIME)
@Target({METHOD})
@Documented
public @interface Api {
    String value();
}
