package online.stringtek.nettyinaction.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group=new NioEventLoopGroup();
        EchoServerHandler echoServerHandler=new EchoServerHandler();
        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(group).localAddress(new InetSocketAddress(8888))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(echoServerHandler);
                    }
                 });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
