package com.app.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345678";
	private static final String DATABASE = "data_test";
	private static final String HOST = "localhost:3306";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://" + HOST + "/" + DATABASE;
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, USERNAME, PASSWORD);
	}

}
