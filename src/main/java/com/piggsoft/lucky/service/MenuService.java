package com.piggsoft.lucky.service;

import com.piggsoft.lucky.dao.MenuMapper;
import com.piggsoft.lucky.dto.MenuDto;
import com.piggsoft.lucky.model.Menu;
import com.piggsoft.lucky.model.MenuExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>Created by fire pigg on 2016/01/20.
 *
 * @author piggsoft@163.com
 */
@Service
public class MenuService {


    @Autowired
    private MenuMapper menuMapper;

    public List<MenuDto> getAll() {
        List<MenuDto> menuDtos = new ArrayList<MenuDto>();
        List<Menu> topMenus = getTop();
        for (Menu menu : topMenus) {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu, menuDto);
            menuDto.setMenus(getChild(menuDto.getMenuid()));
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }

    public List<Menu> getTop() {
        MenuExample example = new MenuExample();
        example.createCriteria()
                .andPidIsNull();
        return menuMapper.selectByExample(example);
    }

    public List<Menu> getChild(int pid) {
        MenuExample example = new MenuExample();
        example.createCriteria()
                .andPidEqualTo(pid);
        return menuMapper.selectByExample(example);
    }

}
