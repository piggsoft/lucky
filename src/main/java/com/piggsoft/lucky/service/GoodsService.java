package com.piggsoft.lucky.service;

import com.piggsoft.lucky.dao.GoodsMapper;
import com.piggsoft.lucky.model.Goods;
import com.piggsoft.lucky.model.GoodsExample;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <br>Created by fire pigg on 2016/02/01.
 *
 * @author piggsoft@163.com
 */
@Service
public class GoodsService {

    private String sort = "create_tm DESC";

    @Autowired
    private GoodsMapper goodsMapper;


    public List<Goods> findToday() {
        return findDay(new Date());
    }

    public List<Goods> findDay(Date day) {
        GoodsExample example = new GoodsExample();
        DateTime time = new DateTime(day);
        example.createCriteria()
                .andCreateTmBetween(
                        time.withTimeAtStartOfDay().toDate(),
                        time.plusDays(1).withTimeAtStartOfDay().toDate());
        example.setOrderByClause(sort);
        return goodsMapper.selectByExample(example);
    }

    public int save(Goods goods) {
        return goodsMapper.insert(goods);
    }

    public int update(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }


}
