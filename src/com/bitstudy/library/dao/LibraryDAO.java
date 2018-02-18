package com.bitstudy.library.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bitstudy.common.util.ConnectionPool;
import com.bitstudy.common.util.JdbcUtil;
import com.bitstudy.common.util.LibraryFileRenamePolicy;
import com.bitstudy.library.domain.Library;
import com.oreilly.servlet.MultipartRequest;

public class LibraryDAO {
	public LibraryDAO() {}
	
	// 자료실 게시물의 총 게시물수를 구해주는 메서드
	public int totalCnt() {
		List<Library> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select lib_no");
			sql.append("  from t97_library ");
			
			stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Library l = new Library();
				l.setLibNo(rs.getInt("lib_no"));
				
				list.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return list.size();
	}
	
	// 전체리스트 보여주는 메서드
	public List<Library> selectLibrary(int pageNo) {
		pageNo = pageNo *5 -4;
		List<Library> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select b.* ");
			sql.append("  from ( ");
			sql.append("        select rownum rnum , a.* ");
			sql.append("          from (select l.* , m.name ");
			sql.append("                  from t97_library l ");
			sql.append("                  inner join t97_member m ");
			sql.append("                     on l.id=m.id ");
			sql.append("                 order by lib_no desc ) a ");
			sql.append("        )b ");
			sql.append("  where rnum between " + pageNo + " and " + (pageNo+4));
			stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Library l = new Library();
				l.setLibNo(rs.getInt("lib_no"));
				l.setTitle(rs.getString("title"));
				l.setId(rs.getString("id"));
				l.setRegDate(rs.getDate("reg_date"));
				l.setReadCount(rs.getInt("read_count"));
				l.setRecommentCount(rs.getInt("recomment_count"));
				l.setName(rs.getString("name"));
				list.add(l);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
	// 조회수 메서드
	public void readCount(int no) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update t97_library ");
			sql.append("   set read_count = read_count+1 ");
			sql.append(" where lib_no = ? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
	}
	
	// 글번호 조회 메서드
	public Library selectLibraryByNo(int no) {
		readCount(no);
		Library library = new Library();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select l.lib_no, l.title, l.reg_date, l.content, l.id, m.name, l.read_count, l.recomment_count ");
			sql.append("  from t97_library l inner join t97_member m ");
			sql.append("    on l.id = m.id ");
			sql.append(" where lib_no = ? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			ResultSet rs = stmt.executeQuery();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			while(rs.next()) {
				library.setLibNo(rs.getInt("lib_no"));
				library.setTitle(rs.getString("title"));
				library.setName(rs.getString("name"));
				library.setId(rs.getString("id"));
				library.setContent(rs.getString("content"));
				library.setRegDate(rs.getTimestamp("reg_date"));
				library.setReadCount(rs.getInt("read_count"));
				library.setRecommentCount(rs.getInt("recomment_count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return library;
	}
	
	// 조회번호의 파일리스트 가져오기
	public List<Library> selectLibraryByNoFile(int no) {
		List<Library> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" select * ");
			sql.append("   from t97_attachment ");
			sql.append("  where lib_no = ? ");
			sql.append("  order by no desc");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1,no);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Library library = new Library();
				library.setFileSize(rs.getInt("file_size"));
				library.setFileOrgName(rs.getString("file_org_name"));
				library.setFileSystemName(rs.getString("file_system_name"));
				library.setFilePath(rs.getString("file_path"));
				library.setNo(rs.getInt("no"));
				list.add(library);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
	// 글등록메서드
	public void insertLibrary(Library library, List<Library> list) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into t97_library(lib_no, title, content, id) ");
			sql.append(" values (s97_library_no.nextval, ?, ?, ?) ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, library.getTitle());
			stmt.setString(2, library.getContent());
			stmt.setString(3, library.getId());
			stmt.executeUpdate();
			
			StringBuffer sqlFile = new StringBuffer();
			
			for(int i = 0; i < list.size(); i++) {
				Library l = list.get(i);
				sqlFile.append(" insert into t97_attachment(no, file_org_name, file_system_name, file_path, file_size, lib_no) ");
				sqlFile.append(" values (s97_attachment_no.nextval, ?, ?, ?, ?, s97_library_no.currval) ");
				
				stmt = con.prepareStatement(sqlFile.toString());
				stmt.setString(1, l.getFileOrgName());
				stmt.setString(2, l.getFileSystemName());
				stmt.setString(3, l.getFilePath());
				stmt.setLong(4, l.getFileSize());
				stmt.executeUpdate();
				sqlFile.setLength(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
	}
	
//	파일 수정하는 메서드
//	public void updateLibraryFile(List<Library> list) {
//		String upload = "C:/java97/server-work/wtpwebapps/bitstudy/upload/library";
//        String path = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
//		
//        File f = new File(upload+path);
//        if(!f.exists()) f.mkdirs();
//        
//        MultipartRequest mRequest = new MultipartRequest(request, upload+path, 1024*1024*30, "utf-8", new LibraryFileRenamePolicy());
//		
//		Connection con = null;
//		PreparedStatement stmt = null;
//		try {
//			con = ConnectionPool.getConnection();
//			StringBuffer sql = new StringBuffer();
//			
//			for(int i = 0; i < list.size(); i++) {
//				Library library = list.get(i);
//				sql.append(" update t97_attachment ");
//				sql.append("    set file_org_name = ?, file_size = ? ");
//				sql.append("  where no = ? ");
//				
//				stmt = con.prepareStatement(sql.toString());
//				stmt.setString(1, library.get);
//				stmt.setString(2, library.getContent());
//				stmt.setInt(3, library.getLibNo());
//				
//				stmt.executeUpdate();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(stmt);
//			ConnectionPool.releaseConnection(con);
//		}
//	}
	
	// 수정하는 메서드
	public void updateLibrary(Library library) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update t97_library ");
			sql.append("    set title = ?, content = ?, reg_date = sysdate ");
			sql.append("  where lib_no = ? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, library.getTitle());
			stmt.setString(2, library.getContent());
			stmt.setInt(3, library.getLibNo());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
	}
	
	// 게시글 삭제 메서드
	public void deleteLibrary(int no) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete ");
			sql.append("   from t97_library ");
			sql.append("  where lib_no = ? ");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
			// 첨부파일 삭제부분
			StringBuffer sql1 = new StringBuffer();
			sql1.append(" delete ");
			sql1.append("   from t97_attachment ");
			sql1.append("  where lib_no = ? ");
			
			stmt = con.prepareStatement(sql1.toString());
			stmt.setInt(1,  no);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
	}
	
	// 게시글 삭제하면 실제 upload 된 파일까지 삭제
	public void deleteLibraryFile(List<Library> list) {	
		String upload = "C:/java97/server-work/wtpwebapps/bitstudy/upload/library";
		boolean chk = false;
		
		for(int i = 0; i < list.size(); i++) {
			Library library = list.get(i);
			File f = new File(upload+library.getFilePath()+"/"+library.getFileSystemName());
			
			if(f.exists()) {
				chk = f.delete();
			}		
/*			if(chk) {
				System.out.println("파일 삭제 성공");
			} else {
				System.out.println("파일 삭제 실패");
			}*/
		}
	}
	
	// 추천 메서드
	public boolean recommendLibrary(int no, String id) {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean chk = true;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" select lib_no, id ");
			sql.append("   from t97_recommend ");
			sql.append("  where lib_no = ? and id = ? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			stmt.setString(2, id);
			
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while(rs.next()) {
				count++;
			}
			if(count > 0) {
				chk = false;
			} else {
				StringBuffer sql1 = new StringBuffer();
				sql1.append(" insert into t97_recommend(lib_no, id) ");
				sql1.append(" values (?, ?) ");
				
				stmt = con.prepareStatement(sql1.toString());
				stmt.setInt(1, no);
				stmt.setString(2, id);
				stmt.executeUpdate();
				
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" update t97_library ");
				sql2.append("    set recomment_count = recomment_count+1 ");
				sql2.append("  where lib_no = ? ");
				stmt = con.prepareStatement(sql2.toString());
				stmt.setInt(1, no);
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return chk;
	}
	
	// 검색
	public List<Library> searchLibrary(String opt, String keyWord) {
		Connection con = null;
		PreparedStatement stmt = null;
		List<Library> list = new ArrayList<>();
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" select l.* , m.name ");
			sql.append("   from t97_library l ");
			sql.append("   inner join t97_member m ");
			sql.append("     on l.id = m.id ");
			
			switch (opt) {
			case "title":
				sql.append("  where title like ? ");
				break;
			case "content":
				sql.append("  where content like ? ");
				break;
			case "name":
				sql.append("  where name like ? ");
				break;
			}
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, "%"+keyWord+"%");
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Library l = new Library();
				l.setLibNo(rs.getInt("lib_no"));
				l.setTitle(rs.getString("title"));
				l.setId(rs.getString("id"));
				l.setRegDate(rs.getDate("reg_date"));
				l.setReadCount(rs.getInt("read_count"));
				l.setRecommentCount(rs.getInt("recomment_count"));
				l.setName(rs.getString("name"));
				list.add(l);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
}
