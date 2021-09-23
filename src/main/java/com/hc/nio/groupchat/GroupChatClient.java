package com.hc.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author ：hc
 * @date ：Created in 2021/9/22 20:45
 * @modified By：
 */
public class GroupChatClient {

    private final Selector selector;

    private final SocketChannel socketChannel;


    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()) {
            System.out.println("正在连接服务器...");
        }
        System.out.println("成功连接至服务器，本机地址：" + socketChannel.getLocalAddress());
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    /**
     * 向服务端发送消息
     * @param message 消息内容
     */
    public void sendMessage(String message) throws IOException {
        socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 后台启动，持续获取服务端发来的消息
     */
    public void receiveMessage() throws IOException {
        while (true) {
            int select = selector.select();
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int read = channel.read(byteBuffer);
                        if (read != -1) {
                            System.out.println(new String(byteBuffer.array(), 0, read));
                        }
                    }
                    // 删除当前key，防止重复操作
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient groupClient = new GroupChatClient();
        // 启动一个线程持续接收消息
        new Thread(()-> {
            try {
                groupClient.receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        // 主线程进行发送消息互动
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            groupClient.sendMessage(s);
        }
    }
}
