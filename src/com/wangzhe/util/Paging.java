package com.wangzhe.util;

import java.util.List;
public class Paging<T> {
	private int nowPage;
	private int upPage;
	private int downPage;
	private int allPage;
	private int pageSize;
	private int allCount;
	private Object obj;
	private List<T> list;
	private String primarykey;
	private String tbName;
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getUpPage() {
		return upPage;
	}
	public void setUpPage(int upPage) {
		this.upPage = upPage;
	}
	public int getDownPage() {
		return downPage;
	}
	public void setDownPage(int downPage) {
		this.downPage = downPage;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getPrimarykey() {
		return primarykey;
	}
	public void setPrimarykey(String primarykey) {
		this.primarykey = primarykey;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}
	public void countUpPage(){
		if(nowPage<=1){
			nowPage=1;
			upPage=1;
		}else
			upPage=nowPage-1;
	}
	public void countAllPage(){
		if(allCount%pageSize==0)
			allPage=allCount/pageSize;
		else
			allPage=allCount/pageSize+1;
	}
	public void countDownPage(){
		if(nowPage>=allPage){
			nowPage=allPage;
			downPage=allPage;
		}else
			downPage=nowPage+1;
	}
	public void init(){
		countUpPage();
		countAllPage();
		countDownPage();
	}
}
