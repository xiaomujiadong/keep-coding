package com.saint.demo1.client;

import com.saint.demo1.server.IRPCService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class RPCClient {

    public static void main(String[] args){
        try{
            URL url = new URL("http://localhost:9966/rpc?wsdl");
            QName qName = new QName("http://server.demo1.saint.com/", "IRPCServiceImplService");
            Service service = Service.create(url, qName);
            IRPCService irpcService = service.getPort(IRPCService.class);
            System.out.println(irpcService.RPCMethod("test"));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
