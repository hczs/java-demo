package com.hc.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 操作zip
 * ZipInputStream是一种FilterInputStream
 * @author ：hc
 * @date ：Created in 2021/9/12 15:48
 * @modified By：
 */
public class ZipDemo {

    public static void main(String[] args) throws IOException {
        // 打包zip
        createZip();
        // 读取zip包中的数据
        readZip();
    }

    /**
     * 将一个目录中的文件打包
     */
    private static void createZip() throws IOException {
        // 这里构造方法填的是目标目录，就是压缩包放的地方
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("d:\\test.zip"))) {
            // 获取要打包的目录
            File dir = new File("d:\\test");
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    // 放入一个实体，new ZipEntry中如果传名字就是默认打包到zip的根目录下，若传相对路径，就打包到对应相对路径下，zip包是根目录
                    zip.putNextEntry(new ZipEntry(file.getName()));
                    zip.write(getFileDataAsBytes(file));
                    zip.closeEntry();
                }
            }
        }
    }

    /**
     * 读取zip包中的文件数据
     * @throws IOException IOException
     */
    private static void readZip() throws IOException {
        try(ZipInputStream zip = new ZipInputStream(new FileInputStream("d:\\test.zip"))) {
            ZipEntry entry;
            // 缓冲区读
            byte[] cache = new byte[1024];
            // 存储读取出来的数据
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ( (entry = zip.getNextEntry()) != null) {
                System.out.println("entryName: " + entry.getName());
                if (!entry.isDirectory()) {
                    int n;
                    while ((n = zip.read(cache)) != -1) {
                        System.out.println("read " + n + " bytes");
                        byteArrayOutputStream.write(cache, 0, n);
                    }
                }
            }
            System.out.println(byteArrayOutputStream);
        }
    }

    /**
     * 读取文件中的字节流
     * @param file File对象
     * @return byte[]
     * @throws IOException IOException
     */
    private static byte[] getFileDataAsBytes(File file) throws IOException {
        byte[] bytes = new byte[1024];
        int length = 0;
        // 神器，可以临时搞一个输出流存字节数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(InputStream inputStream = new FileInputStream(file)) {
            while ( (length = inputStream.read(bytes)) != -1 ) {
                byteArrayOutputStream.write(bytes, 0, length);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
