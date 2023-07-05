package com.mtx.metro.entity;

import java.util.HashMap;

enum Permission {
    NORMAL("1"),GOV("2"),ADMIN("3");

    private String per;

    Permission(String per){
        this.per = per;
    }

    public String getPer(){
        return this.per;
    }

    private static HashMap<String,Permission> perMap = new HashMap<>();
    static {
        for (Permission per : Permission.values()){
            perMap.put(per.getPer(),per);
        }
    }

    public static Permission setValue(String value){
        Permission per = perMap.get(value);
        if(per == null){
            throw new IllegalArgumentException("No element matches " + value);
        }return per;
    }
}
