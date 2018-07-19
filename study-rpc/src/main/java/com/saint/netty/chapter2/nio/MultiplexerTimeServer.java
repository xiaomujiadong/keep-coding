package com.saint.netty.chapter2.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel socketChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int prot){
        try{
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(prot), 1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("the time server is start.");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterable = set.iterator();
                SelectionKey selectionKey = null;
                while (iterable.hasNext()){
                    selectionKey = iterable.next();
                    iterable.remove();
                    
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
