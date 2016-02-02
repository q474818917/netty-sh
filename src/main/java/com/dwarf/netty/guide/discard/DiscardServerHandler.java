package com.dwarf.netty.guide.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DiscardServerHandler extends ChannelHandlerAdapter{
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
		System.out.println("接收数据");
		ByteBuf in = (ByteBuf) msg;
		System.out.println(in.toString(io.netty.util.CharsetUtil.UTF_8));
		ctx.write(msg);
		ctx.flush();
    }
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//建立连接，准备传输时invoke
		System.out.println(ctx + "已建立连接");
		super.channelActive(ctx);
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
	
}
