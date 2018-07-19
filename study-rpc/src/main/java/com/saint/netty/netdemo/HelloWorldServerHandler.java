package com.saint.netty.netdemo;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg){
        System.out.println("server channelRead..");
        System.out.println(channelHandlerContext.channel().remoteAddress()+"->server: "+msg.toString());
        channelHandlerContext.write("server write: "+msg);
        channelHandlerContext.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable){
        throwable.printStackTrace();
        channelHandlerContext.close();
    }
}
