package com.bitstudy.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.member.domain.Member;
import com.bitstudy.notice.dao.NoticeDAO;
import com.bitstudy.notice.domain.Notice;

@WebServlet("/jsp/notice/detail")
public class NoticeDetailController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		String popup = request.getParameter("popup");
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeDAO dao = new NoticeDAO();
		Notice notice = dao.selectNoticeByNo(no);
		if(notice == null) return ;
		int cnt = dao.updateReadCount(no);
		
		request.setAttribute("popup",(popup != null && !popup.equals("") && popup.equals("1"))?true:false);
		request.setAttribute("notice", notice);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/notice/noticeRead.jsp");
		rd.forward(request, response);
		
	}
}