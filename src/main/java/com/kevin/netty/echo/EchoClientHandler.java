package com.kevin.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext arg0, ByteBuf in) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8)); 
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        //super.channelActive(ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", //2
                                                CharsetUtil.UTF_8));
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

}


