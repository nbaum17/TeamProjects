package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection con;
	private static final String HOST = "localhost:3306/teamprojects";
	private static final String USER = "root";
	private static final String PASSWD = "root";

	/**
	 * This establishes a connection with the database if one does not already exist. 
	 * @return the current database connection
	 * @throws SQLException in case the connection failes other classes might want to know that. 
	 */
	public static Connection getConnection() throws SQLException {
		if(con == null) {
			con = DriverManager.getConnection("jdbc:mysql://" + HOST, USER, PASSWD);
		}
		return con;
	}
}
