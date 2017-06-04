package com.stream.io.taskVersion;

import com.stream.io.old.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by stream on 2017/5/26.
 */
public class TimeServer {
    
    public static void main(String[] args) {
        int port = 8080;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            socket = null;
            TimeServerHandlerExecutePool pool = new TimeServerHandlerExecutePool(1, 1);
            while (true) {
                socket = serverSocket.accept();
                pool.execute(new TimeServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
