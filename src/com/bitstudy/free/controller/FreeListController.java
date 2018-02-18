package com.bitstudy.free.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import com.bitstudy.free.dao.FreeDAO;
import com.bitstudy.free.domain.Free;

@WebServlet("/jsp/free/list")
public class FreeListController extends HttpServlet {
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	FreeDAO dao=new FreeDAO();
	List<Free> list = dao.select();
	request.setAttribute("list", list);
	RequestDispatcher rd=request.getRequestDispatcher("/jsp/free/free.jsp");
	rd.forward(request, response);
	
}
}