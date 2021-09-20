package com.hc.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 演示：使用FileChannel从文件读取数据到Buffer
 * @author ：hc
 * @date ：Created in 2021/9/19 20:52
 * @modified By：
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        // 写入数据
        channelWrite();

        // 读取数据
        channelRead();

        // 其他方法如：position、size、truncate、force演示
        otherFunction();

        // 两个channel通信，互相传数据
        transferData();
    }

    /**
     * channel写入数据
     * @throws IOException io异常
     */
    private static void channelWrite() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\hello.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        // 把以下数据写入文件
        String newData = "write some words..." + System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(newData.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
            System.out.println("已经写入...");
        }
        channel.close();
    }

    /**
     * channel读取数据
     * @throws IOException io异常
     */
    private static void channelRead() throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\hello.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        // 接收数据的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(48);
        // 这个返回值的含义就是本次读取了多少数据，如果没数据了就是-1（到了文件末尾），和普通流的读取有点类似
        int read = channel.read(buffer);
        while (read != -1) {
            System.out.println("读取了：" + read);
            // 将缓存字节数组的指针设置为数组的开始序列即数组下标0。这样就可以从buffer开头，对该buffer进行遍历（读取）了。
            buffer.flip();
            while (buffer.hasRemaining()) {
                // 读的字节转成字符，查看字符是否与文本一样
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            // 有可能一次没读完，继续读（就是文件大小字节数大于缓冲区设定的字节数的时候，需要接着读）
            read = channel.read(buffer);
        }
        channel.close();
        System.out.println("结束了！");
    }

    /**
     * 其他方法演示
     */
    private static void otherFunction() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\hello.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        // position，定到channel的特定位置来进行读取写入操作
        // position 设置在文件结束符之后，读取得话会返回-1，写入的话可以正常写入，但是会造成文件空洞，磁盘上物理文件写入的数据间有空隙
        long position = channel.position();
        System.out.println("channel 当前位置：" + position);
        channel.position(position + 12);
        System.out.println("channel 改变位置：" + channel.position());
        // size() 获取该通道所关联文件的大小
        System.out.println("channel所关联文件大小为：" + channel.size());
        // truncate() 方法，截取文件，下面的情况就是会把第20个字节之后的内容都删除
        channel.truncate(20);
        System.out.println("截取后文件大小：" + channel.size());
        // force(boolean metaData) 可以类比为stream中的flush方法，把缓冲区的内容刷到磁盘上
        // 这个布尔类型的参数含义是是否将文件元数据（权限信息等）写入到磁盘
        channel.force(true);
    }

    /**
     * 两个通道间通信，互相传数据
     * transferFrom和transferTo
     */
    private static void transferData() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("d:\\hello.txt", "rw");
        RandomAccessFile bFile = new RandomAccessFile("d:\\test.txt", "rw");
        // 将aFile中的内容复制到bFile中
        FileChannel fromChannel = aFile.getChannel();
        FileChannel toChannel = bFile.getChannel();

        // transferFrom，从目标channel传数据到当前channel
        // 从fromChannel的位置0，复制size个字节到toChannel中
        toChannel.transferFrom(fromChannel, 0, fromChannel.size());

        // transferTo，从当前Channel传数据到目标Channel中
        RandomAccessFile cFile = new RandomAccessFile("d:\\c.txt", "rw");
        FileChannel cFileChannel = cFile.getChannel();
        fromChannel.transferTo(0, fromChannel.size(), cFileChannel);

        aFile.close();
        bFile.close();
        cFile.close();
        System.out.println("复制完成！");
    }
}
