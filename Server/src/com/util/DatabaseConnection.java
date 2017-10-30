package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection con = null;
	private static final String HOST = "localhost:3306/team_projects?useSSL=false";
	private static final String USER = "user";
	private static final String PASSWD = "user";

	/**
	 * This returns a connection to the database. 
	 * @return the current database connection
	 * @throws SQLException in case the connection failes other classes might want to know that. 
	 */
	public static Connection getConnection() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://" + HOST, USER, PASSWD);
		return con;
	}
}
