package com.dwarf.netty.guide.protostuff;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtostuffEncoder extends MessageToMessageEncoder<RequestInfo> {
	
	Schema<RequestInfo> schema = RuntimeSchema.getSchema(RequestInfo.class);
	
	@Override
	protected void encode(ChannelHandlerContext ctx, RequestInfo msg, List<Object> out) throws Exception {
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] data = ProtostuffIOUtil.toByteArray(msg, schema, buffer);
        ByteBuf buf = Unpooled.copiedBuffer(data);//在写消息之前需要把消息的长度添加到投4个字节
        out.add(buf);
	}

}
