package com.bitstudy.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitstudy.qna.dao.QnaDAO;
import com.bitstudy.qna.domain.Qna;

@WebServlet("/jsp/qna/adelete")
public class QnaADeleteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
//			답글일때는 no로 삭제
		Qna qna = new Qna();
		int no = Integer.parseInt(request.getParameter("no").trim());
		int groupNo = 0 ; 
		
		qna.setNo(no);
		qna.setGroupNo(groupNo);
		qna.setId("M0001"); // 테스트용
				
		System.out.println("a no : " + qna.getNo());
		
		QnaDAO dao = new QnaDAO();
		boolean cnt = dao.delete(qna);
//		System.out.println("a 삭제 : " + cnt);
		
		
		response.sendRedirect("/bitstudy/jsp/qna/detail?no=" + no);
	}
}
