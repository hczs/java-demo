package com.hc.java8;

import java.time.*;

/**
 * 新的时间日期API
 * 都在java.time包中
 * @author ：hc
 * @date ：Created in 2021/10/2 14:39
 * @modified By：
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        // LocalDate
        localDate();
        // LocalTime
        localTime();
        // localDateTime
        localDateTime();
    }

    private static void instant() {

    }

    /**
     * LocalDateTime API使用
     * LocalDateTime是LocalDate和LocalTime的结合，拥有年月日时分秒
     */
    private static void localDateTime() {
        // 通过年月日时分秒创建
        LocalDateTime of = LocalDateTime.of(2021, 10, 2, 22, 30, 16);
        // 也可以通过localDate和localTime对象创建
        LocalDate localDate = LocalDate.of(2021, 10, 2);
        LocalTime localTime = LocalTime.of(22, 23, 16);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        // 也可以通过localDate.atTime()
        LocalDateTime atTime = localDate.atTime(localTime);
        // 也可以通过localTime.atDate()
        LocalDateTime atDate = localTime.atDate(localDate);
        // localDateTime也可以取出localDate和localTime
        LocalDate toLocalDate = localDateTime.toLocalDate();
        LocalTime toLocalTime = localDateTime.toLocalTime();
    }

    /**
     * LocalTime API 使用
     * LocalTime和LocalDate类似，LocalTime是针对具体时间来说的，比如时分秒；LocalDate是针对日期的，比如年月日
     */
    private static void localTime() {
        LocalTime localTime = LocalTime.of(22, 23, 16);
        System.out.println("LocalTime：" + localTime);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.println("时分秒：" + hour + ":" + minute + ":" + second);
    }

    /**
     * LocalDate API使用
     * LocalDate类表示一个具体的日期，但不包含具体时间，也不包含时区信息。
     * 可以通过LocalDate的静态方法of()创建一个实例
     * LocalDate也包含一些方法用来获取年份，月份，天，星期几等
     */
    private static void localDate() {
        // 初始化日期 2021-10-02
        LocalDate localDate = LocalDate.of(2021, 10, 2);
        // 也可以通过静态方法now() 来获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println("当前日期：" + now);
        // 获取年份
        int year = localDate.getYear();
        // 获取月份枚举
        Month month = localDate.getMonth();
        // 获取当前时间是当前月中的第几天
        int dayOfMonth = localDate.getDayOfMonth();
        // 获取当前日期是本周第几天 也就是周几 返回的是一个week的枚举
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        // 获取当前月一共有几天
        int lengthOfMonth = localDate.lengthOfMonth();
        // 是否为闰年
        boolean leapYear = localDate.isLeapYear();
        System.out.println("当前年份：" + year + " 当前月份：" + month + " 当前天是当前月的第几天：" + dayOfMonth
        + " 当前天是本周第几天：" + dayOfWeek + " 当前月共多少天：" + lengthOfMonth + " 是否为闰年：" + leapYear);
    }
}
