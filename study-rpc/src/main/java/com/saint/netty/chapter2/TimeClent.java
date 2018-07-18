package com.saint.netty.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClent {

    public static void main(String[] args){
        int port = 8080;

        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try{
            socket = new Socket("127.0.0.1", port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("QUERY TIME ORDER");
            System.out.println("send order 2 server succeed");
            String resp = bufferedReader.readLine();
            System.out.println("Now is "+resp);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(null!=printWriter){
                printWriter.close();
            }
            printWriter = null;

            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bufferedReader = null;
            }

            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;


        }
    }
}
