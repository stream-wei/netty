package com.stream.io.old;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by stream on 2017/5/25.
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("socket server start");
            while (true){
                System.out.println("wait accpet");
                Socket socket = serverSocket.accept();
                System.out.println("client in");
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (serverSocket != null){
                System.out.println("The time server close");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
