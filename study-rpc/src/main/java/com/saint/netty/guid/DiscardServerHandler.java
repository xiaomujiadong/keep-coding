package com.saint.netty.guid;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.ByteBuffer;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg){
//        ByteBuf byteBuf = (ByteBuf)msg;
//        try{
//            while(byteBuf.isReadable()){
//                System.out.println((char)byteBuf.readByte());
//                System.out.flush();
//            }
//        }finally {
//            ReferenceCountUtil.release(msg);
//        }
        channelHandlerContext.write(msg);
        channelHandlerContext.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable){
        //出现异常就关闭连接
        throwable.printStackTrace();
        channelHandlerContext.close();
    }
}
