package com.dwarf.netty.bean;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SimpleType {
	
	@Protobuf(fieldType = FieldType.STRING, order = 1, required = true)
    public String name;

    @Protobuf(fieldType = FieldType.INT32, order = 2, required = false)
    public int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
