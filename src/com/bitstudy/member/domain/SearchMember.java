package com.bitstudy.member.domain;

public class SearchMember {
	private String opt;
	private Member member;
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}