package com.dwarf.netty.guide.protostuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ProtostuffServerHandler extends ChannelHandlerAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(ProtostuffServerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("服务端显示建立连接...");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("received message from client is {}", msg);
		RequestInfo info = (RequestInfo)msg;
		System.out.println(info.subReqID);
		/*ResponseInfo respInfo = new ResponseInfo();
		respInfo.subReqID = info.subReqID;
		respInfo.respCode = "200";
		respInfo.desc = "received message successful";
		
		ctx.writeAndFlush(respInfo);*/
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
        ctx.close();
	}
	
}
