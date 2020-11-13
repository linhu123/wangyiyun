package com.linhu.chapter2.bingfarongqi;

import java.util.HashMap;

import static java.util.Objects.hash;

public class Test {

    public static void main(String[] args) {
        HashMap<String,Object> map = new HashMap<>(20);
        //存数据的东西，----k--v
        map.put("key","value");
        System.out.println("key's hashcode is "+"key".hashCode());
        System.out.println("key's hashcode is "+hash("key"));

    }

}
