package com.piggsoft.lucky.service;

import com.piggsoft.lucky.dao.KindMapper;
import com.piggsoft.lucky.model.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>Created by fire pigg on 2016/02/01.
 *
 * @author piggsoft@163.com
 */
@Service
public class KindService {


    @Autowired
    private KindMapper kindMapper;


    public int save(Kind kind) {
        return kindMapper.insert(kind);
    }

    public int update(Kind kind) {
        return kindMapper.updateByPrimaryKeySelective(kind);
    }

    public Kind findById(int id) {
        return kindMapper.selectByPrimaryKey(id);
    }

    public List<Kind> findAll() {
        return kindMapper.selectByExample(null);
    }

}
