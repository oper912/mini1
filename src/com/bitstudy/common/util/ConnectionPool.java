package com.bitstudy.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	private static final int INIT_COUNT = 5;
	private static List<Connection> free = new ArrayList<>();	//사용하지 않는 connection
	private static List<Connection> used = new ArrayList<>();	//사용중인 connection
	
	static {	// 클래스가 구동될때 실행
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 192.168.0.61
			for(int i=0;i<INIT_COUNT;i++) {
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.61:1521:XE", "hr", "hr");
				free.add(con);
			}
		} catch(Exception e) {
			System.out.println("초기 풀 생성시 오류 발생");
			e.printStackTrace();
		}
	} 
	
	public static Connection getConnection() throws Exception{
		if(free.isEmpty()) {
			throw new Exception("모든 커넥션이 사용중입니다.");
		}
		
		Connection con = free.remove(0);	//0번 인덱스 삭제 시 자동으로 인덱스를 한칸씩 앞으로 이동시킴
		used.add(con);
		return con;
	}
	
	public static void releaseConnection(Connection con) {
		used.remove(con);
		free.add(con);
	}
	
	public static void main(String[] args) {
		try {
			for(int i=0;i<10;i++) {
				System.out.println(i+1+"번째 요청");
				Thread.sleep(1000);
				Connection con = ConnectionPool.getConnection();
				System.out.println(con);
				ConnectionPool.releaseConnection(con);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}