package com.bitstudy.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.member.domain.Member;
import com.bitstudy.notice.dao.NoticeDAO;
import com.bitstudy.notice.domain.Notice;

@WebServlet("/jsp/notice/save")
public class NoticeSaveController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		Notice notice = new Notice();
		notice.setId(user.getId());
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		
		NoticeDAO dao = new NoticeDAO();
		int cnt = dao.insertNotice(notice);
		
		if(cnt == 1)response.sendRedirect(request.getContextPath()+"/jsp/notice/list");
	}
}
