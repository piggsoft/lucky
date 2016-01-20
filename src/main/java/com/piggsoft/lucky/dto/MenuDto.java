package com.piggsoft.lucky.dto;

import com.piggsoft.lucky.model.Menu;

import java.util.List;

/**
 * <br>Created by fire pigg on 2016/01/20.
 *
 * @author piggsoft@163.com
 */
public class MenuDto extends Menu {
    private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
