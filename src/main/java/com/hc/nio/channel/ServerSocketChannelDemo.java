package com.hc.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * ServerSocketChannel使用
 * 实现非阻塞模式下，监听本机的一个端口
 * 无连接就打印null
 * 有连接就接收，创建SocketChannel对象，打印连接者的地址信息
 * @author ：hc
 * @date ：Created in 2021/9/20 17:53
 * @modified By：
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8888;
        ByteBuffer buffer = ByteBuffer.wrap("hello,nio!".getBytes(StandardCharsets.UTF_8));
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        // 设置模式非阻塞，如果设置为true得话，就会阻塞，也就是下面accept的时候，必须得有连接进来才会进行下面的if判断
        serverSocketChannel.configureBlocking(false);
        while (true) {
            System.out.println("waiting for connections...");
            // 如果设置成阻塞的话，没有连接的情况，就会一直阻塞到这一行，后面的代码也不会执行
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                System.out.println("null...");
                Thread.sleep(2000);
            } else {
                System.out.println("incoming connection from: " + socketChannel.getRemoteAddress());
                // 缓冲区指针指向0
                buffer.rewind();
                socketChannel.write(buffer);
                socketChannel.close();
            }
        }
    }
}
