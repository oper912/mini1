package com.bitstudy.calendar.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.calendar.dao.CalendarDAO;
import com.bitstudy.calendar.domain.Calendar;
import com.google.gson.Gson;

@WebServlet("/jsp/calendar/calendar")
public class CalendarViewController extends HttpServlet {

		
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calendar calendar = new Calendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
		
		
	//	String startDate = request.getParameter("start	Date");
	//	System.out.println("startDate: "+startDate);
		//calendar.setStartDate(startDate);
		
		
		
			
		calendar.setStartDate(sf.format(new Date()));		//여기에 YYYY-MM 값을 입력 받자	. startDate를 yyyy-MM을 한다.
															
		//System.out.println(calendar.getStartDate()); //현재 날이 찍힘.
		
		CalendarDAO dao = new CalendarDAO();
		List<Calendar> list = dao.selectCalendar(calendar);
	
		request.setAttribute("list", new Gson().toJson(list));
		//System.out.println("이얍"+new Gson().toJson(list)); //데이터 잘 탔는지

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/calendar/calendar.jsp");
		rd.forward(request, response);
	}
}
