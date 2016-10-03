package org.restapi.portal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.restapi.portal.model.EmployeeDetails;

public class Database {

	//String passwordQry = "select encode(password1, 'escape') AS PASSWORD FROM ACCOUNTS WHERE USERNAME=?";

	public static List<Map<String, Object>> dbLookUp(String sqlQuery, LinkedHashMap<Integer, String[]> params) {
		PreparedStatement preparedStatement = null;
		Connection conn = DBConnectionProvider.getConnection();

		try {
			Class.forName("org.postgresql.Driver");
			//String passwordQry = "select encode(password1, 'escape') AS PASSWORD FROM ACCOUNTS WHERE USERNAME = ?";
			preparedStatement = conn.prepareStatement(sqlQuery);

			for (Map.Entry<Integer, String[]> entry : params.entrySet()) {
				int key = entry.getKey();
				String[] value = entry.getValue();

				if (value[0].equals("String")) {
					preparedStatement.setString(key, value[1]);
				} else if (value[0].equals("Integer")) {
					preparedStatement.setInt(key, Integer.parseInt(value[1]));
				}

			}
			ResultSet rs = preparedStatement.executeQuery();

			return resultSetToList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {

			try {
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Operation done successfully");
		return null;
	}

	/**
	 * Convert the ResultSet to a List of Maps, where each Map represents a row
	 * with columnNames and columValues
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;
		while (rs.next()) {
			row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			rows.add(row);
		}
		System.out.println("Result Size - " + rows.size());
		return rows;
	}
}
