package com.mrjeff.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Cesar on 28/02/2019.
 */
@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String code;
    private Integer amount;
    
    public Voucher() {
    	
    }

    public Voucher(String code, Integer amount) {
        this.code = code;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
