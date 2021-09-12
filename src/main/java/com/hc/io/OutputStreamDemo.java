package com.hc.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * OutputStream 使用
 * @author ：hc
 * @date ：Created in 2021/9/12 15:11
 * @modified By：
 */
public class OutputStreamDemo {

    public static void main(String[] args) throws IOException {
        // 基础使用
        baseUse();
    }

    /**
     * OutputStream基础使用
     * @throws IOException IOException
     */
    private static void baseUse() throws IOException {
        OutputStream outputStream = new FileOutputStream("d:\\test.txt");
        // 如果test.txt里面有值的话就会把内容覆盖掉！
        // 和read一样，write也有三个方法，都是类似的
        // write(byte[]) / write(int n) / write(byte[], off, len)
        outputStream.write("覆盖内容".getBytes(StandardCharsets.UTF_8));
        // 事实上，如果写入数据得话，会先写入缓冲区，然后攒的差不多了，才会将缓冲区内容真正写进磁盘，做一次磁盘IO
        // flush就是强制将缓冲区中的内容刷进磁盘，不过close方法调用时也会将缓冲区的内容刷进磁盘
        // 其实read的时候也有缓冲区，先读若干个字节到缓冲区，再执行接下来的read方法
        outputStream.flush();
        outputStream.close();

        // 建议使用try(resources)方式来操作，编译器会帮我们在finally块中进行close操作~
        try(OutputStream outputStream1 = new FileOutputStream("d:\\test.txt")) {
            outputStream1.write("hello world!".getBytes(StandardCharsets.UTF_8));
        }
    }
}
