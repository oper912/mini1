package com.bitstudy.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.qna.dao.QnaDAO;
import com.bitstudy.qna.domain.*;

@WebServlet("/jsp/qna/modify")
public class QnaModifyController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		System.out.println("content : "+ request.getParameter("content"));
		Qna qna = new Qna();
		qna.setTitle(request.getParameter("title"));
		qna.setContent(request.getParameter("content"));
		qna.setGroupNo(Integer.parseInt(request.getParameter("groupNo")));
		qna.setNo(Integer.parseInt(request.getParameter("no")));
		
		
		QnaDAO dao = new QnaDAO();
		int cnt = dao.updateQna(qna);
		
		response.sendRedirect("/bitstudy/jsp/qna/list");
	}
}