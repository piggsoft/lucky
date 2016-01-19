package com.piggsoft.lucky.controller;

import com.piggsoft.lucky.model.User;
import com.piggsoft.lucky.validate.ParamValidate;
import com.piggsoft.lucky.validate.ParamsValidate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>Created by fire pigg on 2016/01/15.
 *
 * @author piggsoft@163.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/add")
    @ParamsValidate({
            @ParamValidate(name = "username", required = true, regex = "[\\w\\W]{5,50}"),
            @ParamValidate(name = "password", required = true, regex = "[\\w\\W]{5,50}")
    })
    public String add(User user) {

        return null;
    }

}
