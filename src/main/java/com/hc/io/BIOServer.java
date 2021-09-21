package com.hc.io;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用BIO实现简单服务器
 * 使用多个telnet 127.0.0.1 8888
 * ctrl + ] > send message 进行发送消息
 * 单线程测试：一次只能处理一个请求，只有那个连接关闭，才可以处理下一个连接请求
 * 多线程测试：可以处理多个，但是线程资源有限，处理请求数依然不乐观
 * @author ：hc
 * @date ：Created in 2021/9/21 15:13
 * @modified By：
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        while (true) {
            System.out.println("等待连接...");
            // 如果没有连接将会一直阻塞在accept
            Socket socket = serverSocket.accept();
            // 如果可以运行到这一行，证明不再是在accept阻塞了，也就是有连接进来了
            System.out.println("有客户端连接了！");
            // 有连接后，main线程把连接交给线程池中的一个线程去处理，main继续等待连接...
            threadPoolExecutor.execute(() -> handler(socket));
        }
    }

    /**
     * 读取已连接的Socket数据
     * @param socket socket
     */
    private static void handler(Socket socket){
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName()
                    + "处理客户端请求，客户端地址：" + socket.getRemoteSocketAddress());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("连接关闭，客户端地址为：" + socket.getRemoteSocketAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
