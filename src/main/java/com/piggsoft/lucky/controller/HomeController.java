package com.piggsoft.lucky.controller;

import com.piggsoft.lucky.validate.ParamValidate;
import com.piggsoft.lucky.validate.ParamsValidate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @RequestMapping
    @ParamsValidate({
            @ParamValidate(name = "code", required = true)
    })
    public String api() {
        return "aa";
    }

}
