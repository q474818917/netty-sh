package com.dwarf.netty.guide.protostuff;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;


public class ProtostuffDecoder extends MessageToMessageDecoder<ByteBuf> {
	
	Schema<RequestInfo> schema = RuntimeSchema.getSchema(RequestInfo.class);
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		final byte[] array;
        final int offset;
        final int length = msg.readableBytes();
        if (msg.hasArray()) {
            array = msg.array();
            offset = msg.arrayOffset() + msg.readerIndex();
        } else {
            array = new byte[length];
            msg.getBytes(msg.readerIndex(), array, 0, length);
            offset = 0;
        }
        
        byte[] data = new byte[length];
        msg.readBytes(data);
        
        Schema<RequestInfo> schema = RuntimeSchema.getSchema(RequestInfo.class);
        RequestInfo info = new RequestInfo();
        ProtostuffIOUtil.mergeFrom(data, info, schema);
		out.add(info);
	}

}
