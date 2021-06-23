package online.stringtek.nettyinaction.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String id = ctx.channel().id().asLongText();
        ctx.writeAndFlush(Unpooled.copiedBuffer("client:"+id+" connected.", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf buf)
        throws Exception {
        System.out.println(buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
