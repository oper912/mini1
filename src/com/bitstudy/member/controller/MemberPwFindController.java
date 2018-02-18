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

@WebServlet("/jsp/member/pwfind")
public class MemberPwFindController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setPhoneNo(request.getParameter("phoneNo"));
		
		SearchMember search = new SearchMember();
		search.setOpt(request.getParameter("opt"));
		search.setMember(member);
		
		MemberDAO dao = new MemberDAO();
		member = dao.selectMember(search);
		if(member == null) {
			request.setAttribute("error", "입력하신 정보에 해당하는 회원이 존재하지 않습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordFind.jsp");
			rd.forward(request, response);
			return ;
		}
		
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordHint.jsp");
		rd.forward(request, response);
	}
}
