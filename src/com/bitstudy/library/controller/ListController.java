package com.bitstudy.library.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.library.dao.LibraryDAO;
import com.bitstudy.library.domain.Library;

@WebServlet("/jsp/library/librarylist")
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryDAO dao = new LibraryDAO();
		List<Library> list = new ArrayList<>();
		
		String gpageNo = request.getParameter("paging");
		int pageNo = (gpageNo == null || gpageNo.equals("") ? 1 : Integer.parseInt(gpageNo));
		
		list = dao.selectLibrary(pageNo);
		int cnt = dao.totalCnt();
		
//		String msg = (String)request.getAttribute("error");
//		if(msg.equals(null) || msg != null) {
//			request.setAttribute("error", "검색할 단어를 입력하세요.");
//		}
		
		request.setAttribute("list", list);
		request.setAttribute("cnt", cnt/5+1);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/library/librarylist.jsp");
		rd.forward(request, response);
	}
}
