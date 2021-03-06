package com.dwarf.netty.guide.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtobufClient {
	
	private String host;
	private int port;
	
	public ProtobufClient(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public void connect(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(bossGroup)
			 .channel(NioSocketChannel.class)
			 .handler(new ChannelInitializer<SocketChannel>() {  
                 @Override  
                 protected void initChannel(SocketChannel ch) throws Exception {  
                     ch.pipeline()  
                     .addLast(new ProtobufVarint32FrameDecoder())                          
                     .addLast(new ProtobufDecoder(  
                             SubscribeRespProto.SubscribeResp.getDefaultInstance()))                       
                     .addLast(new ProtobufVarint32LengthFieldPrepender())                          
                     .addLast(new ProtobufEncoder())                       
                     .addLast(new ProtobufClientHandler());  
                 };  
             });
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String args[]){
		int port;String host;
        if (args.length > 1) {
            port = Integer.parseInt(args[0]);
            host = String.valueOf(args[1]);
        } else {
        	host = "127.0.0.1";
            port = 8089;
        }
        new ProtobufClient(host, port).connect();
	}
}
