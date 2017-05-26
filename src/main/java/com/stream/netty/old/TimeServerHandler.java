package com.stream.netty.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by stream on 2017/5/25.
 */
public class TimeServerHandler implements Runnable {
    
    private Socket socket;
    
    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }
    
    public void run() {
        BufferedReader reader = null;
        PrintWriter printWriter = null;
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            printWriter = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while (true){
                body = reader.readLine();
                if (body == null){
                    break;
                }
                System.out.println("The time server receive order : " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                printWriter.println(currentTime);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
