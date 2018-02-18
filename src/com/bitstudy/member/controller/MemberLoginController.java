package com.bitstudy.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitstudy.member.dao.MemberDAO;
import com.bitstudy.member.domain.Member;
import com.bitstudy.member.domain.SearchMember;

@WebServlet("/jsp/member/login")
public class MemberLoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		member.setEmail(request.getParameter("email"));
		member.setPassword(request.getParameter("password"));
		
		SearchMember search = new SearchMember();
		search.setOpt(request.getParameter("opt"));
		search.setMember(member);
		
		MemberDAO dao = new MemberDAO();
		member = dao.selectMember(search);
		
		String path = "main";
		if(member == null) {
			path = "login";
			request.setAttribute("error", "이메일 또는 비밀번호가 올바르지 않습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/login.jsp");
			rd.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			if("remember".equals(request.getParameter("loginkeep"))) {
				Cookie cookie = new Cookie("AUTH",URLEncoder.encode(member.getId(),"utf-8"));
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24); //로그인 상태 유지 선택시  - 7일간 상태 유지
				response.addCookie(cookie);
			} else {
				session.setMaxInactiveInterval(60*30);
			}
			session.setAttribute("user", member);
			response.sendRedirect(request.getContextPath()+"/jsp/home/"+path);
		}
	}
}