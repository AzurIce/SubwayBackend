package com.mtx.metro.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.util.HashMap;

public enum PerEnum implements IEnum<String> {
    NORMAL_USER("1","NORMAL_USER"),
    COMPANY_USER("2","COMPANY_USER"),
    ADMINISTRATOR("3","ADMINISTRATOR");

    private String per;
    private String desc;

    PerEnum(String per, String desc){
        this.per = per;
        this.desc = desc;
    }

    @Override
    public String toString(){
        return desc;
    }

    @Override
    public String getValue() {
        return per;
    }
}
