package com.saint.netty.guid;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext channelHandlerContext){
        final ByteBuf time = channelHandlerContext.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture channelFuture = channelHandlerContext.writeAndFlush(time);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture innerChannelFuture) throws Exception {
                assert channelFuture == innerChannelFuture;
                channelHandlerContext.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable){
        throwable.printStackTrace();
        channelHandlerContext.close();
    }
}
