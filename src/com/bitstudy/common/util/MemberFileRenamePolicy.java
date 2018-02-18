package com.bitstudy.common.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MemberFileRenamePolicy implements FileRenamePolicy{
	private String newFileName;
	
	public MemberFileRenamePolicy(String newFileName) {
		this.newFileName = newFileName;	//사용자 아이디 값을 받음
	}
	@Override
	public File rename(File f) {
		String parent = f.getParent();
		String fname = f.getName();
		String ext = "";
		int index = fname.lastIndexOf(".");
		if (index != -1) {
			ext = fname.substring(index);
		}
		return new File(parent, newFileName + ext);//사용자의 아이디.확장자로 파일 저장
	}
}
