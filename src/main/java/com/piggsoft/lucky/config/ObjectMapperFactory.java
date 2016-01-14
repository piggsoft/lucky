package com.piggsoft.lucky.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.FactoryBean;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
public class ObjectMapperFactory implements FactoryBean<ObjectMapper>{


    public ObjectMapper getObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
