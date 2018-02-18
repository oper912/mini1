package com.bitstudy.free.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.free.dao.FreeDAO;
import com.bitstudy.free.domain.Free;

@WebServlet("/com/bitstudy/free/controller/free")
public class FreeServlet extends HttpServlet{
	FreeDAO dao = new FreeDAO();
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
	reqest.setCharacterEncoding("utf-8");
	String content=reqest.getParameter("content");
	//String no=reqest.getParameter("no");
	//String id=reqest.getParameter("id");
	Free vo=new Free();
	vo.setContent(content);
	//vo.setGroupNo(Integer.parseInt(no));
	//vo.setId(id);
	
	dao.insert(vo);
	System.out.println("호출됨");
	}
}
