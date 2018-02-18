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

@WebServlet("/jsp/notice/write")
public class NoticeWriteController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		String mode = request.getParameter("mode");
		if(mode.equals("mod")){
			int no = Integer.parseInt(request.getParameter("no"));
			NoticeDAO dao = new NoticeDAO();
			Notice notice = dao.selectNoticeByNo(no);
			
			request.setAttribute("notice", notice);
			request.setAttribute("no", no);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/notice/noticeModify.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/jsp/notice/noticeWrite.jsp");
		}
	}
}
