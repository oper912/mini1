package com.bitstudy.library.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.library.dao.LibraryDAO;
import com.bitstudy.library.domain.Library;

@WebServlet("/jsp/library/libraryupdateapply")
public class LibraryUpdateApplyController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		LibraryDAO dao = new LibraryDAO();
		Library library = new Library();
//		List<Library> list = new ArrayList<>();
		
		library.setTitle(request.getParameter("title"));
		library.setContent(request.getParameter("content"));
		int no = Integer.parseInt(request.getParameter("libNo"));
		library.setLibNo(no);
		dao.updateLibrary(library);
		
//		list = dao.selectLibraryByNoFile(no);
//		dao.updateLibraryFile(list);
		
		response.sendRedirect("/bitstudy/jsp/library/detail?no="+no);
	}
}
