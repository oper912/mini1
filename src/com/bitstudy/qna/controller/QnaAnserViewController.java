package com.bitstudy.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.qna.dao.QnaDAO;
import com.bitstudy.qna.domain.*;

@WebServlet("/jsp/qna/anserview")
public class QnaAnserViewController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no").trim());
		QnaDAO dao = new QnaDAO();
		Qna qna = dao.selectQnaByNo(no);
		if(qna == null)return;
		
		request.setAttribute("qna", qna);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/qna/qnaAnser.jsp");
		rd.forward(request, response);
		
	}
}