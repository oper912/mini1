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

@WebServlet("/jsp/member/drop")
public class MemberDropController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.dropMember(user.getId());
		
		if(cnt != 1) {
			request.setAttribute("error", "탈퇴 작업중 오류가 발생하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/home/main");
			rd.forward(request, response);
		}
		response.sendRedirect(request.getContextPath()+"/jsp/home/main");
	}
}