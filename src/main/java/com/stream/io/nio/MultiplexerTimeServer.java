package com.stream.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by stream on 2017/6/4.
 */
public class MultiplexerTimeServer implements Runnable {
    
    private Selector selector;
    
    private ServerSocketChannel channel;
    
    private volatile boolean stop;
    
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port), 1024);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port :" + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stop(){
        this.stop = true;
    }
    
    public void run() {
        while (stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    
                }
            } catch (Exception e){
            
            }
        }
    }
    
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel accept = ssc.accept();
                accept.configureBlocking(false);
                ssc.register(selector,SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    byte [] bytes = new byte[byteBuffer.remaining()];
                    
                }
            }
        }
    }
}
