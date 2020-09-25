package utils;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
	
	/**
	 * methodName:getConnection
	 * @return conn
	 * */
	public static Connection getConnection() {
		
		try {
			//DataSource
			//http://tomcat.apache.org/tomcat-8.5-doc/jndi-datasource-examples-howto.html#Oracle_8i,_9i_&_10g
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			Connection conn = ds.getConnection();
			//DB연결 성공 후 conn반환
			return conn;
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		}
		//DB연결 실패할 경우 null를 반환
		return null;
		}
		
	/**
	 * methodName:close
	 * */
	public static void close(Connection conn ,PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * methodName:close (OverLoading)
	 * */
	public static void close(Connection conn ,PreparedStatement pstmt) {
			
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    
}
