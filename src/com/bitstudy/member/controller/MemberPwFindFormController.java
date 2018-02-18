package com.bitstudy.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.member.domain.Member;

@WebServlet("/jsp/member/pwfindform")
public class MemberPwFindFormController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.sendRedirect(request.getContextPath()+"/jsp/member/passwordFind.jsp");
	}	
}