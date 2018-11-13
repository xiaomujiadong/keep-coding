package com.saint.netty.chapter5;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    private static final String ECHO_REQ = "Hi, Weclome to netty.$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        for(int i=0; i<10; i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        System.out.println("this is "+(++counter)+" time receive server :{ "+msg+" }");
    }

   @Override
   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        ctx.flush();
   }
   @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable caust){
        caust.printStackTrace();
        ctx.close();
   }
}
