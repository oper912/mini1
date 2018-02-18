package com.bitstudy.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bitstudy.common.util.ConnectionPool;
import com.bitstudy.common.util.JdbcUtil;
import com.bitstudy.free.domain.Free;

public class FreeDAO {

	public List<Free> select(){
	 List<Free>list = new ArrayList<>();
	 Connection con=null;
	 PreparedStatement stmt=null;
	 try {
		 con=ConnectionPool.getConnection();
		 StringBuffer sql=new StringBuffer();
		 sql.append(" select no, content, group_no, m.name, reg_date,m.id ");
		 sql.append(" from t97_free f inner join t97_member m ");
		 sql.append(" on f.id=m.id ");
		 sql.append(" order by group_no desc , no ");
		 stmt=con.prepareStatement(sql.toString());
		 ResultSet rs=stmt.executeQuery();
		 	while (rs.next()) {
		 		Free domain=new Free();
		 		domain.setNo(rs.getInt("no"));
		 		domain.setGroupNo(rs.getInt("group_no"));
		 		domain.setContent(rs.getString("content"));
		 		domain.setName(rs.getString("name"));
		 		domain.setRegDate(rs.getTimestamp("reg_date"));
		 		domain.setId(rs.getString("id"));
		 		list.add(domain);
		 		//System.out.println(list.toString());
		 	}
		 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }finally {
		 JdbcUtil.close(stmt);
		 ConnectionPool.releaseConnection(con);
	 }
	 
	return list;
	
	}
	public void insert(Free domain) {
	Connection con=null;
	PreparedStatement stmt=null;
	
	try {
		con=ConnectionPool.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("insert into t97_free(no,group_no,content,id) ");
		sql.append(" values(s97_free_no.nextval,s97_free_no.currval,?,?)");
		stmt=con.prepareStatement(sql.toString());
		
		stmt.setString(1,domain.getContent());
		stmt.setString(2,domain.getId());
		stmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		JdbcUtil.close(stmt);
		ConnectionPool.releaseConnection(con);
	}
	
	}
	
	public void insertComment(Free domain) {
		Connection con=null;
		PreparedStatement stmt=null;
		
		try {
			con=ConnectionPool.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("insert into t97_free(no,group_no,content,id) ");
			sql.append(" values(s97_free_no.nextval,?,?,?)");
			stmt=con.prepareStatement(sql.toString());
			
			stmt.setInt(1,domain.getGroupNo());
			stmt.setString(2,domain.getContent());
			stmt.setString(3,domain.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	public void update(Free domain) {
	Connection con=null;
	PreparedStatement stmt=null;
	try {
		con=ConnectionPool.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("update t97_free ");
		sql.append(" set content = ?");
		sql.append("where no = ?");
		stmt=con.prepareStatement(sql.toString());
		stmt.setString(1,domain.getContent());
		stmt.setInt(2,domain.getNo());
		stmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		JdbcUtil.close(stmt);
		ConnectionPool.releaseConnection(con);
		
	}
	}
	public boolean Commentdelete(int no) {
		Connection con= null;
		PreparedStatement stmt = null;
		try {
			con=ConnectionPool.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("delete");
			sql.append(" from t97_free");
			sql.append(" where no=? ");
			stmt=con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			int cnt=stmt.executeUpdate();
			if(cnt>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return false;
	}
	public boolean delete(int groupNo) {
		Connection con= null;
		PreparedStatement stmt = null;
		try {
			con=ConnectionPool.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("delete");
			sql.append(" from t97_free");
			sql.append(" where group_No=? ");
			stmt=con.prepareStatement(sql.toString());
			stmt.setInt(1, groupNo);
			int cnt=stmt.executeUpdate();
			if(cnt>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return false;
	}
}
