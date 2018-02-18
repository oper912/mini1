package com.bitstudy.free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.free.dao.FreeDAO;
import com.bitstudy.free.domain.Free;
@WebServlet("/jsp/free/update")
public class FreeUpdateController extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String content=request.getParameter("content");
		
		String no=request.getParameter("update");
		System.out.println("update no::"+no);
		Free vo=new Free();
		vo.setContent(content);
		vo.setNo(Integer.parseInt(no));
		FreeDAO dao=new FreeDAO();
		dao.update(vo);
	
		response.sendRedirect("/bitstudy/jsp/free/list");
		
	}
}