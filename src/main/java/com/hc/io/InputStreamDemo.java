package com.hc.io;

import java.io.*;

/**
 * InputStream使用
 * @author ：hc
 * @date ：Created in 2021/9/11 20:10
 * @modified By：
 */
public class InputStreamDemo {

    public static void main(String[] args) throws IOException {
        // InputStream基础使用，注意InputStream是抽象类，不是接口
        baseInputStream();

        // 使用缓冲区的方式来读取字节流
        readFile();
    }

    /**
     * InputStream基本使用
     */
    private static void baseInputStream() throws IOException {
        InputStream inputStream = null;
        int n;
        try {
            inputStream = new FileInputStream("d:\\test.txt");
            // read正常情况下返回的是读取的字节的int值(0-255)，如果读完了就返回-1
            while ( (n = inputStream.read()) != -1 ) {
                System.out.println(n);
            }
        } finally {
            // 记得及时释放资源，不释放资源得话程序就会一直占用着资源
            if (inputStream != null) {
                inputStream.close();
            }
        }

        // 使用try(resources) 这种方式会自动释放资源，也就是编译器会自己添加finally块调用close方法
        try ( InputStream inputStream1 = new FileInputStream("d:\\test.txt")) {
            while ( (n = inputStream1.read()) != -1 ) {
                System.out.println(n);
            }
        }
    }

    /**
     * 普通的read方法一次读取一个字节，这样读取效率太低
     * 现在搞一个缓冲区来读取，就是一次性读取多个字节到缓冲区，对于文件和网络流来说，利用缓冲区一次性读取多个字节的效率不错
     * InputStream有两个重载方法来支持读取多个字节
     * int read(byte[] b)：读取若干字节并填充到byte[]数组，返回读取的字节数
     * int read(byte[] b, int off, int len)：指定byte[]数组的偏移量和最大填充数
     */
    private static void readFile() throws IOException {
        try (InputStream inputStream = new FileInputStream("d:\\test.txt")) {
            // 定义一个1000个字节的缓冲区
            byte[] buffer = new byte[1000];
            int n;
            while ( (n = inputStream.read(buffer)) != -1 ) {
                System.out.println("read " + n + " bytes");
            }
        }
    }
}
