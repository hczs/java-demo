package com.hc.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Stream API 使用
 * @author ：hc
 * @date ：Created in 2021/9/27 20:01
 * @modified By：
 */
public class StreamDemo {

    public static void main(String[] args) {
        // 中间操作
        // 常用 filter、map、limit、skip、sorted、distinct
        intermediateOperation();
        // flatMap使用
        flatMapOperation();

        // 终端操作
        // 常用 allMatch、anyMatch、noneMatch、findAny、findFirst、count
        endOperation();
        // reduce 归约
        reduceOperation();
    }

    /**
     * 中间操作
     * 操作stream流，返回的还是stream
     */
    private static void intermediateOperation() {
        // 常用 filter、map、limit、skip、sorted、distinct
        // filter 过滤 把符合括号中条件的值筛选出来
        System.out.println("==================filter==================");
        List<String> strings = Arrays.asList("H", "", "C", "Z", "S");
        strings.stream().filter(e -> !e.isEmpty()).forEach(System.out::println);
        // map 映射 把每一个值都按照括号中的操作一遍，然后操作完的值就是映射之后的值
        System.out.println("==================map==================");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().map(e -> e * e).forEach(System.out::println);
        // limit 截取 保留前n个元素，skip 跳过 扔掉前n个元素
        System.out.println("==================limit==================");
        numbers.stream().limit(3).forEach(System.out::println);
        System.out.println("==================skip==================");
        numbers.stream().skip(3).forEach(System.out::println);
        // sorted 对流进行排序 默认自然序升序，想降序的话使用 sorted(Comparator.reverseOrder())
        System.out.println("==================sorted==================");
        numbers.stream().sorted().forEach(System.out::println);
        // 显而易见，去重
        System.out.println("==================distinct==================");
        numbers.stream().distinct().forEach(System.out::println);
    }

    /**
     * flatMap，当你想要让一个值转换为另一个值的时候可以用map，但是当想要用一个值获取多个值，然后还想
     * 把这多个值都装到一个list里面，就该考虑用flatMap了，展平map！
     */
    private static void flatMapOperation(){
        // 场景：列出list中各不相同的单词
        List<String> list = new ArrayList<String>();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");
        // 先切分句子得出每个单词，切分肯定会得到若干个String[]，怎么把这么多的String[]整合到一个Stream<String>里面呢？
        // 使用flatMap，展平为Stream<String>
        // 然后去重，再合并为一个list
        List<String> collect = list.stream().map(line -> line.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * 终端操作，操作完会有一个返回值，但是不是stream对象了
     * 常用：allMatch、anyMatch、noneMatch、findAny、findFirst
     */
    private static void endOperation() {
        List<Integer> numbers = Arrays.asList(2, 3, 2, 3, 7, 3, 5);
        // allMatch 是否匹配所有元素，判断流中所有元素是否都符合指定条件
        // 流中元素是否都小于10 true
        System.out.println(numbers.stream().allMatch(num -> num < 10));
        // anyMatch 用于判断流中是否存在至少一个元素满足指定的条件
        // 流中元素至少有一个是等于5的 true
        System.out.println(numbers.stream().anyMatch(num -> num == 5));
        // noneMatch 与allMatch相反，判断流中所有元素是否都不符合指定条件
        // 流中元素都不小于10 false
        System.out.println(numbers.stream().noneMatch(num -> num < 10));
        // findAny 从流中随便选一个元素出来，返回的是Optional类型的元素
        Optional<Integer> any = numbers.stream().findAny();
        System.out.println(any);
        // findFirst 取流中第一个元素 返回的是Optional类型的元素
        System.out.println(numbers.stream().findFirst());
        // count 返回流中元素的个数
        // 以下是去重之后，流中元素的个数
        System.out.println(numbers.stream().distinct().count());
    }

    /**
     * 归约：指将集合中的元素经过指定运算，折叠成一个元素输出，如：求平均值、最值等等
     * 在流中，reduce能够实现归约
     * reduce接收两个参数：
     * 1.初始值
     * 2.进行归约操作的lambda表达式
     * 数值流相关
     */
    private static void reduceOperation() {
        List<Integer> numbers = Arrays.asList(2, 3, 2, 3, 7, 3, 5);
        // 第一个参数的含义：表示reduce计算的初始值是0
        // 第二个参数的含义：是一个两个参数的lambda表达式，表示要进行的归约操作
        // reduce会把流中元素两两输给lambda表达式，最后将计算出累加之和
        Integer reduce = numbers.stream().reduce(0, (num1, num2) -> num1 + num2);
        // 上面的还可以这样写，使用Integer自带的sum方法，这么着得话，Integer的min，max方法也都可以用
        Integer reduce1 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        // 求numbers中的最大值，这里不加第一个参数了，直接进行函数里的计算
        Optional<Integer> reduce2 = numbers.stream().reduce(Integer::min);
        System.out.println(reduce2);
        // 数值流，Stream API提供了三种数值流：IntStream、DoubleStream、LongStream
        // 也提供了将普通流转换成数值流的三种方法：mapToInt、mapToDouble、mapToLong
        // 采用reduce进行数值操作会涉及到基本数值类型和引用数值类型之间的装箱、拆箱操作，因此效率较低。
        // 当流操作为纯数值操作时，使用数值流能获得较高的效率。
        IntStream intStream = numbers.stream().mapToInt(num -> num);
        // 每种数值流也都有计算函数如max、min、sum
        OptionalInt max = numbers.stream().mapToInt(num -> num).max();
        System.out.println(max);
    }
}
