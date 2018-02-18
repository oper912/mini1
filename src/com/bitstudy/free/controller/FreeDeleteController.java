package com.bitstudy.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.free.dao.FreeDAO;
@WebServlet("/jsp/free/delect")
public class FreeDeleteController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String groupNo=request.getParameter("delete");
		FreeDAO dao=new FreeDAO();
		dao.delete(Integer.parseInt(groupNo));
		response.sendRedirect("/bitstudy/jsp/free/list");
	}
}