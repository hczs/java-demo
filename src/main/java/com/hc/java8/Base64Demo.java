package com.hc.java8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Java8提供Base64加密解密，再也不用使用第三方包了！
 * @author ：hc
 * @date ：Created in 2021/9/26 21:09
 * @modified By：
 */
public class Base64Demo {
    public static void main(String[] args) {
        String text = "hello world!";
        String encode = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println("encode: " + encode);
        String decode = new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
        System.out.println("decode: " + decode);
    }
}
