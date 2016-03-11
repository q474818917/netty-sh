package com.dwarf.netty;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		while(true){
			System.out.println("before sleep 15" + System.currentTimeMillis());
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("after sleep 15" + System.currentTimeMillis());
		}
	}
}
