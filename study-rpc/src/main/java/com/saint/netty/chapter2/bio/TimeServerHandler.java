package com.saint.netty.chapter2.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;

public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try{
            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            printWriter = new PrintWriter(this.socket.getOutputStream(), true);

            String currnetTime = null;
            String body = null;

            while(true){
                body = bufferedReader.readLine();
                if(null == body){
                    break;
                }
                System.out.println("The time server receive order : "+body);
                currnetTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Timestamp(System.currentTimeMillis()).toString():"BAD ORDER";
                printWriter.println(currnetTime);
                System.out.println("currentTime: "+currnetTime);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(printWriter!=null){
                printWriter.close();
            }
            printWriter = null;
            if(this.socket!=null){
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.socket = null;
        }
    }
}
