package com.bitstudy.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.free.dao.FreeDAO;
@WebServlet("/jsp/free/commentdelect")
public class FreeCommentDeleteController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String no=request.getParameter("delete");
		FreeDAO dao=new FreeDAO();
		dao.Commentdelete(Integer.parseInt(no));
		response.sendRedirect("/bitstudy/jsp/free/list");
	}
}