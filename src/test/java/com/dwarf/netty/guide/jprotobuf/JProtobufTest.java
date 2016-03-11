package com.dwarf.netty.guide.jprotobuf;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dwarf.netty.bean.SimpleType;
import com.dwarf.netty.guide.protobuf.SubscribeReqProto;

public class JProtobufTest {
	
SubscribeReqProto.SubscribeReq subscribeReq;
	
	@Before
	public void init(){
	}
	
	@Test
	public void test(){
		Codec<SimpleType> simpleTypeCodec = ProtobufProxy.create(SimpleType.class);

		SimpleType stt = new SimpleType();
        stt.name = "abc";
        stt.setValue(100);
        try {
            // 序列化
            byte[] bb = simpleTypeCodec.encode(stt);
            // 反序列化
            SimpleType newStt = simpleTypeCodec.decode(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
