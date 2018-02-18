package com.bitstudy.qna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.qna.dao.QnaDAO;
import com.bitstudy.qna.domain.Qna;

@WebServlet("/jsp/qna/detail")
public class QnaDetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
					
		int no = Integer.parseInt(request.getParameter("no").trim());
		QnaDAO dao = new QnaDAO();
		List<Qna> list = dao.selectQnaByNoA(no);
		int ls;
		if (list.size() > 0) {
			ls = list.size();
		}else {
			ls = 0;
		}
//		System.out.println("qdc ls : " + ls);
		Qna qna = new Qna();
		qna = dao.selectQnaByNo(no);
				
		dao.readCount(no);
		request.setAttribute("ls", ls);		
		request.setAttribute("qnal", qna);		
		request.setAttribute("list", list);		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/qna/qnaView.jsp");
		rd.forward(request, response);
	}
}