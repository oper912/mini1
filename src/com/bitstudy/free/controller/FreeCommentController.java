package com.bitstudy.free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.INTERNAL;

import com.bitstudy.free.dao.FreeDAO;
import com.bitstudy.free.domain.Free;
import com.bitstudy.member.domain.Member;

@WebServlet("/jsp/free/comment")
public class FreeCommentController extends HttpServlet{
	FreeDAO dao = new FreeDAO();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	String content=request.getParameter("content");
	String group_no=request.getParameter("group_no");
	
	
	
	Free vo=new Free();

	vo.setContent(content);
	vo.setGroupNo(Integer.parseInt(group_no));
	HttpSession session = request.getSession();
	Member member = (Member)session.getAttribute("user");
	vo.setId(member.getId());
	
	dao.insertComment(vo);

	response.sendRedirect("/bitstudy/jsp/free/list");
	
	
	
	}
}
