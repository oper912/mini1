package com.bitstudy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.member.dao.MemberDAO;
import com.bitstudy.member.domain.Member;
import com.bitstudy.member.domain.SearchMember;

@WebServlet("/jsp/member/signup")
public class MemberSignupController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");

		Member member = new Member();
		member.setEmail(request.getParameter("email"));
		member.setPassword(password);
		member.setName(request.getParameter("name"));
		member.setPhoneNo(request.getParameter("phoneNo"));
		member.setPwHintQuestion(request.getParameter("pwHintQuestion"));
		member.setPwHintAnswer(request.getParameter("pwHintAnswer"));
		
		//비밀번호, 비밀번호 확인 입력값 비교
		if(!password.equals(passwordCheck)) {
			request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/signup.jsp");
			rd.forward(request, response);
			return ;
		}
		
		SearchMember search = new SearchMember();
		search.setOpt(request.getParameter("opt"));
		search.setMember(member);
		
		MemberDAO dao = new MemberDAO();
		//이메일 중복체크
		if(dao.selectMemberCheck(search)) {
			request.setAttribute("error", "사용중인 이메일 입니다.");
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/signup.jsp");
			rd.forward(request, response);
			return ;
		}
		//가입정보 insert
		int cnt = dao.insertMember(member);
		
		response.sendRedirect(request.getContextPath()+"/jsp/home/main");
	}
}