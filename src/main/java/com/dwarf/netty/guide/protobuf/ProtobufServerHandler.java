package com.dwarf.netty.guide.protobuf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ProtobufServerHandler extends ChannelHandlerAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(ProtobufServerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("received message from client is {}", msg);
		SubscribeReqProto.SubscribeReq subscribeReq = (SubscribeReqProto.SubscribeReq)msg;
		SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
		builder.setSubReqID(subscribeReq.getSubReqID());
		builder.setRespCode("200");
		builder.setDesc("received message successful");
		SubscribeRespProto.SubscribeResp subscribeResp = builder.build();
		ctx.writeAndFlush(subscribeResp);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	
}
