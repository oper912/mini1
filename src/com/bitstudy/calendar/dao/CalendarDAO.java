package com.bitstudy.calendar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bitstudy.calendar.domain.Calendar;
import com.bitstudy.common.util.ConnectionPool;
import com.bitstudy.common.util.JdbcUtil;


public class CalendarDAO {

	int no = 0;
	
	public List<Calendar> selectCalendar (Calendar cal) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ");
			sql.append("  from t97_calendar");
			sql.append(" where startDate between ? and '2999-12-31'");
			//여기에 현재 날짜 값이 옴. 따라서 2017-09로 검색함.
			
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1,cal.getStartDate());
			
			
			ResultSet rs = stmt.executeQuery();
			
			List<Calendar> list = new ArrayList<>();
			
				while(rs.next()) {
					Calendar calendar = new Calendar();
					
					calendar.setCalNo(rs.getInt("calNo"));
					calendar.setName(rs.getString("name"));
					calendar.setEmail(rs.getString("email"));
					calendar.setTitle(rs.getString("title"));
					calendar.setContent(rs.getString("content"));
					calendar.setLocation(rs.getString("location"));
					calendar.setStartDate(rs.getString("startDate"));
					calendar.setStartTime(rs.getString("startTime"));
					calendar.setEndTime(rs.getString("endTime"));
					calendar.setTextColor(rs.getString("textColor"));
					calendar.setBackColor(rs.getString("backColor"));
					calendar.setStart(rs.getString("startDate"));
					
					
					list.add(calendar);
				}
		
			
				return list;
			
		} catch (Exception e) {
			
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return null;
	}

	public void insertCalendar(Calendar cal) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert ");
			sql.append("  into t97_calendar(calNo, name, email, title, content, location, startDate, startTime, endTime, textColor, backColor) ");
			sql.append(" values (s97_calendar_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, cal.getName());
			stmt.setString(2, cal.getEmail());
			stmt.setString(3, cal.getTitle());
			stmt.setString(4, cal.getContent());
			stmt.setString(5, cal.getLocation());
			stmt.setString(6, cal.getStartDate());
			stmt.setString(7, cal.getStartTime());
			stmt.setString(8, cal.getEndTime());
			stmt.setString(9, cal.getTextColor());
			stmt.setString(10, cal.getBackColor());
			
			int cnt = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		
		
		
	}

	public void updateCalendar(Calendar cal) {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update t97_calendar ");
			sql.append("   set title = ?, content = ?, location = ?, startDate = ?, startTime = ?, endTime = ?, textColor = ?, backColor = ?");
			sql.append(" where calNo = ? ");

			
			stmt = con.prepareStatement(sql.toString());
			
			stmt.setString(1, cal.getTitle());
			stmt.setString(2, cal.getContent());
			stmt.setString(3, cal.getLocation());
			stmt.setString(4, cal.getStartDate());
			stmt.setString(5, cal.getStartTime());
			stmt.setString(6, cal.getEndTime());
			stmt.setString(7, cal.getTextColor());
			stmt.setString(8, cal.getBackColor());
			stmt.setInt(9,  cal.getCalNo());

			int cnt = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
	}


	
	
	
	
	
	
	
	
	
	public void deleteCalendar(Calendar cal) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("delete");
			sql.append("  from t97_calendar ");
			sql.append(" where calNo = ?");
			
			stmt = con.prepareStatement(sql.toString());
			
			stmt.setInt(1, cal.getCalNo());
			
			int cnt = stmt.executeUpdate();
			System.out.println("123");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		} 
	}
}
