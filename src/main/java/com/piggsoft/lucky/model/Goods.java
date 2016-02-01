package com.piggsoft.lucky.model;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer id;

    private Integer name;

    private BigDecimal moneyIn;

    private BigDecimal moneyOut;

    private Date createTm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public BigDecimal getMoneyIn() {
        return moneyIn;
    }

    public void setMoneyIn(BigDecimal moneyIn) {
        this.moneyIn = moneyIn;
    }

    public BigDecimal getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(BigDecimal moneyOut) {
        this.moneyOut = moneyOut;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }
}