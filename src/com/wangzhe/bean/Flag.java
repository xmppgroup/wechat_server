package com.wangzhe.bean;

public enum Flag {
	VALID(0), INVALID(-1);
	
	private int value;
	
	private Flag(int value) {
		this.value = value;
	}
	
	public int value(){
		return value;
	}
}
