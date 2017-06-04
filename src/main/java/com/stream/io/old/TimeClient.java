package com.stream.io.old;

import java.io.*;
import java.net.Socket;

/**
 * Created by stream on 2017/5/25.
 */
public class TimeClient implements Runnable{
    
    public static void main(String[] args) throws Exception{
//        for (int i = 0; i < 11; i++) {
            TimeClient timeClient = new TimeClient();
            new Thread(timeClient).start();
//        }
        
    }
    
    public void run(){
        int port = 8080;
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("QUERY TIME ORDER");
            System.out.println(reader.readLine());
            reader.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
