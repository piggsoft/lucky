package com.piggsoft.lucky.controller;

import com.piggsoft.lucky.api_config.ApiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>Created by fire pigg on 2016/01/19.
 *
 * @author piggsoft@163.com
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiHelper apiHelper;

    @RequestMapping
    public Object api(String api, String json) {
        return apiHelper.invoke(api, json);
    }

}
