package com.dwarf.netty.guide.protobuf;

import org.junit.Before;
import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufTest {
	
	SubscribeReqProto.SubscribeReq subscribeReq;
	
	@Before
	public void init(){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqID(1);
		builder.setUserName("t1");
		builder.setProductName("britax1");
		builder.addAddress("US bridge");
		subscribeReq = builder.build();
	}
	
	@Test
	public void test(){
		System.out.println(subscribeReq.toString());
		try {
			System.out.println(SubscribeReqProto.SubscribeReq.parseFrom(subscribeReq.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}
	
}
