package com.bitstudy.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.qna.dao.QnaDAO;
import com.bitstudy.qna.domain.*;

@WebServlet("/jsp/qna/anser")
public class QnaAnserController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Qna qna = new Qna();
		qna.setGroupNo(Integer.parseInt(request.getParameter("groupNo")));
		qna.setNo(Integer.parseInt(request.getParameter("no")));
		qna.setName(request.getParameter("name"));
		qna.setId(request.getParameter("id"));
		qna.setTitle("답변 : " + request.getParameter("title"));
		qna.setContent(request.getParameter("content"));

		
		
		QnaDAO dao = new QnaDAO();
		int cnt = dao.insertA(qna);
		
//		response.sendRedirect("/bitstudy/jsp/qna/list");
		response.sendRedirect("/bitstudy/jsp/qna/detail?no=" + qna.getNo());
		
	}
}