package com.dwarf.netty.guide.protostuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwarf.netty.guide.echo.EchoClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 发送必须要以ByteBuf发送
 * 接收同样也要以ByteBuf接收
 * @author jiyu
 *
 */
public class ProtostuffClientHandler extends ChannelHandlerAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(ProtostuffClientHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i = 0; i < 10; i++){
			RequestInfo info = new RequestInfo(); 
			info.subReqID = i;
			info.userName = "u-" + i;
			info.productName = "pro-sh-" + i;
			info.address = "address-sh-" + i;
			
			ctx.write(info);
		}
		ctx.flush();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ResponseInfo info = (ResponseInfo)msg;
		logger.info("receive server response is {}", info);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
        ctx.close();
	}
	
	
}
