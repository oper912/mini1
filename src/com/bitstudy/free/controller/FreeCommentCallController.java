package com.bitstudy.free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jsp/free/commentcall")
public class FreeCommentCallController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		if(session.getAttribute("user")!=null) {
			request.setAttribute("content", request.getAttribute("content"));
			RequestDispatcher rd=request.getRequestDispatcher("/jsp/free/comment");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			}
		}
	
	
}