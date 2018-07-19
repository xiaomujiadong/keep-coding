package com.saint.netty.chapter3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;

public class TimeServerHandler extends ChannelHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg){
//        ByteBuf byteBuffer = (ByteBuf)msg;
//        byte[] req = new byte[byteBuffer.readableBytes()];
//        byteBuffer.readBytes(req);
//        String body = new String(req);
//        System.out.println("the time server receive order : "+body);
//
//
//    }
}
