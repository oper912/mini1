package com.bitstudy.common.domain;

public class Paging {
	private int totalDoc;		//전체 문서수
	private int totalPage;		//전체 페이지수
	private int dspDocCount;	//화면에 표시할 문서수
	private int dspPageCount;	//화면에 표시할 페이지수
	private int groupEndPage;	//네비 마지막 번호
	private int groupCurPage;	//네비 현재 번호
	private int startPage;		//네비표시기준 시작 페이지
	private int endPage;		//네비표시기준 종료 페이지
	private int curPage;		//네비표시기준 현재 페이지
	
	public int getTotalDoc() {
		return totalDoc;
	}
	public void setTotalDoc(int totalDoc) {
		this.totalDoc = totalDoc;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getDspDocCount() {
		return dspDocCount;
	}
	public void setDspDocCount(int dspDocCount) {
		this.dspDocCount = dspDocCount;
	}
	public int curPage() {
		return dspPageCount;
	}
	public void setDspPageCount(int dspPageCount) {
		this.dspPageCount = dspPageCount;
	}
	public int getGroupEndPage() {
		return groupEndPage;
	}
	public void setGroupEndPage(int groupEndPage) {
		this.groupEndPage = groupEndPage;
	}
	public int getGroupCurPage() {
		return groupCurPage;
	}
	public void setGroupCurPage(int groupCurPage) {
		this.groupCurPage = groupCurPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}