import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by vamsi on 7/7/17.
 */
public class MainClient {

    public static void main(String args[]) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //pipeline.addLast(new LoggingHandler());
                            pipeline.addLast(new HttpClientCodec());
                        }
                    });

            String host = getHostIpAddr();
            if ("".equals(host)) {
                System.exit(1);
            }

            Integer port = 12000;
            int maxRequests = 1000;

            for (int i = 0; i < maxRequests; i++) {
                Channel channel = bootstrap.connect(host, port).sync().channel();

                ByteBuf payloadByteBuf = Unpooled.copiedBuffer("", CharsetUtil.UTF_8);

                HttpRequest request = new DefaultFullHttpRequest(
                        HttpVersion.HTTP_1_1, HttpMethod.POST, "/messages", payloadByteBuf);

                request.headers().set(HttpHeaderNames.HOST, host);
                request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
                request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);

                request.headers().set("X_RESTBUS_MESSAGE_ID", UUID.randomUUID().toString());
                request.headers().set("X_RESTBUS_GROUP_ID", UUID.randomUUID().toString());

                //flush request
                channel.writeAndFlush(request);

                channel.closeFuture().sync();
            }

        } finally {
            group.shutdownGracefully();
        }
    }

    private static String getHostIpAddr() throws SocketException {
        String host = "";
        NetworkInterface wlp3s0 = NetworkInterface.getByName("wlp3s0");
        Enumeration<InetAddress> iNetAddresses = wlp3s0.getInetAddresses();
        while (iNetAddresses.hasMoreElements()) {
            InetAddress inetAddress = iNetAddresses.nextElement();
            host = inetAddress.getHostAddress();
        }
        return host;
    }

}
