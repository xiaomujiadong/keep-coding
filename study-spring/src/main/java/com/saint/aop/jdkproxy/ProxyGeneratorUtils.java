package com.saint.aop.jdkproxy;

import java.io.FileOutputStream;

import sun.misc.ProxyGenerator;

public class ProxyGeneratorUtils {

    public static void writeProxyClassToHardDisk(String path){
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11", UserServiceImpl.class.getInterfaces());

        FileOutputStream out = null;

        try{
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
