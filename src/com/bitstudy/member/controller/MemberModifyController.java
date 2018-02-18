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

@WebServlet("/jsp/member/modify")
public class MemberModifyController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		String curPassword = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		
		Member member = new Member();
		member.setId(user.getId());
		member.setPassword(curPassword);
		member.setName(request.getParameter("name"));
		member.setPhoneNo(request.getParameter("phoneNo"));
		member.setPwHintQuestion(request.getParameter("pwHintQuestion"));
		member.setPwHintAnswer(request.getParameter("pwHintAnswer"));
		
		SearchMember search = new SearchMember();
		search.setOpt(request.getParameter("opt"));
		search.setMember(member);
		
		MemberDAO dao = new MemberDAO();
		if(!dao.selectMemberCheck(search)) {
			member.setPassword(newPassword);
			request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/passwordCheck.jsp");
			rd.forward(request, response);
			return ;
		}
		
		// 비밀번호 변경을 안할 경우 기존 비밀번호 세팅
		member.setPassword(newPassword.equals("")?curPassword:newPassword);	
		dao = new MemberDAO();
		search.setMember(member);
		
		int cnt = dao.updateMember(search);
		if(cnt == 1) {
			response.sendRedirect(request.getContextPath()+"/jsp/home/main");
		} else {
			request.setAttribute("error", "정보 수정 중 오류가 발생하였습니다.");
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/infoModify.jsp");
			rd.forward(request, response);
		}
	}
}