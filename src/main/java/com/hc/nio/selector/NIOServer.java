package com.hc.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO实现非阻塞的服务器
 * @author ：hc
 * @date ：Created in 2021/9/21 20:37
 * @modified By：
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建serverSocketChannel来接收连接，生成socketChannel来处理连接
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));
        // 创建selector对象
        Selector selector = Selector.open();

        serverSocketChannel.configureBlocking(false);
        // 将serverSocketChannel注册到selector中去，进行事件(OP_ACCEPT)监听，有连接事件就操作
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了一秒，服务器无事件发生...");
                continue;
            }
            // 如果有事件发生了，拿到selectionKeys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey curKey = keyIterator.next();
                // 如果是OP_ACCEPT 客户端连接事件
                if (curKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) curKey.channel();
                    // 接收连接生成SocketChannel，然后就可以进行进一步操作了，进一步操作是啥不管，把这个channel放入到selector管理
                    SocketChannel socketChannel = channel.accept();
                    System.out.println("有一个客户端连接成功！客户端地址：" + socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    // 第三个参数目前含义不明，应该是放读取的数据的，就是这个socketChannel目前有一个专门属于自己的buffer来操作
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                // 如果是OP_READ事件
                if (curKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) curKey.channel();
                    // 这个attachment就是获取register时的第三个参数buffer
                    ByteBuffer buffer = (ByteBuffer) curKey.attachment();
                    // 是否需要对buffer进行clear，参数重置？
                    buffer.clear();
                    // 读取到buffer中
                    int length = channel.read(buffer);
                    System.out.println("read from 客户端(" + channel.getRemoteAddress() +"): " + new String(buffer.array(), 0, length));
                }
                keyIterator.remove();
            }
        }
    }
}
