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

@WebServlet("/jsp/library/librarydelete")
public class LibraryDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryDAO dao = new LibraryDAO();
		int no = Integer.parseInt(request.getParameter("libNo"));
		List<Library> list = dao.selectLibraryByNoFile(no);
		dao.deleteLibrary(no);
		
		dao.deleteLibraryFile(list);
		
		request.setAttribute("msg", no+"번 글이 삭제되었습니다.");
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/library/librarylist");
		rd.forward(request, response);
	}
}
