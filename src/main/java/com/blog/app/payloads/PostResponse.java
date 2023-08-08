package com.blog.app.payloads;

import java.util.List;

public class PostResponse {
private List<Postdto> content;
private int pageNumber;
private int pageSize;
private long totalElements;
private int totalPages;
private boolean  lastPages;
public List<Postdto> getContent() {
	return content;
}
public void setContent(List<Postdto> content) {
	this.content = content;
}
public int getPageNumber() {
	return pageNumber;
}
public void setPageNumber(int pageNumber) {
	this.pageNumber = pageNumber;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public long getTotalElements() {
	return totalElements;
}
public void setTotalElements(long l) {
	this.totalElements = l;
}
public int getTotalPages() {
	return totalPages;
}
public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}
public boolean isLastPages() {
	return lastPages;
}
public void setLastPages(boolean lastPages) {
	this.lastPages = lastPages;
}
public PostResponse() {
	super();
	// TODO Auto-generated constructor stub
}

}
