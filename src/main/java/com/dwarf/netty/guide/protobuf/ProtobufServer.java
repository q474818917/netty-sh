package com.dwarf.netty.guide.protobuf;

import com.dwarf.netty.guide.discard.DiscardServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtobufServer {
	
	private int port;
	
	public ProtobufServer(int port){
		this.port = port;
	}
	
	public void run(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
        	ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     public void initChannel(SocketChannel ch) throws Exception {
                         ch.pipeline()
                         	.addLast(new ProtobufVarint32FrameDecoder())
                         	.addLast(new ProtobufDecoder(  
                                    SubscribeReqProto.SubscribeReq.getDefaultInstance()))
                         	.addLast(new ProtobufVarint32LengthFieldPrepender())
                         	.addLast(new ProtobufEncoder())
                         	.addLast(new ProtobufServerHandler());
                     }
             })
             .option(ChannelOption.SO_BACKLOG, 1024);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
        
	}
	
	
	public static void main(String[] args) {
		int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8089;
        }
        new ProtobufServer(port).run();
	}

}
