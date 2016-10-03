package org.restapi.portal.bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restapi.portal.database.Database;

public class LoginDao extends Database {

	/*public static boolean validate(LoginBean bean) {
		boolean status = false;
		try {
			Connection con = DBConnectionProvider.getConnection();
	
			String query = "select username, designation, encode(password, 'escape') AS PASSWORD FROM ACCOUNTS WHERE USERNAME=? "
					+ " AND encode(password, 'escape') = ?";
			PreparedStatement ps = con.prepareStatement(query);
	
			ps.setString(1, bean.getUserId());
			ps.setString(2, bean.getPassword());
	
			ResultSet rs = ps.executeQuery();
			status = rs.next();
	
		} catch (Exception e) {
		}
	
		return status;
	
	}*/

	public static LoginBean getLoginDetails(LoginBean bean) {
		String query = "select username, designation, encode(PASSWORD, 'escape') AS password, fname, lname, id FROM ACCOUNTS WHERE USERNAME=?  AND encode(password, 'escape') = ?";
		LinkedHashMap<Integer, String[]> params = new LinkedHashMap<Integer, String[]>();
		params.put(1, new String[] { "String", bean.getUserName() });
		params.put(2, new String[] { "String", bean.getPassword() });

		List<Map<String, Object>> resultRow = dbLookUp(query, params);

		if (resultRow != null)
			for (Map<String, Object> result : resultRow) {
				bean.setDesignation((String) result.get("designation"));
				bean.setfName((String) result.get("fname"));
				bean.setlName((String) result.get("lname"));
				bean.setId((Integer) result.get("id"));

				return bean;
			}
		return null;
	}

}