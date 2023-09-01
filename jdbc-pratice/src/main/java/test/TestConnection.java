package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String[] args) {
		Connection conn = null;
		// 1. JDBC Driver Class 로딩
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.64.2:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url,"webdb","qorwodnjs1!");
			
			System.out.println("연결 성공!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2.연결하기
		catch (SQLException e) {
				System.out.println("드라이버 로딩 실패: " + e);
		}
		finally {
			try {
				if(conn != null) {
					conn.close();
				}
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}

}
