package com.bitstudy.common.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class LibraryFileRenamePolicy implements FileRenamePolicy{
	@Override
	public File rename(File f) {
		// f = new File("c:/java/upload/Test.java"); // String parent = f.getParent();  하면 "c:/java/upload" 까지만 추출됨
		
		// 파일 경로 ex)c:/java/upload
		String parent = f.getParent();
		// 파일 이름 ex)Test.java
		String name = f.getName();
		
		// 확장자는 유지하고 파일의 이름부분을 변경
		// 사용자가 선택한 파일의 확장자만 가져오기
		String ext = "";
		int index = name.lastIndexOf(".");
		if (index != -1) {
			// 확장자명만 얻어오기 ext = ".java"
			ext = name.substring(index);
		}
		String fName = UUID.randomUUID().toString(); // 유니크한 이름을 하나 생성한다.
		return new File(parent, fName + ext);
	}
}
