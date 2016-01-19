package com.piggsoft.lucky.config;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * <br>Created by fire pigg on 2016/01/19.
 *
 * @author piggsoft@163.com
 */
@Inherited
@Retention(RUNTIME)
@Target({METHOD})
@Documented
public @interface Api {
    String value();
}
