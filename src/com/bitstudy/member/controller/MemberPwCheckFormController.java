package com.bitstudy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitstudy.member.domain.Member;

@WebServlet("/jsp/member/pwcheckform")
public class MemberPwCheckFormController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(request.getSession().getAttribute("user") == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		String newPassword = request.getParameter("newPassword");
		String passwordCheck = request.getParameter("passwordCheck");
		
		Member member = new Member();
		member.setName(request.getParameter("name"));
		member.setPhoneNo(request.getParameter("phoneNo"));
		member.setPwHintQuestion(request.getParameter("pwHintQuestion"));
		member.setPwHintAnswer(request.getParameter("pwHintAnswer"));
		
		if(!newPassword.isEmpty() || !passwordCheck.isEmpty()) {
			if(!newPassword.equals(passwordCheck)) {
				request.setAttribute("error", "변경할 비밀번호가 일치하지 않습니다.");
				request.setAttribute("member", member);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/infoModify.jsp");
				rd.forward(request, response);
				return ;
			} else {
				member.setPassword(newPassword);
			}
		}
		
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordCheck.jsp");
		rd.forward(request, response);
	}
}
