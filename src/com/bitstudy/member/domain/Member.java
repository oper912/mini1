package com.bitstudy.member.domain;

import java.util.Date;

public class Member {
	private String id;
	private String email;
	private String password;
	private String name;
	private String phoneNo;
	private String pwHintQuestion;
	private String pwHintAnswer;
	private Date joinDate;
	private Date dropDate;
	private boolean admin;
	private String image;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPwHintQuestion() {
		return pwHintQuestion;
	}
	public void setPwHintQuestion(String pwHintQuestion) {
		this.pwHintQuestion = pwHintQuestion;
	}
	public String getPwHintAnswer() {
		return pwHintAnswer;
	}
	public void setPwHintAnswer(String pwHintAnswer) {
		this.pwHintAnswer = pwHintAnswer;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getDropDate() {
		return dropDate;
	}
	public void setDropDate(Date dropDate) {
		this.dropDate = dropDate;
	}
	public boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
