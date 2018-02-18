package com.bitstudy.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.qna.dao.QnaDAO;
import com.bitstudy.qna.domain.Qna;

@WebServlet("/jsp/qna/editor")
public class QnaWriteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Qna qna = new Qna();
		qna.setTitle(request.getParameter("title"));
		qna.setName(request.getParameter("name"));
		qna.setId(request.getParameter("id"));
		qna.setContent(request.getParameter("content"));
		
//		System.out.println(qna.getTitle());
//		System.out.println(qna.getGroupNo());
//		System.out.println(qna.getName());
//		System.out.println(qna.getId());
//		System.out.println(qna.getContent());
		int cnt = 0;

		QnaDAO dao = new QnaDAO();
//		if (qna.getGroupNo() == 1) {
			 cnt = dao.insertQ(qna);
//		}else {
//			
//			cnt = dao.insertA(qna);
//		}
		if(cnt == 1)response.sendRedirect("/bitstudy/jsp/qna/list");
		
	}
}
