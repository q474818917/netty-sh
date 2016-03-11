package com.dwarf.protocol;

import java.io.Serializable;

public abstract class BaseMsg implements Serializable{

	private static final long serialVersionUID = 7273303848498984864L;
	
	private MsgType msgType;
	
	public BaseMsg(){
		
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
	
}
