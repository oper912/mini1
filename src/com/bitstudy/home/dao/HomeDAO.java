package com.bitstudy.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bitstudy.calendar.domain.Calendar;
import com.bitstudy.common.util.ConnectionPool;
import com.bitstudy.common.util.JdbcUtil;
import com.bitstudy.library.domain.Library;
import com.bitstudy.notice.domain.Notice;
import com.bitstudy.qna.domain.Qna;

public class HomeDAO {
	public List<Object> selectList(String boardName) {
		List<Object> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			ResultSet rs = null;

			switch(boardName.toLowerCase()) {
			case "notice":
				sql.append("select no, title, reg_date ");
				sql.append("  from t97_notice ");
				sql.append(" where rownum <=5 ");
				sql.append(" order by no desc");
				
				stmt = con.prepareStatement(sql.toString());
				rs = stmt.executeQuery();
				while(rs.next()) {
					Notice notice = new Notice();
					notice.setNo(rs.getInt("no"));
					notice.setTitle(rs.getString("title"));
					notice.setRegDate(rs.getDate("reg_date"));
					list.add(notice);
				}	
				break;
				
			case "calendar":
				sql.append("select c.cal_no, c.title, c.start_date, m.name ");
				sql.append("  from t97_calendar c ");
				sql.append(" inner join t97_member m ");
				sql.append("    on c.id = m.id ");
				sql.append(" where rownum <=5 ");
				sql.append(" order by c.start_date desc, c.start_time");
				
				stmt = con.prepareStatement(sql.toString());
				rs = stmt.executeQuery();
				while(rs.next()) {
					Calendar cal = new Calendar();
					cal.setCalNo(rs.getInt("cal_no"));
					cal.setTitle(rs.getString("title"));
					cal.setStartDate(rs.getString("start_date"));
					cal.setName(rs.getString("name"));
					list.add(cal);
				}
				break;
				
			case "qna":
				sql.append("select q.no, q.title, q.reg_date, m.name ");
				sql.append("  from t97_qna q");
				sql.append(" inner join t97_member m ");
				sql.append("    on q.id = m.id ");
				sql.append(" where rownum <=5 ");
				sql.append(" order by q.no desc");
				
				stmt = con.prepareStatement(sql.toString());
				rs = stmt.executeQuery();
				while(rs.next()) {
					Qna qna = new Qna();
					qna.setNo(rs.getInt("no"));
					qna.setTitle(rs.getString("title"));
					qna.setRegDate(rs.getDate("reg_date"));
					qna.setName(rs.getString("name"));
					list.add(qna);
				}
				break;
				
			case "library":
				sql.append("select l.lib_no, l.title, l.reg_date, m.name");
				sql.append("  from t97_library l ");
				sql.append(" inner join t97_member m ");
				sql.append("    on l.id = m.id ");
				sql.append(" where rownum <=5 ");
				sql.append(" order by l.lib_no desc");
				
				stmt = con.prepareStatement(sql.toString());
				rs = stmt.executeQuery();
				while(rs.next()) {
					Library lib = new Library();
					lib.setLibNo(rs.getInt("lib_no"));
					lib.setTitle(rs.getString("title"));
					lib.setRegDate(rs.getDate("reg_date"));
					lib.setName(rs.getString("name"));
					list.add(lib);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
}