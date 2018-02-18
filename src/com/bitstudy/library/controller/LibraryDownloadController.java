package com.bitstudy.library.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	다운로드 기능을 구현하기 위한 방식
 * 		- 다운로드 할 파일을 자바의 IO 기능을 이용해서 읽어들인 후
 * 		    응답 객체를 통해서 사용자 브라우져로 출력한다.
 */
@WebServlet("/jsp/library/librarydownload")
public class LibraryDownloadController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일을 읽고 사용자 다운로드 이름을 결정하기 위한 필수 파라미터
		// path : 다운로드할 파일의 서버경로
		// sname : 서버에 실제 저장된 파일명
		// dname : 다운로드할 파일명
		
		String path = request.getParameter("path");
		String sname = request.getParameter("sname");
		String dname = request.getParameter("dname");
		
		String upload = "C:/java97/server-work/wtpwebapps/bitstudy/upload/library";
		File f = new File(upload+path, sname); // upload+path 경로의 sname 파일을 읽겠다
		
		// 4개의 header 설정
		// 파일 다운로드 하기
		response.setHeader("Content-Type", "application/octet-stream"); //
		// 다운로드할 파일 이름 설정
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(dname.getBytes("utf-8"), "8859_1")); // 다운로드 할 이름을 한글로 그냥보내면 깨지게 된다
		// 다운로드 데이터 타입 설정
		response.setHeader("Content-Tranfer-Encoding", "binary");
		// 다운로드할 파일의 사이즈 설정
		response.setHeader("Content-Length", String.valueOf(f.length())); // f.length()는 반환타입이 long 형이기 때문에 문자로 바꿔줌
		
		// 파일을 읽기 위한 객체
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
				
		// 출력하기 위한 객체
		OutputStream out = response.getOutputStream(); // 사용자가 요청한 파일을 출력하기 위한 객체 (파일을 읽어들인 후 내보내기 위해선 byte 단위여야함)
		BufferedOutputStream bos = new BufferedOutputStream(out);
//		out.write(파일의 내용..);
		
		while(true) {
			int ch = bis.read();
			if (ch == -1) break;
			
			bos.write(ch);
		}
		
		bos.close();
		out.close();
		
		
	}
}
