package com.hc.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * NIOClient
 * 向服务端发消息
 * @author ：hc
 * @date ：Created in 2021/9/21 20:59
 * @modified By：
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);
        // 连接不会阻塞
        if (!socketChannel.connect(socketAddress)) {
            // 如果没连上还可以做其他事情
            while (!socketChannel.finishConnect()) {
                System.out.println("做其他事情...");
            }
        }
        while (true) {
            // 输入数据发送
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            // 如果连接成功就发送数据
            // 这个wrap其实就是封装了，创建指定大小，然后挨个put一系列操作，wrap返回的byteBuffer大小就是参数中字节数组的大小
            ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
            // 发送数据，其实就是write
            socketChannel.write(byteBuffer);
        }
    }
}
