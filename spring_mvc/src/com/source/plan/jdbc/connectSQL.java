package com.source.plan.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;





public class connectSQL {
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/daiam?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123123";//bpmqbtJOkz6G
	private static BasicDataSource dataSource;
	
	static{
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_CLASS);
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(12);
		dataSource.setMaxActive(12);
	}
	
	public static synchronized Connection getConnection(){
		try {
//			System.out.println("活动的连接："+dataSource.getNumActive());
//			System.out.println("空闲的连接："+dataSource.getNumIdle());
			
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 禁止实例�?
	private connectSQL() {
	}

	// 关闭ResultSet方法
	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭Statement方法
	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭CallableStatement方法
	public static void closeCallableStatement(CallableStatement cstmt) {
		try {
			if (cstmt != null)
				cstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭PreparedStatement方法
	public static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭本次连接方法
	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
