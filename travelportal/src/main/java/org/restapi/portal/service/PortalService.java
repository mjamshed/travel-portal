package org.restapi.portal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.restapi.portal.database.Database;
import org.restapi.portal.model.EmployeeDetails;

public class PortalService {
	private static Map<String, EmployeeDetails> employeeDetails = Database.getMessages();

	public PortalService() {
		employeeDetails.put("jamshed", new EmployeeDetails("jamshed", "123456", "employee"));
		employeeDetails.put("tuckkhai", new EmployeeDetails("tuckkhai", "123456", "manager"));
	}

	public List<EmployeeDetails> getAllDetails() {
		return new ArrayList<EmployeeDetails>(employeeDetails.values());
	}

	public EmployeeDetails getEmployee(String id) {
		return employeeDetails.get(id);
	}

}
