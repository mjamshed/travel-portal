package org.restapi.portal.model;

public class EmployeeDetails {
	private String id;
	private String password;
	private String designation;

	public EmployeeDetails() {
	}

	public EmployeeDetails(String id, String password, String designation) {
		this.id = id;
		this.password = password;
		this.designation = designation;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
