package com.bitstudy.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitstudy.common.domain.Search;
import com.bitstudy.common.util.ConnectionPool;
import com.bitstudy.common.util.JdbcUtil;
import com.bitstudy.notice.domain.Notice;

public class NoticeDAO {
	
	public List<Notice> selectNotice(Search search){
		List<Notice> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			String keyword = search.getKeyword();
			int count = search.getCount();
			int pageNo = search.getPageNo();
			int endNo = pageNo * count;
			int startNo = endNo - count + 1;
		
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select n.no, n.title, n.reg_date, n.read_count ");
			sql.append(" from (select rownum rnum, nr.* ");
			sql.append("         from (select * ");       
			sql.append("                 from t97_notice ");
			sql.append("                order by no desc) nr");
			
			switch(search.getOpt().toLowerCase()) {
			case "basic":
				sql.append("       ) n ");
				sql.append(" where n.rnum between ? and ?"); 
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(1, startNo);
				stmt.setInt(2, endNo);
				break;
				
			case "title":
				sql.append("        where nr.title like ? ");
				sql.append("       )n ");
				sql.append(" where n.rnum between ? and ?"); 
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				stmt.setInt(2, startNo);
				stmt.setInt(3, endNo);
				break;
				
			case "content":
				sql.append("       where nr.content like ? ");
				sql.append("       )n ");
				sql.append(" where n.rnum between ? and ?"); 
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				stmt.setInt(2, startNo);
				stmt.setInt(3, endNo);
				break;
				
			case "all":
				sql.append("       where (nr.title like ? ");
				sql.append("          or nr.content like ?) ");
				sql.append("       )n ");
				sql.append(" where n.rnum between ? and ?"); 
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				stmt.setString(2, "%"+keyword+"%");
				stmt.setInt(3, startNo);
				stmt.setInt(4, endNo);
				
			}
			if(stmt != null) {
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Notice notice = new Notice();
					notice.setNo(rs.getInt("no"));
					notice.setTitle(rs.getString("title"));
					notice.setRegDate(rs.getDate("reg_date"));
					notice.setReadCount(rs.getInt("read_count"));
					list.add(notice);
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
	
	public int selectNoticeCount(Search search) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			String keyword = search.getKeyword();
			
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select Count(n.no)");
			sql.append(" from (select no, title, content ");       
			sql.append("         from t97_notice ");
			sql.append("        order by no desc) n");
			
			switch(search.getOpt().toLowerCase()) {
			case "basic":
				stmt = con.prepareStatement(sql.toString());
				break;
				
			case "title":
				sql.append(" where n.title like ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				break;
				
			case "content":
				sql.append(" where n.content like ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				break;
				
			case "all":
				sql.append(" where (n.title like ? ");
				sql.append("   or n.content like ?) ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				stmt.setString(2, "%"+keyword+"%");
			}
			if(stmt != null) {
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) cnt = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	/*
	public int selectNoticeCount(Search search) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			String keyword = search.getKeyword();
			
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select Count(n.no)");
			sql.append(" from (select rownum rnum, nr.* ");
			sql.append("         from (select * ");       
			sql.append("                 from t97_notice ");
			sql.append("                order by no desc) nr");
			sql.append("       ) n ");
			
			switch(search.getOpt().toLowerCase()) {
			case "basic":
				stmt = con.prepareStatement(sql.toString());
				break;
				
			case "title":
				sql.append(" where n.title like ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				break;
				
			case "content":
				sql.append(" where n.content like ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				break;
				
			case "all":
				sql.append(" where (n.title like ? ");
				sql.append("   or n.content like ?) ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				stmt.setString(2, "%"+keyword+"%");
			}
			if(stmt != null) {
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) cnt = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	*/
	//글번호 조회 - 문서 조회
	public Notice selectNoticeByNo(int no) {
		Notice notice = new Notice();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select n.id, n.no, n.title, n.content, n.reg_date , m.name ");
			sql.append("  from t97_notice n ");
			sql.append(" inner join t97_member m ");
			sql.append("    on n.id = m.id ");
			sql.append(" where no = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				notice.setId(rs.getString("id"));
				notice.setNo(rs.getInt("no"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegDate(rs.getTimestamp("reg_date"));
				notice.setName(rs.getString("name"));
				return notice;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return null;
	}
	
	//글 저장
	public int insertNotice(Notice notice) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into t97_notice(no, id, title, content) ");
			sql.append("values(s97_notice_no.nextval, ?, ?, ? )");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, notice.getId());
			stmt.setString(2, notice.getTitle());
			stmt.setString(3, notice.getContent());
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	//글 수정
	public int updateNotice(Notice notice) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("update t97_notice ");
			sql.append("   set title = ?, content = ? ");
			sql.append(" where no = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, notice.getTitle());
			stmt.setString(2, notice.getContent());
			stmt.setInt(3, notice.getNo());
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	//조회수 증가
	public int updateReadCount(int no) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("update t97_notice ");
			sql.append("   set read_count = read_count+1 ");
			sql.append(" where no = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	//글 삭제
	public int deleteNotice(int no) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("delete from t97_notice ");
			sql.append(" where no = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
}