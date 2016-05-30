package com.kevin.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port){
        super();
        this.port = port;
    }
    
    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                .group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
    
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // TODO Auto-generated method stub
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                    
                });
            
            ChannelFuture future = serverBootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel());
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
    
}


