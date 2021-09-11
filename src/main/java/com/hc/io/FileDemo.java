package com.hc.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件操作相关demo
 * @author ：hc
 * @date ：Created in 2021/9/11 18:37
 * @modified By：
 */
public class FileDemo {

    public static void main(String[] args) throws IOException {
        // 创建file，可以是文件，也可以是目录
        newFile();
        // file相关操作：判断 文件/目录 读写权限、创建删除 文件/目录
        operateFile();
        // 遍历目录，按条件筛选
        traversalFile();
        // 操作nio.file提供的Path接口
        operatePath();
    }

    /**
     * 创建File的方式
     */
    private static void newFile() throws IOException {
        // 传一个 "." 代表的是当前目录，当前目录就是你的java项目的目录
        // 传一个 "/" 或 "\\" 就是表示的java项目所在磁盘的根目录
        // 传一个 ".." 就是代表着上一级目录，也就是项目所属文件夹
        // 也可以传绝对路径，就是表示的绝对路径目录
        // 如果里面传的是具体文件file就是表示的具体文件，传目录就是表示的目录，file就是用来操作文件和目录的
        File file = new File("..");
        // 返回绝对路径
        System.out.println(file.getAbsolutePath());
        // 返回创建file时传入构造方法的路径
        System.out.println(file.getPath());
        // 返回规范路径
        System.out.println(file.getCanonicalPath());

        // 通过isFile和isDirectory判断是file是文件还是目录
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());

        // File.separator可以获取当前系统路径分隔符的表示符号，比如win是 "\" ,linux是 "/"
        System.out.println(File.separator);
    }

    /**
     * file相关操作：判断 文件/目录 读写权限、创建删除 文件/目录
     */
    private static void operateFile() throws IOException {
        File file = new File("..");

        // 是否可读
        System.out.println(file.canRead());
        // 是否可写
        System.out.println(file.canWrite());
        // 是否可执行，如果file是目录，canExecute代表就是是否可以列出它包含的文件夹和子目录
        System.out.println(file.canExecute());

        // 创建删除文件
        // 在当前工程根目录下创建文件
        File testTxt = new File("./test.txt");
        // 创建文件
        if (!testTxt.exists()) {
            boolean success = testTxt.createNewFile();
            System.out.println(success ? "创建成功！" : "创建失败！");
        }
        // 删除文件
        boolean success = testTxt.delete();
        System.out.println(success ? "删除成功！" : "删除失败！");

        // 创建删除目录
        // 在当前工程根目录下创建目录
        File testDir = new File("./dir");
        // 创建当前file对象表示的目录
        if (testDir.mkdir()) {
            System.out.println("创建dir目录成功！");
        } else {
            // 创建当前file对象表示的目录，如果父目录不存在也会把不存在的父目录创建出来
            boolean mkdirs = testDir.mkdirs();
            System.out.println(mkdirs ? "mkdirs创建dir目录成功！" : "创建dir目录失败！");
        }
        // 删除目录
        boolean deleteDir = testDir.delete();
        System.out.println(deleteDir ? "删除dir目录成功" : "删除dir目录失败");
    }

    /**
     * 遍历目录
     */
    private static void traversalFile() throws IOException {
        File file = new File(".");
        File[] files = file.listFiles();
        if (files != null) {
            for (File e : files) {
                System.out.println(e.getCanonicalPath());
            }
        }

        System.out.println("==============只列出文件，不列出目录===============");
        // listFiles还提供了多种带过滤的重载方法，比如可以list的时候添加过滤条件
        File[] listFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // 这里只列出目录下所有文件，不列出目录
                return pathname.isFile();
            }
        });
        // 上面那个也可以用lambda表达式来写，因为FileFilter是@FunctionalInterface函数式接口
        File[] listFiles1 = file.listFiles(File::isFile);
        if (listFiles != null) {
            for (File listFile : listFiles) {
                System.out.println(listFile);
            }
        }
    }

    /**
     * 操作nio.file提供的Path接口
     * 如果需要对目录进行复杂的拼接、遍历等操作，使用Path对象更方便。
     */
    private static void operatePath() {
        Path path = Paths.get(".");
        System.out.println(path);
        // 转为绝对路径
        System.out.println(path.toAbsolutePath());
        // 转为File对象
        File file = path.toFile();
        System.out.println(file.getAbsolutePath());
    }
}
