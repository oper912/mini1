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

@WebServlet("/jsp/member/pwhintcheck")
public class MemberPwHintCheckController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		member.setEmail(request.getParameter("email"));
		member.setPwHintQuestion(request.getParameter("pwHintQuestion"));
		member.setPwHintAnswer(request.getParameter("pwHintAnswer"));
		
		SearchMember search = new SearchMember();
		search.setOpt(request.getParameter("opt"));
		search.setMember(member);
		
		MemberDAO dao = new MemberDAO();
		if(!dao.selectMemberCheck(search)) {
			member.setPwHintAnswer("");
			request.setAttribute("error", "답변이 일치하지 않습니다.");
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordHint.jsp");
			rd.forward(request, response);
			return ;
		}
		
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordReset.jsp");
		rd.forward(request, response);
	}
}
