package com.saint.netty.netdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class HelloWorldServer {
    private int port;

    public HelloWorldServer(int port){
        this.port = port;
    }

    public void start(){
        //group的大部分属性的初始化在MultithreadEventExecutorGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            //group指定ServerBootstrap的parentGroup 为bossGroup
            //childGroup为woker
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, worker)
            //channel调用的是ServerBootstrap的父类AbstractBootstrap，作用是初始化serverBootstrap的channelFactory，
            // 里面的参数表示以后创建出来的channel为NioServceSocketChannle对象，channel返回的是serverBootstrap对象
            .channel(NioServerSocketChannel.class)
            //给serverBootstrap初始化localAddress属性
            .localAddress(new InetSocketAddress(port))
            //初始化childHandler,返回serverBootstrap
            .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast(new HelloWorldServerHandler());
                        };
                    })
            //给serverBootstrap中的option赋值,方法为AbstractBootstrap中的
            //option -->new LinkedHashMap<ChannelOption<?>, Object>();
            .option(ChannelOption.SO_BACKLOG, 128)
            //给serverBootstrap中childOption赋值，方法为ServerBootstrap中的
            //childOption -->new LinkedHashMap<ChannelOption<?>, Object>();
            .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，开始接收进来的连接
            //调用父类AbstractBootstrap中的bind
            //bind首先判断group和channelFactory是否为空，根据这个可知，这2个属性为必须的
            //最终调用AbstractBootstrap.doBind(localAddress); localAddress为.localAddress方法初始化的
            //doBind(final SocketAddress localAddress):
            // bind()返回的是DefaultChannelPromise对象
            //sync()只是让ChannelFuture挂起
            ChannelFuture future = serverBootstrap.bind(port).sync();

            System.out.println("Server start listen at " + port );
            future.channel().closeFuture().sync();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new HelloWorldServer(port).start();
    }
}
