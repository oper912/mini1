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

@WebServlet("/jsp/member/pwreset")
public class MemberPwResetController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(request.getSession().getAttribute("user") == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		
		Member member = new Member();
		member.setEmail(request.getParameter("email"));
		member.setPassword(password);
		
		if(!password.equals(passwordCheck)) {
			member.setPassword("");
			request.setAttribute("error", "변경할 비밀번호가 일치하지 않습니다.");
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordReset.jsp");
			rd.forward(request, response);
			return ;
		}
		
		SearchMember search = new SearchMember();
		search.setOpt(request.getParameter("opt"));
		search.setMember(member);
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.updateMember(search);
		
		if(cnt == 1) {
			response.sendRedirect(request.getContextPath()+"/jsp/home/main");
		} else {
			request.setAttribute("error", "비밀번호 재설정 중 오류가 발생하였습니다.");
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordReset.jsp");
			rd.forward(request, response);
		}
	}
}