package com.bitstudy.home.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitstudy.home.dao.HomeDAO;
import com.bitstudy.member.dao.MemberDAO;
import com.bitstudy.member.domain.Member;
import com.bitstudy.member.domain.SearchMember;

@WebServlet("/jsp/home/main")
public class HomeMainController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 상태 유지 쿠키가 있을 경우 로그인 정보 세팅
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c: cookies) {
				if("AUTH".equals(c.getName())){
					Member member = new Member();
					member.setId(c.getValue());
					
					SearchMember search = new SearchMember();
					search.setOpt("loginkeep");
					search.setMember(member);
					
					MemberDAO mdao = new MemberDAO();
					member = mdao.selectMember(search);
					
					HttpSession session = request.getSession();
					session.setAttribute("user", member);
				}
			}
		}
		
		//공지사항, 일정, Q&A, 자료실 목록 데이터 가져오기
		HomeDAO dao = new HomeDAO();
		List<Object> noticeList = dao.selectList("notice");		
		List<Object> calendarList = dao.selectList("calendar");
		List<Object> qnaList = dao.selectList("qna");
		List<Object> libraryList = dao.selectList("library");
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("calendarList", calendarList);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("libraryList", libraryList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/home/main.jsp");
		rd.forward(request, response);
	}
}