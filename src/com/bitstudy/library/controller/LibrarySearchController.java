package com.bitstudy.library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.library.dao.LibraryDAO;
import com.bitstudy.library.domain.Library;

@WebServlet("/jsp/library/librarysearch")
public class LibrarySearchController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String opt = request.getParameter("opt");
		String keyWord = request.getParameter("keyword");
		
		if(keyWord.equals("") || keyWord == "") {
			request.setAttribute("error", "검색할 단어를 입력하세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/library/librarylist");
			rd.forward(request, response);
		} else {
			List<Library> list = new LibraryDAO().searchLibrary(opt, keyWord);
			
			int cnt = list.size();
			request.setAttribute("list", list);
			request.setAttribute("cnt", cnt/5+1);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/library/librarylist.jsp");
			rd.forward(request, response);
		}
	}
}
