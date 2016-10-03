package org.restapi.portal.database;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.restapi.portal.model.EmployeeDetails;

public class DatabaseHandler extends Database {
	private Map<String, EmployeeDetails> employeeDetails = new HashMap();

	public Map<String, EmployeeDetails> getLoginDetails(String username) {
		EmployeeDetails ed = new EmployeeDetails();
		String query = "select username, designation, encode(password, 'escape') AS PASSWORD FROM ACCOUNTS WHERE USERNAME=?";
		LinkedHashMap<Integer, String[]> params = new LinkedHashMap<Integer, String[]>();
		params.put(1, new String[] { "String", username });
		List<Map<String, Object>> resultRow = dbLookUp(query, params);

		if (resultRow != null)
			for (Map<String, Object> result : resultRow) {
				ed.setId((String) result.get("username"));
				ed.setPassword((String) result.get("password"));
				ed.setDesignation((String) result.get("designation"));
				employeeDetails.put(username, ed);
			}
		return employeeDetails;
	}
}
