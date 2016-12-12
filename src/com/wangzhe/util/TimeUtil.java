package com.wangzhe.util;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeUtil {
	private static final AtomicInteger ATOMIC_INTEGER
		= new AtomicInteger(100);
	
	public static long getTime(){
		int r = ATOMIC_INTEGER.getAndIncrement();
		if(r > 999){
			ATOMIC_INTEGER.set(100);
		}
		return System.currentTimeMillis() + r;
	}

}
