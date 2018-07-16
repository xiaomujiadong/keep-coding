package com.saint.demo1.server;

import javax.xml.ws.Endpoint;

public class RPCServiceMain {

    public static void main(String[] args){
        Endpoint.publish("http://localhost:9966/rpc", new IRPCServiceImpl());
    }
}
