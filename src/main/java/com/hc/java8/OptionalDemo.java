package com.hc.java8;

import java.util.HashMap;
import java.util.Optional;

/**
 * 干掉空指针！ - Optional
 * @author ：hc
 * @date ：Created in 2021/10/17 19:47
 * @modified ：
 */
public class OptionalDemo {

    public static void main(String[] args) {
        // 测试数据创建
        HashMap<String, String> map = new HashMap<>(10);
        map.put("name","hc");

        // optional 创建
        // of不会接收空指，如果为空，依然会报空指针
        Optional<String> age = Optional.of(map.get("age"));
        // ofNullable 可以接收空的
        Optional<String> nullableAge = Optional.ofNullable(map.get("age"));

        // optional 简单操作，少用，知道有这个东西就好
        // isPresent要少用，这个是检测optional里的值是否为空，为空就返回false，不为空返回true
        // 但是如果用了isPresent方法，还不如直接 value != null来判断呢，所以isPresent少用
        boolean isPresent = nullableAge.isPresent();
        // get() 这个方法也要少用，作用就是取出optional里面的值，有值就返回，没值就报错
        if (isPresent) {
            String get = nullableAge.get();
        }

        // optional 进阶操作，也是常用方法
        // 常用方法，ifPresent，里面是一个consumer接口，也就是 T -> void
        Optional<String> nameOptional = Optional.ofNullable(map.get("name"));
        nameOptional.ifPresent(name -> System.out.println("get a name: " + name));
        // orElse 有值就返回，没有值就给个默认值
        String myName = nameOptional.orElse("名字是空的！");
        System.out.println(myName);
        // orElseGet 有值就返回，没值就返回方法里的东西，里面是个Supplier接口，也就是 ()->T
        String orElseGet = nameOptional.orElseGet(OptionalDemo::getName);
        // orElseThrow 有值就返回，没值就抛出指定异常，如果指定异常也为null，就报空指针异常
        String orElseThrow = nameOptional.orElseThrow(() -> new RuntimeException("出异常了！"));
    }


    public static String getName() {
        return "hc";
    }

}
