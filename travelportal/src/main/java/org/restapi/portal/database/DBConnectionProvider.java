package org.restapi.portal.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionProvider {
	private static final String DB_HOST = "ec2-54-243-203-232.compute-1.amazonaws.com";
	private static final String DB_USERNAME = "ujrleeibuktmti";
	private static final String DB_PASSWORD = "G9XbSWi6SyUmS235gyCQFKrejV";
	private static final String DB_PORT = "5432";
	private static final String DB_NAME = "d4bmnb4b78k16c";
	private static final String DATABASE_URL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
			+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

	private static Connection conn = null;

	/*static {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DATABASE_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception e) {
		}
	}*/

	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DATABASE_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception e) {
		}
		return conn;
	}

}
