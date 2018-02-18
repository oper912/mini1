package com.bitstudy.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.library.dao.LibraryDAO;

@WebServlet("/jsp/library/recommend")
public class LibraryRecommendController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("libNo"));
		String id = request.getParameter("reid");
		
		boolean chk = new LibraryDAO().recommendLibrary(no, id);
		
		if(chk==false) {
			request.setAttribute("recommend", "이미 추천하였습니다");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/library/detail?no="+no);
		rd.forward(request, response);
	}
}