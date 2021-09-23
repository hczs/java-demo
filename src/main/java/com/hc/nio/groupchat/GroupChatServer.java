package com.hc.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author ：hc
 * @date ：Created in 2021/9/22 20:15
 * @modified By：
 */
public class GroupChatServer {

    /**
     * 服务端selector
     */
    private Selector selector;

    public GroupChatServer() {
        try {
            // 初始化工作
            selector = Selector.open();
            ServerSocketChannel listenerChannel = ServerSocketChannel.open();
            listenerChannel.bind(new InetSocketAddress(8888));
            listenerChannel.configureBlocking(false);
            // 注册到selector，绑定accept事件
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端已启动，监听端口：8888");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听连接，轮询
     */
    public void listen(){
        try {
            while (true) {
                int count = selector.select();
                // 如果有事件处理
                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey selectionKey = selectionKeyIterator.next();
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = channel.accept();
                            socketChannel.configureBlocking(false);
                            // 将新连接注册到selector管理
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线啦！");
                            System.out.println("当前在线人数：" + (selector.keys().size() - 1));
                        }
                        if (selectionKey.isReadable()) {
                            // 处理读客户端数据
                            readData(selectionKey);
                        }
                        // 删除当前key，防止重复处理
                        selectionKeyIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过SelectionKey，反向获取channel，然后读取数据
     * @param key SelectionKey
     */
    private void  readData(SelectionKey key){
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int read = channel.read(byteBuffer);
            if (read > 0) {
                // 缓冲区数据转字符串打印
                String msg = new String(byteBuffer.array(), 0, read);
                System.out.println("from client：" + msg);
                // 向其他客户端转发消息
                sendToOtherClient( channel.getRemoteAddress() +" 说: " + msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了!");
                // 离线如何操作？取消注册，关闭通道
                key.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    /**
     * 服务器转发给其他客户端消息
     * @param msg 消息内容
     * @param self 被转发消息的客户端
     */
    private void sendToOtherClient(String msg, SocketChannel self) {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            Channel channel =  key.channel();
            // 排除发消息的客户端
            if (channel instanceof SocketChannel && channel != self) {
                // 转发
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                try {
                    // 数据写入通道
                    ((SocketChannel) channel).write(byteBuffer);
                    System.out.println("正在向客户端：" + ((SocketChannel) channel).getRemoteAddress() + "转发消息...");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("消息转发失败！");
                }
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
