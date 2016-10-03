package org.restapi.portal.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBCTester {

	//private static final String DATABASE_URL = "jdbc:postgresql://ujrleeibuktmti:G9XbSWi6SyUmS235gyCQFKrejV@ec2-54-243-203-232.compute-1.amazonaws.com:5432/d4bmnb4b78k16c";
	private static final String DB_HOST = "ec2-54-243-203-232.compute-1.amazonaws.com";
	private static final String DB_USERNAME = "ujrleeibuktmti";
	private static final String DB_PASSWORD = "G9XbSWi6SyUmS235gyCQFKrejV";
	private static final String DB_PORT = "5432";
	private static final String DB_NAME = "d4bmnb4b78k16c";

	//jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
	private static final String DATABASE_URL = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
			+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("DATABASE_URL : " + DATABASE_URL);
			c = DriverManager.getConnection(DATABASE_URL, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql1 = "CREATE SEQUENCE IF NOT EXISTS next_seq";
			String sql2 = "CREATE TABLE IF NOT EXISTS ACCOUNTS (ID integer PRIMARY KEY default nextval('next_seq'), "
					+ "USERNAME VARCHAR(100), PASSWORD BYTEA," + "FNAME VARCHAR(150)," + "LNAME VARCHAR(150),"
					+ "EMAIL VARCHAR(150), DESIGNATION VARCHAR(150) );";
			String sql3 = "insert into ACCOUNTS(username, password, fname, lname,email, designation) "
					+ "values('jamshed','123456','Mohammed', 'Jamshed','md.jamie.in@hotmail.com','System Engineer')";
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("successfully");
	}

}
