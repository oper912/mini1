package com.bitstudy.library.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitstudy.common.util.LibraryFileRenamePolicy;
import com.bitstudy.library.dao.LibraryDAO;
import com.bitstudy.library.domain.Library;
import com.bitstudy.member.domain.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/jsp/library/libraryinsert")
public class LibraryInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Library library = new Library();
		
		String upload = "C:/java97/server-work/wtpwebapps/bitstudy/upload/library";
        String path = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		
        File f = new File(upload+path);
        if(!f.exists()) f.mkdirs();
        
        MultipartRequest mRequest = new MultipartRequest(request, upload+path, 1024*1024*30, "utf-8", new LibraryFileRenamePolicy());
        
        Enumeration<String> fNames = mRequest.getFileNames();

        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("user");
        String id = member.getId();
        String title = mRequest.getParameter("title");
        String content = mRequest.getParameter("content");
        
		library.setTitle(title);
		library.setId(id);
		library.setContent(content);
		
		List<Library> list = new ArrayList<>();
	    while(fNames.hasMoreElements()) {
	        String fileName = fNames.nextElement();
	        File file = mRequest.getFile(fileName);
	        	
	        if(file != null) {
	        	Library l = new Library();
	        	l.setFileSize(file.length());
	        	l.setFilePath(path);
	        	l.setFileOrgName(mRequest.getOriginalFileName(fileName));
	        	l.setFileSystemName(mRequest.getFilesystemName(fileName));
	        	
	        	list.add(l);
	    	}
	    }
		
		new LibraryDAO().insertLibrary(library, list);

		response.sendRedirect(request.getContextPath()+"/jsp/library/librarylist");
	}
}
