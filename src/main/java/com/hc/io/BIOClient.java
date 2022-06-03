package com.hc.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author : hc
 * @date : Created in 2022/6/2 22:43
 * @modified :
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 8888);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            String message = "hello";
            dos.write(message.getBytes(StandardCharsets.UTF_8));
            dos.flush();
        }
    }
}
