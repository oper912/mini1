package com.bitstudy.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitstudy.member.domain.Member;

@WebServlet("/jsp/library/libraryinsertcall")
public class LibraryInsertCallController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("user")!=null) {
			response.sendRedirect(request.getContextPath()+"/jsp/library/libraryinsert.jsp");
		} else {
			request.setAttribute("inserterror", "로그인을 하셔야 등록할 수 있습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/loginform");
			rd.forward(request, response);
		}
	}	
}
