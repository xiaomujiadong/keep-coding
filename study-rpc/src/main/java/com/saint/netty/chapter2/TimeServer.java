package com.saint.netty.chapter2;

import javax.ws.rs.container.TimeoutHandler;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args){
        int port = 8080;

        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is start in port: "+port);

            Socket socket = null;

            while(true){
                socket = serverSocket.accept();

                new Thread(new TimeServerHandler(socket)).start();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
