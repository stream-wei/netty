package com.stream.netty.old;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by stream on 2017/5/25.
 */
public class TimeClient {
    
    public static void main(String[] args) throws Exception{
        int port = 8080;
        Socket socket = new Socket("127.0.0.1",port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
        printWriter.println("QUERY TIME ORDER");
        System.out.println(reader.readLine());
        reader.close();
        printWriter.close();
    }
}
