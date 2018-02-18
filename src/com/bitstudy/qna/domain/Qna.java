package com.bitstudy.qna.domain;

import java.util.Date;

public class Qna {

	int no ;
	String title;
	String content;
	String name;
	Date regDate = new Date();
	int readCount = 0;
	int groupNo;
	String id;

	public int getGroupNo() {
		return groupNo;
	}
	
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
