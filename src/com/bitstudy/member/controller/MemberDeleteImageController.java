package com.bitstudy.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.common.util.MemberFileRenamePolicy;
import com.bitstudy.member.dao.MemberDAO;
import com.bitstudy.member.domain.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/jsp/member/deleteimage")
public class MemberDeleteImageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		//디렉토리에 있는 프로필 사진 파일 삭제
		String path = "C:/java97/server-work/wtpwebapps/"+request.getContextPath()+"/profile";
		File f = new File(path+"/"+user.getImage());
		if(f.exists()) f.delete();
		
		//DB에 있는 프로필 사진 정보 삭제
		MemberDAO dao = new MemberDAO();
		int cnt = dao.deleteMemberImage(user.getId());
		if(cnt != 1) {
			request.setAttribute("error", "사진 삭제 중 오류가 발생하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/profileImg.jsp");
			rd.forward(request, response);
			return ;
		}
		user.setImage("");
		response.sendRedirect(request.getContextPath()+"/jsp/home/main");
	}
}
