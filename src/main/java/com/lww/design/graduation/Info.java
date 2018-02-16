package com.lww.design.graduation;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.codec.Base64;

import java.util.Date;

/**
 * @author lww
 * @date 2017/12/12
 */
public class Info {
    public static void main(String[] args) {
        System.out.println("Mac Test");
        System.out.println("lww graduation design");
        System.out.println(new Date().getTime());
        byte[] s = Base64.decode("5AvVhmFLUs0KTA3Kprsdag==");

        String str = new String(Base64.encode(s));


        System.out.printf(str);
    }
}
