package com.AllInOne.AllInOne.entities;

import java.util.List;

public class AccountResponseClass {
	
	private int pageNum;
	private int paseSize;
	private int totalNumberOfPages;
	private int totalNumberOfElements;
	private String hasNext;
	private List<Account1204> list;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPaseSize() {
		return paseSize;
	}
	public void setPaseSize(int paseSize) {
		this.paseSize = paseSize;
	}
	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}
	public void setTotalNumberOfPages(int totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}
	public int getTotalNumberOfElements() {
		return totalNumberOfElements;
	}
	public void setTotalNumberOfElements(int totalNumberOfElements) {
		this.totalNumberOfElements = totalNumberOfElements;
	}
	public String getHasNext() {
		return hasNext;
	}
	public void setHasNext(String hasNext) {
		this.hasNext = hasNext;
	}
	public List<Account1204> getList() {
		return list;
	}
	public void setList(List<Account1204> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "AccountResponseClass [pageNum=" + pageNum + ", paseSize=" + paseSize + ", totalNumberOfPages="
				+ totalNumberOfPages + ", totalNumberOfElements=" + totalNumberOfElements + ", hasNext=" + hasNext
				+ ", list=" + list + "]";
	}
	
	
}
