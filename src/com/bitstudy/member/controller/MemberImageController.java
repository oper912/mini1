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
import com.bitstudy.member.domain.ProfileImage;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/jsp/member/image")
public class MemberImageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member user = (Member)request.getSession().getAttribute("user");
		if(user == null) {
			//세션 만료 시 로그인 페이지 호출
			response.sendRedirect(request.getContextPath()+"/jsp/member/loginform");
			return;
		}
		
		String path = "C:/java97/server-work/wtpwebapps/"+request.getContextPath()+"/profile";
		File f = new File(path);
		if(!f.exists())f.mkdirs();
		
		MultipartRequest mRequest = new MultipartRequest(
				request, 
				path, 			//업로드할 디렉토리 경로
				1024 * 1024 * 30,	//업로드 최대 사이즈 설정 : 1024 * 1024 * 30 => 30MB
				"utf-8",			//파라미터 인코딩 지정
				new MemberFileRenamePolicy(user.getId())//rename 메서드 자동호출
		); //request에 들어온 내용을 파싱하여 upload경로에 파일을 저장
		System.out.println("파일 업로드 성공");
		
		//저장할 데이터 추출
		String fileName = "attachImg";
		File file = mRequest.getFile(fileName);
		
		if(file != null) {
			ProfileImage image = new ProfileImage();
			image.setId(user.getId());
			image.setFileSize(file.length());
			image.setFileOrgName(mRequest.getOriginalFileName(fileName));
			image.setFileSystemName(mRequest.getFilesystemName(fileName));
			
			MemberDAO dao = new MemberDAO();
			int cnt = dao.selectMemberImage(user.getId());
			System.out.println("cnt :: "+ cnt);
			if(cnt == 0) cnt = dao.insertMemberImage(image);
			else cnt = dao.updateMemberImage(image);
			
			if(cnt != 1) {
				request.setAttribute("error", "사진 업로드 중 오류가 발생하였습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/member/profileImg.jsp");
				rd.forward(request, response);
				return ;
			}
			user.setImage(mRequest.getFilesystemName(fileName));
			response.sendRedirect(request.getContextPath()+"/jsp/home/main");
		}
	}
}
