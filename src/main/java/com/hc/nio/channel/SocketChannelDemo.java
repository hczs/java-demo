package com.hc.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketChannel
 * 基于TCP
 * @author ：hc
 * @date ：Created in 2021/9/20 18:42
 * @modified By：
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws IOException {
        // 使用open(SocketAddress remote)
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

        // 方式二 使用connect
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("www.baidu.com", 80));

        // 设置非阻塞
        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socketChannel.read(buffer);
        socketChannel.close();
        System.out.println("read over!");
    }
}
