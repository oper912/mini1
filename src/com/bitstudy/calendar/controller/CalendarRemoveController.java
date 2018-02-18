package com.bitstudy.calendar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitstudy.calendar.dao.CalendarDAO;
import com.bitstudy.calendar.domain.Calendar;

@WebServlet("/jsp/calendar/delete")
public class CalendarRemoveController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Calendar calendar = new Calendar();
		int calNo = Integer.parseInt(request.getParameter("calNo"));
		
		
		calendar.setCalNo(calNo);
		CalendarDAO dao = new CalendarDAO();
		dao.deleteCalendar(calendar);
		

		
	}

}
