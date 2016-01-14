package com.piggsoft.lucky.response;

import java.util.HashMap;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
public class SimpleResponse extends HashMap<String, Object> {

    public static SimpleResponse create() {
        return new SimpleResponse();
    }

    public SimpleResponse add(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
