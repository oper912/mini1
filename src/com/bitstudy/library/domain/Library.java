package com.bitstudy.library.domain;

import java.util.Date;

public class Library {
	private int libNo;     // 자료실 글번호
	private String title;  // 글제목
	private String content;  // 글내용
	private Date regDate;  // 작성날짜
	private int readCount;  // 조회수
	private int recommentCount;  // 추천수
	private String id;
	private String name; // member 테이블에서 id랑 조인해서 가져올 이름
	
	private int fileNo;    // 자료실 글번호랑 같은 파일테이블 번호
	private int no;        // 파일테이블 번호
	private String filePath; // 파일이 저장된 경로
	private String fileOrgName; // 파일의 진짜 이름
	private String fileSystemName; // 파일이 서버에 저장된 이름
	private long fileSize; // 파일의 사이즈 (byte 단위)
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getLibNo() {
		return libNo;
	}
	public void setLibNo(int libNo) {
		this.libNo = libNo;
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
	public int getRecommentCount() {
		return recommentCount;
	}
	public void setRecommentCount(int recommentCount) {
		this.recommentCount = recommentCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileOrgName() {
		return fileOrgName;
	}
	public void setFileOrgName(String fileOrgName) {
		this.fileOrgName = fileOrgName;
	}
	public String getFileSystemName() {
		return fileSystemName;
	}
	public void setFileSystemName(String fileSystemName) {
		this.fileSystemName = fileSystemName;
	}
}
