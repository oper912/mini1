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

@WebServlet("/jsp/library/libraryupdate")
public class LibraryUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryDAO dao = new LibraryDAO();
		int no = Integer.parseInt(request.getParameter("libNo"));
		
		Library library = dao.selectLibraryByNo(no);
		List<Library> list = dao.selectLibraryByNoFile(no); 
		
		request.setAttribute("library", library);
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/library/libraryupdate.jsp");
		rd.forward(request, response);
	}
}
