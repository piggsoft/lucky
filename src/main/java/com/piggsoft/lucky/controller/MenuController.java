package com.piggsoft.lucky.controller;

import com.piggsoft.lucky.dto.MenuDto;
import com.piggsoft.lucky.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <br>Created by fire pigg on 2016/01/20.
 *
 * @author piggsoft@163.com
 */
@RestController
@RequestMapping("/menu")
public class MenuController {


    @Qualifier("menuService")
    @Autowired
    private MenuService menuService;

    @RequestMapping("/getAll")
    public List<MenuDto> getAll() {
        return menuService.getAll();
    }

}
