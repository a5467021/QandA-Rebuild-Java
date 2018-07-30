package com.ncuhome.QandA.api.models;

import java.sql.DriverManager;
import java.sql.Connection;

public class BaseDatabaseModel {

	private static final String DB_URL = "jdbc:mysql://192.168.114.136:3306/devdb?useUnicode=yes&characterEncoding=UTF-8";
	private static final String USER = "dev";
	private static final String PASSWORD = "dev0123";
	
	private static Connection conn = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		return conn;
	}
}
