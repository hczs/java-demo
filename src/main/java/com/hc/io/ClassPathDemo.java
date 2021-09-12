package com.hc.io;

import java.io.*;
import java.util.Properties;

/**
 * @author ：hc
 * @date ：Created in 2021/9/12 17:09
 * @modified By：
 */
public class ClassPathDemo {

    public static void main(String[] args) throws IOException {
        // 基本使用
        baseUse();

        // 从classPath获取和从外部文件获取
        Properties props = new Properties();
        props.load(inputStreamFromClassPath("/default.properties"));
        props.load(inputStreamFromFile(".conf.properties"));
    }

    /**
     * classPath文件基本读取
     */
    private static void baseUse() throws IOException {
        try (InputStream resourceAsStream = ClassPathDemo.class.getResourceAsStream("/default.properties")) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            System.out.println(properties.getProperty("name"));
            System.out.println(properties.getProperty("age"));
            System.out.println(properties.getProperty("gender"));
            // 使用过这个输入流之后，输入流resourceAsStream就是空了，下面再用这个流的话还需要重新获取，因为输入流只能用一次
            byte[] cache = new byte[1024];
            int length = 0;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 一定要判空
            if (resourceAsStream != null) {
                while ( (length = resourceAsStream.read(cache)) != -1) {
                    // 只往输出流中写入读取的字节长度，因为cache存在空字节问题
                    byteArrayOutputStream.write(cache, 0, length);
                }
            }
            System.out.println(byteArrayOutputStream);
        }
    }

    /**
     * 从classpath中获取配置文件
     * @param filePath 文件相对路径
     * @return InputStream
     */
    private static InputStream inputStreamFromClassPath(String filePath) {
         return ClassPathDemo.class.getResourceAsStream(filePath);
    }

    /**
     * 从外部文件获取配置文件
     * @param filePath 文件路径：相对/绝对
     * @return InputStream
     * @throws FileNotFoundException FileNotFoundException
     */
    private static InputStream inputStreamFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        // 如果文件不存在就新建
        if (!file.exists()) {
            System.out.println("file " + filePath + " is not exists!");
            boolean success = file.createNewFile();
            System.out.println(success ? "文件创建成功！" : "文件创建失败！");
        }
        return new FileInputStream(file);
    }
}
