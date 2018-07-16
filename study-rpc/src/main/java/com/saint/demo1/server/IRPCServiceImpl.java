package com.saint.demo1.server;

import javax.jws.WebService;

@WebService(endpointInterface = "com.saint.demo1.server.IRPCService")
public class IRPCServiceImpl implements IRPCService {
    @Override
    public String RPCMethod(String param) {
        System.out.println("service received: "+param);
        return "RPC Method invoked: "+param;
    }
}
