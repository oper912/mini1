package com.bitstudy.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitstudy.qna.dao.QnaDAO;
import com.bitstudy.qna.domain.Qna;

@WebServlet("/jsp/qna/qdelete")
public class QnaQDeleteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
			// 전체글일때는 groupNo로 묶어서 삭제하기
		Qna qna = new Qna();
		int groupNo = Integer.parseInt(request.getParameter("no"));
//		int no = 0 ; 
		
		qna.setId(request.getParameter("id"));
		qna.setNo(0);
		qna.setGroupNo(groupNo);
		
		
		QnaDAO dao = new QnaDAO();
		boolean cnt = dao.delete(qna);
		
		
		response.sendRedirect("/bitstudy/jsp/qna/list");
	}
}
