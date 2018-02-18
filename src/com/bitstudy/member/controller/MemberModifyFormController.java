package com.bitstudy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitstudy.member.dao.MemberDAO;
import com.bitstudy.member.domain.Member;
import com.bitstudy.member.domain.SearchMember;

@WebServlet("/jsp/member/modifyform")
public class MemberModifyFormController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		Member member = new Member();
		member.setId(user.getId());
		
		SearchMember search = new SearchMember();
		search.setOpt("modinfo");
		search.setMember(member);
		
		MemberDAO dao = new MemberDAO();
		member = dao.selectMember(search);
		request.setAttribute("member", member);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/infoModify.jsp");
		rd.forward(request, response);
	}
}
