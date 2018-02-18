package com.bitstudy.calendar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.calendar.dao.CalendarDAO;
import com.bitstudy.calendar.domain.Calendar;

@WebServlet("/html/calendar/update")
public class CalendarModiController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Calendar calendar = new Calendar();
		
		int calNo = Integer.parseInt(request.getParameter("calNo"));

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String location = request.getParameter("location");
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String textColor = request.getParameter("textColor");
		String backColor = request.getParameter("backColor");
		
	
		calendar.setCalNo(calNo);
		calendar.setTitle(title);
		calendar.setContent(content);
		calendar.setLocation(location);
		calendar.setStartDate(startDate);
		calendar.setStartTime(startTime);
		calendar.setEndTime(endTime);
		calendar.setTextColor(textColor);
		calendar.setBackColor(backColor);
		
		CalendarDAO dao = new CalendarDAO();
		dao.updateCalendar(calendar);
		
		
		
		
		
	}

}
