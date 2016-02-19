package com.dwarf.nio.uptime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class UptimeClientHandler extends ChannelHandlerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(UptimeClientHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("客户端已连接...");
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(!(evt instanceof IdleStateEvent)){
			return;
		}
		IdleStateEvent ie = (IdleStateEvent)evt;
		if(ie.state() == IdleState.ALL_IDLE){
			logger.info("读写同时超时...");
		}else if(ie.state() == IdleState.READER_IDLE){
			
			logger.info("读超时...");
		}else if(ie.state() == IdleState.WRITER_IDLE){
			logger.info("写超时...");
		}
		ctx.channel().write("ping\n");
		logger.info("处理心跳...");
	}
	
}
