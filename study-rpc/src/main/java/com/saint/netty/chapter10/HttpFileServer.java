package com.saint.netty.chapter10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

    public void run(final int port, final String url)throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker);
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            socketChannel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            socketChannel.pipeline().addLast("http-encoder", new HttpResponseDecoder());
                            socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            socketChannel.pipeline().addLast("fileServerHandler", new HttpFileServerHander());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(url, port).sync();
            System.out.println("HTTP 文件目录服务器已经启动，网址L: 127.0.0.1:"+port+url);

            channelFuture.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        String url = "";

        new HttpFileServer().run(port, url);

    }

}
