package com.piggsoft.lucky.service;

import com.piggsoft.lucky.dao.UserMapper;
import com.piggsoft.lucky.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createUser(User user) {
        userMapper.insertSelective(user);
    }


}
