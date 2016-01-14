package com.piggsoft.lucky.exception;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
public class PiggException extends RuntimeException {

    private String errorMsg;

    public PiggException() {}

    public PiggException(String s, String errorMsg) {
        super(s);
        this.errorMsg = errorMsg;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
