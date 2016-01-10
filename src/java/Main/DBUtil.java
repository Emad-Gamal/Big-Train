package Main;


import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * * @Author : Emad Gamal Attia
 */
public class DBUtil {
    	final static String URL = "jdbc:mysql://localhost:3306/train_reservation";
	final static String USER = "root";
	final static String PASS = "root";
	final static String JDBC = "com.mysql.jdbc.Driver";

	static {

		try {
			Class.forName(JDBC);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}

	public static void cleanUpResources(Connection conn, ResultSet rs, Statement stmt) throws SQLException {

		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();

	}
        
        public static void cleanUpResources(Connection conn, Statement stmt) throws SQLException {
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();

	}
        
        public static String Date(String date) {
       
        String[] parts = date.split("-");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];
        date = year + "-" + month + "-" + day;
        return date;
    }

}
