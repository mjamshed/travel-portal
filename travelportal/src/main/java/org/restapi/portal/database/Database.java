package org.restapi.portal.database;

import java.util.HashMap;
import java.util.Map;

import org.restapi.portal.model.EmployeeDetails;

public class Database {
	private static Map<String, EmployeeDetails> employeeDetails = new HashMap();

	public static Map<String, EmployeeDetails> getMessages() {
		return employeeDetails;

	}
}
