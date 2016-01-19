package com.piggsoft.lucky.api;

import com.piggsoft.lucky.config.Api;

/**
 * <br>Created by fire pigg on 2016/01/19.
 *
 * @author piggsoft@163.com
 */
public class Demo {

    @Api("haha")
    public String helloworld(String json) {
        return "hello world";
    }
}
