package com.bitstudy.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.bitstudy.common.domain.Paging;
import com.bitstudy.common.domain.Search;
import com.bitstudy.notice.dao.NoticeDAO;
import com.bitstudy.notice.domain.Notice;

@WebServlet("/jsp/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		String count = request.getParameter("count");
		String pageNo = request.getParameter("curpage");
		String opt = request.getParameter("opt");
		String groupNo = request.getParameter("gpage");
		
		//==========paging 관련==========
		int dspPageCount = 5;
		int dspDocCount = count==null || count.equals("")?10:Integer.parseInt(count);
		int curPage = pageNo==null || pageNo.equals("")?1:Integer.parseInt(pageNo);
		int groupPage = groupNo==null || groupNo.equals("")?1:Integer.parseInt(groupNo);
		//==========paging 관련==========
		
		Search search = new Search();
		search.setOpt(opt==null || opt.equals("")?"basic":opt);
		search.setKeyword(request.getParameter("keyword"));
		search.setCount(dspDocCount);
		search.setPageNo(curPage);
		
		NoticeDAO dao = new NoticeDAO();
		int totalCount = dao.selectNoticeCount(search);	//전체문서수 가져오기
		List<Notice> list = dao.selectNotice(search);	//검색조건에 해당하는 게시글 목록 가져오기
		
		//==========paging 관련==========
		int tmpEndPage = dspPageCount * groupPage;
		int totalPage = (totalCount / dspDocCount) + (totalCount % dspDocCount == 0 ?0:1);
		
		Paging paging = new Paging();
		paging.setCurPage(curPage);			//네비표시기준 현재 페이지
		paging.setTotalDoc(totalCount);		//전체 문서수
		paging.setDspDocCount(dspDocCount);	//화면에 표시할 문서수
		paging.setDspPageCount(dspPageCount);//화면에 표시할 페이지수
		paging.setTotalPage(totalPage);		//전체 페이지수
		paging.setGroupEndPage((totalPage/dspPageCount) + (totalPage % dspPageCount == 0?0:1));//네비 마지막 번호
		paging.setGroupCurPage(groupPage);	//네비 현재 번호
		paging.setStartPage(tmpEndPage-dspPageCount+1);	//네비표시기준 시작 페이지
		paging.setEndPage(tmpEndPage<totalPage ? tmpEndPage : totalPage);//네비표시기준 종료 페이지
		//==========paging 관련==========
		
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
		request.setAttribute("search", search);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/notice/noticeList.jsp");
		rd.forward(request, response);
	}
}