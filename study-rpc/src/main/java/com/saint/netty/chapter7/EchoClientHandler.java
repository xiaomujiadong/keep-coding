package com.saint.netty.chapter7;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final int sendnumber;

    public EchoClientHandler(int sendnumber){
        this.sendnumber = sendnumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){

    }
}
