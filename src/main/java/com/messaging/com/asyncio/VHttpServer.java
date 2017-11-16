package com.messaging.com.asyncio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

public class VHttpServer {


    public static void main(String args[]) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            int port = 9100;
            Channel channel = bootstrap.group(bossGroup, childGroup)
                    .handler(new LoggingHandler())
                    .childHandler(new VHttpServerHandler())
                    .channel(NioServerSocketChannel.class)
                    .bind(port).sync().channel();

            System.out.println("Started server at port: " + port);

            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
