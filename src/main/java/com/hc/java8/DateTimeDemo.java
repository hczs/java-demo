package com.hc.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * 新的时间日期API
 * 都在java.time包中
 * @author ：hc
 * @date ：Created in 2021/10/2 14:39
 * @modified By：
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        // Java8日期时间类相关API说明，涉及到localDate、localTime、localDateTime、instant、duration、period
        // 日期
        localDate();
        // 时间
        localTime();
        // 日期+时间
        localDateTime();
        // 时间戳
        instant();
        // 时间段（时间范围）
        duration();
        period();

        // 日期的操作和格式化
        dateOperation();
        dateFormat();
    }

    /**
     * 格式化日期
     * 新的日期API中提供了一个DateTimeFormatter类用于处理日期格式化操作
     * 它被包含在java.time.format包中，Java 8的日期类有一个format()方法用于将日期格式化为字符串
     * 该方法接收一个DateTimeFormatter类型参数
     */
    private static void dateFormat() {
        // 2021-10-04T22:34:19.059
        LocalDateTime now = LocalDateTime.now();
        // 20211004
        String basicIsoDate = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        // 2021-10-04
        String isoLocalDate = now.format(DateTimeFormatter.ISO_LOCAL_DATE);
        // 22:34:19.059
        String isoLocalTime = now.format(DateTimeFormatter.ISO_LOCAL_TIME);
        // 自定义
        String customizeDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String customizeDateTime = now.format(DateTimeFormatter.ofPattern("今天是：yyyy年 MMMM dd日 E", Locale.CHINESE));
        System.out.println("now：" + now);
        System.out.println("basicIsoDate：" + basicIsoDate);
        System.out.println("isoLocalDate：" + isoLocalDate);
        System.out.println("isoLocalTime：" + isoLocalTime);
        System.out.println("customizeDate：" + customizeDate);
        System.out.println("customizeDateTime：" + customizeDateTime);
    }

    /**
     * 日期操作
     */
    private static void dateOperation() {
        // 简单操作 - 直接改日期
        LocalDate localDate = LocalDate.of(2021, 10, 4);
        // 将日期中的年改为2022年
        LocalDate withYear = localDate.withYear(2022);
        // 将月份改为12月
        LocalDate withMonth = localDate.withMonth(12);
        // 修改天
        LocalDate withDayOfMonth = localDate.withDayOfMonth(2);
        // 改为2022年12月02日
        LocalDate date = localDate.withYear(2022).withMonth(12).withDayOfMonth(2);
        System.out.println("withYear：" + withYear);
        System.out.println("withMonth：" + withMonth);
        System.out.println("withDayOfMonth：" + withDayOfMonth);
        System.out.println("date：" + date);

        // 简单操作 - 日期加减
        // 加两年
        LocalDate plusYears = localDate.plusYears(2);
        // 减去俩月
        LocalDate minusMonths = localDate.minusMonths(2);
        // 加五天
        LocalDate plus = localDate.plus(2, ChronoUnit.DAYS);
        System.out.println("plusYears：" + plusYears);
        System.out.println("minusMonths：" + minusMonths);
        System.out.println("plus：" + plus);

        // 更灵活的操作，使用with方法+TemporalAdjuster，TemporalAdjusters类中包含了很多静态方法可以直接使用
        // 返回下一个距离当前日期最近的星期日
        LocalDate with1 = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        // 返回本月最后一个星期六
        LocalDate with2 = localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY));
        System.out.println("with1：" + with1);
        System.out.println("with2：" + with2);

        // 自定义操作日期 - 给定一个日期，计算该日期的下一个工作日（就是跳过星期六和星期天）
        LocalDate testDate = LocalDate.of(2021, 10, 4);
        LocalDate with3 = testDate.with(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

            // 正常情况，每次增加一天
            int dayToAdd = 1;

            // 如果是星期五，加三天
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }

            // 如果是周六，加两天
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }

            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        System.out.println("with3：" + with3);
    }

    /**
     * Period在概念上和Duration类似，区别在于Period是以年月日来衡量一个时间段
     */
    private static void period() {
        // 两年六个月三天
        Period period = Period.of(2, 6, 3);
        // 形容一段时间
        Period between = Period.between(LocalDate.of(2021, 10, 4), LocalDate.of(2023, 10, 4));
    }

    /**
     * Duration的内部实现与Instant类似，也是包含两部分：seconds表示秒，nanos表示纳秒。
     * 两者的区别是Instant用于表示一个时间戳（或者说是一个时间点），而Duration表示一个时间段，
     * 所以Duration类中不包含now()静态方法。可以通过Duration.between()方法创建Duration对象
     */
    private static void duration() {
        // 当前时间
        LocalDateTime from = LocalDateTime.of(2021, 10, 4, 19, 41, 16);
        // 火车发车时间
        LocalDateTime to = LocalDateTime.of(2021, 10, 7, 11, 58, 00);
        // 看看距离火车发车还有多长时间2333333
        Duration duration = Duration.between(from, to);
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds();
        long millis = duration.toMillis();
        long nanos = duration.toNanos();
        System.out.println("距离发车还有：" + days + "天");
        System.out.println("距离发车还有：" + hours + "小时");
        System.out.println("距离发车还有：" + minutes + "分钟");
        System.out.println("距离发车还有：" + seconds + "秒");
        System.out.println("距离发车还有：" + millis + "毫秒");
        System.out.println("距离发车还有：" + nanos + "纳秒");

        // 其他创建duration对象的方法，第一个参数是时长，第二个参数是时间单位
        Duration of = Duration.of(5, ChronoUnit.DAYS);
        System.out.println(of.toDays());
    }

    /**
     * Instant用于表示一个时间戳，它与我们常使用的System.currentTimeMillis()有些类似
     * 不过Instant可以精确到纳秒（Nano-Second），System.currentTimeMillis()方法只精确到毫秒（Milli-Second）
     * 如果查看Instant源码，发现它的内部使用了两个常量，seconds表示从1970-01-01 00:00:00开始到现在的秒数，nanos表示纳秒部分（nanos的值不会超过999,999,999）
     * Instant除了使用now()方法创建外，还可以通过ofEpochSecond方法创建：
     */
    private static void instant() {
        // 第一个参数是秒，第二个参数是纳秒
        // 下面代码表示从1970-01-01 00:00:00开始后两分钟的10万纳秒的时刻
        Instant instant = Instant.ofEpochSecond(120, 100000);
        System.out.println(instant);

        Instant now = Instant.now();
        System.out.println(now);
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
