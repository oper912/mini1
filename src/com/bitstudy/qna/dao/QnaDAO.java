package com.bitstudy.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bitstudy.qna.domain.Qna;
import com.bitstudy.common.domain.Search;
import com.bitstudy.common.util.*;

public class QnaDAO {
	List<Qna> list = new ArrayList<>();
	Connection con = null;
	PreparedStatement stmt = null;
	
	public List<Qna> selectQna(Search search) {
		try {
		con  = ConnectionPool.getConnection();
		
		String keyword = search.getKeyword();
		int count = search.getCount();
		int pageNo = search.getPageNo();
		int endNo = pageNo * count;
		int startNo = endNo - count + 1;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select q.no, q.group_no, q.title, q.name, q.reg_date, q.read_count ");
		sql.append(" from (select rownum rnum, qr.* ");
		sql.append("         from (select * ");       
		sql.append("                 from t97_qna ");
		sql.append("                order by reg_date desc) qr");
		sql.append("       ) q ");
		sql.append("               where group_no = no ");
		
		switch(search.getOpt().toLowerCase()) {
		case "basic":
			sql.append(" and q.rnum between ? and ?"); 
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, startNo);
			stmt.setInt(2, endNo);
			break;
			
		case "title":
			sql.append(" and q.title like ? ");
			sql.append("   and q.rnum between ? and ?"); 
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, "%"+keyword+"%");
			stmt.setInt(2, startNo);
			stmt.setInt(3, endNo);
			break;
			
		case "content":
			sql.append(" and q.content like ? ");
			sql.append("   and q.rnum between ? and ?"); 
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, "%"+keyword+"%");
			stmt.setInt(2, startNo);
			stmt.setInt(3, endNo);
			break;
			
		case "all":
			sql.append(" and (q.title like ? ");
			sql.append("   or q.content like ?) ");
			sql.append("   and q.rnum between ? and ?"); 
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, "%"+keyword+"%");
			stmt.setString(2, "%"+keyword+"%");
			stmt.setInt(3, startNo);
			stmt.setInt(4, endNo);
			
		}
		if(stmt != null) {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Qna qna = new Qna();
				qna.setNo(rs.getInt("no"));
				qna.setGroupNo(rs.getInt("group_no"));
				qna.setTitle(rs.getString("title"));
				qna.setName(rs.getString("name"));
				qna.setRegDate(rs.getDate("reg_date"));
				qna.setReadCount(rs.getInt("read_count"));
				list.add(qna);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
		public List<Qna> selectQnaByNoA(int no) {
			try {
//				System.out.println("Dao : " + no);
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select q.no, q.group_no, q.title, q.name, q.reg_date, q.read_count, q.content "); // select * 
				sql.append("  from t97_qna q ");
//				sql.append(" inner join t97_memeber m ");
//				sql.append("  on q.id = m.id ");
				sql.append(" where group_no = ?");
				sql.append(" and no != ?");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(1, no);
				stmt.setInt(2, no);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Qna qnal = new Qna();
					qnal.setNo(rs.getInt("no"));
					qnal.setGroupNo(rs.getInt("group_no"));
					qnal.setTitle(rs.getString("title"));
					qnal.setName(rs.getString("name"));
					qnal.setRegDate(rs.getDate("reg_date"));
					qnal.setReadCount(rs.getInt("read_count"));
					qnal.setContent(rs.getString("content"));
					System.out.println("No : " + qnal.getNo());
					System.out.println("Title : " + qnal.getTitle());
					System.out.println("Content : " + qnal.getContent());
					list.add(qnal);
					}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(stmt);
				ConnectionPool.releaseConnection(con);
			}
			return list;
		}
		
		public Qna selectQnaByNo(int no) {
			Qna qnal = new Qna();
			try {
//				System.out.println("Dao : " + no);
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select q.no, q.group_no, q.title, q.name, q.reg_date, q.read_count, q.content "); // select * 
				sql.append("  from t97_qna q ");
//				sql.append(" inner join t97_memeber m ");
//				sql.append("  on q.id = m.id ");
				sql.append(" where no = ?");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(1, no);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					qnal.setNo(rs.getInt("no"));
					qnal.setGroupNo(rs.getInt("group_no"));
					qnal.setTitle(rs.getString("title"));
					qnal.setName(rs.getString("name"));
					qnal.setRegDate(rs.getDate("reg_date"));
					qnal.setReadCount(rs.getInt("read_count"));
					qnal.setContent(rs.getString("content"));
//					System.out.println(qnal.toString());
					}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(stmt);
				ConnectionPool.releaseConnection(con);
			}
			return qnal;
			
		}
		
		public void readCount(int no) {//조회수
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("update t97_qna ");
				sql.append(" set read_count = read_count + 1 ");
				sql.append(" where no = ? ");
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(1,no);
				stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(stmt);
				ConnectionPool.releaseConnection(con);
			}
		}
		
	public int insertQ(Qna b) {//질문용 글작성
		int cnt = 0;
		try {
//			System.out.println(b.getGroupNo());
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into t97_qna(no, group_no,content,title,name,id )");
			sql.append(" values(qna_no.nextval,qna_no.currval,?,?,?,? )");
			stmt = con.prepareStatement(sql.toString());
			int i =0;
			stmt.setString(++i, b.getContent());
			stmt.setString(++i, b.getTitle());
			stmt.setString(++i, b.getName());
			stmt.setString(++i, b.getId());
			cnt = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	public  int insertA(Qna b) {//답변용 글작성
			int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into t97_qna(no, group_no, content,title,name,id )" );
			sql.append(" values(qna_no.nextval,?,?,?,?,? )");
			stmt = con.prepareStatement(sql.toString());
			int i  = 0;
			
			stmt.setInt(++i, b.getGroupNo());// 원글 시퀀스 번호 가져오기
			stmt.setString(++i, b.getContent());
			stmt.setString(++i, b.getTitle());
			stmt.setString(++i, b.getName());
			stmt.setString(++i, b.getId());
			cnt = stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	public int updateQna(Qna b) { // GroupNo로 질문 답변 확인하기
		int cnt = 0;
		try {
//		System.out.println("content : " + b.getContent());
					
		con = ConnectionPool.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("update t97_qna ");
		sql.append(" set content = ? , title = ? ");
		sql.append(" where no = ? ");
		
		stmt = con.prepareStatement(sql.toString());
		stmt.setString(1, b.getContent());
		stmt.setString(2, b.getTitle());
		stmt.setInt(3, b.getNo());
		cnt = stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	public Boolean delete(Qna d) {
		int cnt = -1;
//		System.out.println("id : " + d.getId());
//		System.out.println("No : " + d.getNo());
//		System.out.println("GroupNo : " + d.getGroupNo());
		
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("delete from t97_qna ");
			sql.append(" where id = ? ");
		if (d.getNo() != 0) {
			sql.append(" and no = ? ");
			stmt = con.prepareStatement(sql.toString());
			int dy = 0;
			stmt.setString(++dy, d.getId());
			stmt.setInt(++dy, d.getNo());
		}else {
			sql.append(" and group_no = ? ");
			stmt = con.prepareStatement(sql.toString());
			int dy = 0;
			stmt.setString(++dy, d.getId());
			stmt.setInt(++dy, d.getGroupNo());
		}
			cnt = stmt.executeUpdate();
//			System.out.println(cnt);
			}catch(Exception e) {
			e.printStackTrace();
			}finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		if(cnt == -1) {
			return false;
		}
		return true;
	}

	public int selectQnaCount(Search search) {
		
		int cnt = 0;
		try {
			String keyword = search.getKeyword();
			
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select Count(q.no)");
			sql.append(" from (select rownum rnum, qr.* ");
			sql.append("         from (select * ");       
			sql.append("                 from t97_qna ");
			sql.append("                order by reg_date desc) qr");
			sql.append("       ) q ");
			
			switch(search.getOpt().toLowerCase()) {
			case "basic":
				stmt = con.prepareStatement(sql.toString());
				break;
				
			case "title":
				sql.append(" where q.title like ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				break;
				
			case "content":
				sql.append(" where q.content like ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, "%"+keyword+"%");
				break;
				
			case "all":
				sql.append(" where (q.title like ? ");
				sql.append("   or q.content like ?) ");
				
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
}
