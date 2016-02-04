package com.dwarf.netty.guide.protostuff;

import org.junit.Test;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtostuffTest {
	
	@Test
	public void test(){
		Schema<RequestInfo> infoSchema = RuntimeSchema.getSchema(RequestInfo.class);
		RequestInfo info = new RequestInfo();
		info.subReqID = 1;
		info.userName = "u1";
		info.productName = "p1";
		info.address = "a1";
		
		LinkedBuffer buffer = LinkedBuffer.allocate(1024);  
        byte[] data = ProtobufIOUtil.toByteArray(info, infoSchema, buffer);
        System.out.println(data);
        
        RequestInfo unInfo = new RequestInfo();
        ProtobufIOUtil.mergeFrom(data, unInfo, infoSchema);
        System.out.println(unInfo);
	}
	
}
