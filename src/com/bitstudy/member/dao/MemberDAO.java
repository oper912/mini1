package com.bitstudy.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bitstudy.common.util.ConnectionPool;
import com.bitstudy.common.util.JdbcUtil;
import com.bitstudy.member.domain.Member;
import com.bitstudy.member.domain.ProfileImage;
import com.bitstudy.member.domain.SearchMember;

public class MemberDAO {
	public Member selectMember(SearchMember search) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			Member member = search.getMember();	//검색 키워드
			
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			ResultSet rs = null;
			
			switch(search.getOpt().toLowerCase()) {
			case "login":
				sql.append("select m.id, m.name, m.admin, p.file_system_name");
				sql.append("  from t97_member m ");
				sql.append("  left outer join t97_profile_img p ");
				sql.append("    on m.id = p.id ");
				sql.append(" where email = ? and password = ? and drop_date is null");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getEmail());
				stmt.setString(2, member.getPassword());
				rs = stmt.executeQuery();
				if(rs.next()) {
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setAdmin(rs.getInt("admin") == 1?true:false);
					member.setImage(rs.getString("file_system_name"));
					return member;
				}
				break;

			case "loginkeep":
				sql.append("select name, admin");
				sql.append("  from t97_member ");
				sql.append(" where id = ? and drop_date is null");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getId());
				rs = stmt.executeQuery();
				if(rs.next()) {
					member.setName(rs.getString("name"));
					member.setAdmin(rs.getInt("admin") == 1?true:false);
					return member;
				}
				break;
				
			case "modinfo":
				sql.append("select name, password, email, phone_no, pw_hint_question, pw_hint_answer ");
				sql.append("  from t97_member ");
				sql.append(" where id = ?");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getId());
				rs = stmt.executeQuery();
				if(rs.next()) {
					member.setName(rs.getString("name"));
					member.setPassword(rs.getString("password"));
					member.setEmail(rs.getString("email"));
					member.setPhoneNo(rs.getString("phone_no"));
					member.setPwHintQuestion(rs.getString("pw_hint_question"));
					member.setPwHintAnswer(rs.getString("pw_hint_answer"));
					return member;
				}
				break;
			
			case "findpw":
				sql.append("select pw_hint_question, pw_hint_answer ");
				sql.append("  from t97_member ");
				sql.append(" where email = ? and name = ? and phone_no = ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getEmail());
				stmt.setString(2, member.getName());
				stmt.setString(3, member.getPhoneNo());
				rs = stmt.executeQuery();
				if(rs.next()) {
					member.setPwHintQuestion(rs.getString("pw_hint_question"));
					member.setPwHintAnswer(rs.getString("pw_hint_answer"));
					return member;
				}
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return null;
	}
	
	public boolean selectMemberCheck(SearchMember search) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			Member member = search.getMember();	//검색 키워드
			
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			ResultSet rs = null;
			switch(search.getOpt().toLowerCase()){
			case "checkpw":
				sql.append("select Count(*) ");
				sql.append("  from t97_member ");
				sql.append(" where id = ? and password = ?");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getId());
				stmt.setString(2, member.getPassword());
				rs = stmt.executeQuery();
				break;
				
			case "hintcheck":
				sql.append("select Count(*) ");
				sql.append("  from t97_member ");
				sql.append(" where email = ? and pw_hint_answer = ? ");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getEmail());
				stmt.setString(2, member.getPwHintAnswer());
				rs = stmt.executeQuery();
				break;
			
			case "signup":
				sql.append("select Count(*) ");
				sql.append("  from t97_member ");
				sql.append(" where email = ?");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getEmail());
				rs = stmt.executeQuery();
				break;
			}
			if(rs.next()) {
				if(rs.getInt(1) > 0) return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return false;
	}
	
	public int insertMember(Member member) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			//회원정보 저장
			StringBuffer sql = new StringBuffer();

			sql.append("insert into t97_member(id, email, password, name, phone_no, pw_hint_question, pw_hint_answer) ");
			sql.append("values(CONCAT('M',TO_CHAR(LPAD(s97_member_id.nextval,4,0))), ?, ?, ?, ?, ?, ?)");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getPhoneNo());
			stmt.setString(5, member.getPwHintQuestion());
			stmt.setString(6, member.getPwHintAnswer());
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	public int updateMember(SearchMember search) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			Member member = search.getMember();	//검색 키워드
			
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			switch(search.getOpt().toLowerCase()) {
			case "checkpw":
				sql.append("update t97_member ");
				sql.append("   set name = ?, phone_no = ?, password = ?, pw_hint_question = ?, pw_hint_answer = ? ");
				sql.append(" where id = ?");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getName());
				stmt.setString(2, member.getPhoneNo());
				stmt.setString(3, member.getPassword());
				stmt.setString(4, member.getPwHintQuestion());
				stmt.setString(5, member.getPwHintAnswer());
				stmt.setString(6, member.getId());
				cnt = stmt.executeUpdate();
				break;
				
			case "pwreset":
				sql.append("update t97_member ");
				sql.append("   set password = ? ");
				sql.append(" where email = ?");
				
				stmt = con.prepareStatement(sql.toString());
				stmt.setString(1, member.getPassword());
				stmt.setString(2, member.getEmail());
				cnt = stmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
//==============================프로필 사진 관련=================================
	public int selectMemberImage(String id) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select Count(id)");
			sql.append("  from t97_profile_img");
			sql.append(" where id = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) cnt = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		System.out.println(cnt);
		return cnt;
	}
	
	public int insertMemberImage(ProfileImage image) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			//회원정보 저장
			StringBuffer sql = new StringBuffer();

			sql.append("insert into t97_profile_img(id, file_size, file_org_name, file_system_name) ");
			sql.append("values(?, ?, ?, ?)");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, image.getId());
			stmt.setLong(2, image.getFileSize());
			stmt.setString(3, image.getFileOrgName());
			stmt.setString(4, image.getFileSystemName());
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	public int updateMemberImage(ProfileImage image) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			//회원정보 저장
			StringBuffer sql = new StringBuffer();
			
			sql.append("update t97_profile_img ");
			sql.append("   set file_size = ?, file_org_name = ? ");
			sql.append(" where id = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setLong(1, image.getFileSize());
			stmt.setString(2, image.getFileOrgName());
			stmt.setString(3, image.getId());
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	public int deleteMemberImage(String id) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("delete from t97_profile_img ");
			sql.append(" where id = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, id);
			cnt = stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return cnt;
	}
	
	public int dropMember(String id) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("update t97_member ");
			sql.append("   set drop_date = sysdate ");
			sql.append(" where id = ?");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, id);
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