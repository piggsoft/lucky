package com.piggsoft.lucky.config;

import com.piggsoft.lucky.exception.PiggException;
import com.piggsoft.lucky.response.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
@ControllerAdvice
public class Configuration extends AbstractJsonpResponseBodyAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    public Configuration() {
        super("callback");
    }

    @ExceptionHandler(PiggException.class)
    @ResponseBody
    public SimpleResponse handlePiggException(HttpServletRequest request, PiggException ex){
        return SimpleResponse.create().add("code", "500").add("errorMsg", ex.getErrorMsg());
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        LOGGER.error("IOException handler executed");
    }

}
