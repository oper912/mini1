package com.bitstudy.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jsp/member/logout")
public class MemberLogoutController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if("AUTH".equals(c.getName())) {
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/jsp/home/main");
	}
}