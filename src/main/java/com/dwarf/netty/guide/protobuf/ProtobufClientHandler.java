package com.dwarf.netty.guide.protobuf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ProtobufClientHandler extends ChannelHandlerAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(ProtobufClientHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i = 0; i < 10; i++){
			SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder(); 
			builder.setSubReqID(i);
			builder.setUserName("sh-" + i);
			builder.setProductName("pro-sh-" + i);
			builder.addAddress("address-sh-" + i);
			SubscribeReqProto.SubscribeReq req = builder.build();
			ctx.write(req);
		}
		ctx.flush();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeRespProto.SubscribeResp resp = (SubscribeRespProto.SubscribeResp)msg;
		logger.info("receive server response is {}", resp);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	
	
}
