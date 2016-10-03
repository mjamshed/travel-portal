package org.restapi.portal.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.restapi.portal.bean.LoginBean;
import org.restapi.portal.database.DBConnectionProvider;
import org.restapi.portal.database.Database;
import org.restapi.portal.service.PortalService;

@Path("/")
public class PortalSubmit extends Database {

	private Map<Integer, PortalService> serviceMap = new HashMap<Integer, PortalService>();

	@POST
	@Produces(MediaType.TEXT_HTML)
	//@Consumes(MediaType.TEXT_PLAIN)
	@Path("tourupdate")
	public Response tourUpdate(MultivaluedMap<String, String> form, @Context HttpServletRequest request)
			throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = DBConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		boolean status = false;
		String message = "";

		LoginBean bean = (LoginBean) request.getSession().getAttribute("bean");
		Map<String, String> parameters = new HashMap<String, String>();
		Iterator<String> it = form.keySet().iterator();

		while (it.hasNext()) {
			String theKey = (String) it.next();
			System.out.println(theKey + "--- " + form.getFirst(theKey));
			parameters.put(theKey, form.getFirst(theKey));
		}

		String updateQuery = "Update TRAVEL_DETAILS set status=?, comments= ? where report_name = ? and submitter_id = ( Select id from accounts where username = ? ) ";

		System.out.println("1 " + parameters.get("appStatus"));
		System.out.println("2 " + parameters.get("comments"));
		System.out.println("3 " + parameters.get("reportName"));
		System.out.println("4 " + parameters.get("userName"));
		try {
			preparedStatement = conn.prepareStatement(updateQuery);
			preparedStatement.setString(1, parameters.get("appStatus"));
			preparedStatement.setString(2, parameters.get("comments"));
			preparedStatement.setString(3, parameters.get("reportName"));
			preparedStatement.setString(4, parameters.get("userName"));
			preparedStatement.executeUpdate();

		} catch (NullPointerException npe) {
			message = "Null pointer exception caught. Please Relogin...";
			status = true;
			npe.printStackTrace();
			System.out.println("Exception : " + npe.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			status = true;
			message = "Exception caught. Please Relogin...";
			e.printStackTrace();
		} finally {

			if (status) {
				status = false;
				conn.rollback();
				String htmlPage = "<html><head> " + "<meta http-equiv='refresh' content='3;url=../index.jsp' /> "
						+ "</head>" + "<body> " + "<h2>" + message + "</h2>" + "</body>" + "</html>";
				return Response.status(400).entity(htmlPage).build();

			} else {
				conn.commit();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (conn != null) {
				conn.close();
			}

		}
		String htmlPage = "<html><head> " + "<meta http-equiv='refresh' content='3;url=../portalmenu.jsp' /> "
				+ "</head>" + "<body> " + "<h2>Request Submitted succesfully...</h2>" + "</body>" + "</html>";

		//return Response.status(200).entity(htmlPage);
		return Response.status(200).entity(htmlPage).build();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	//@Consumes(MediaType.TEXT_PLAIN)
	@Path("toursubmit")
	public Response submitForApproval(MultivaluedMap<String, String> form, @Context HttpServletRequest request)
			throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conn = DBConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		boolean status = false;
		String message = "";
		try {
			System.out.println(">>> am here at submitForApproval");
			System.out.println(form);
			LoginBean bean = (LoginBean) request.getSession().getAttribute("bean");
			System.out.println("----" + bean.getId());
			System.out.println("----" + bean.getUserName());

			/*	String query = "Select id from accounts where USERNAME = ?";
				LinkedHashMap<Integer, String[]> params = new LinkedHashMap<Integer, String[]>();
				params.put(1, new String[] { "String", bean.getUserId() });
			*/
			Map<String, String> parameters = new HashMap<String, String>();
			Iterator<String> it = form.keySet().iterator();
			while (it.hasNext()) {
				String theKey = (String) it.next();
				System.out.println(theKey + "--- " + form.getFirst(theKey));
				parameters.put(theKey, form.getFirst(theKey));
			}

			String insertQuery = "INSERT INTO TRAVEL_DETAILS(PURPOSE,START_DATE,END_DATE,MODE,COST,COST_ACH,COST_ACD,COST_HOTEL,"
					+ "CONVEYANCE,SUBMITTER_ID, REPORT_NAME, STATUS, COMMENTS) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,'Pending Approval', 'Report Submitted for Approval')";

			System.out.println(insertQuery);

			preparedStatement = conn.prepareStatement(insertQuery);

			preparedStatement.setString(1, parameters.get("tourPurpose"));
			preparedStatement.setTimestamp(2, stringTotimeStamp(parameters.get("startDate"), "yyyy-MM-dd"));
			preparedStatement.setTimestamp(3, stringTotimeStamp(parameters.get("endDate"), "yyyy-MM-dd"));
			preparedStatement.setString(4, parameters.get("travelMode"));
			preparedStatement.setString(5, parameters.get("cost"));
			preparedStatement.setString(6, parameters.get("cach"));
			preparedStatement.setString(7, parameters.get("cadh"));
			preparedStatement.setString(8, parameters.get("hcost"));
			preparedStatement.setString(9, parameters.get("localc"));
			preparedStatement.setInt(10, bean.getId());
			preparedStatement.setString(11, parameters.get("reportname"));
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (NullPointerException npe) {
			message = "Null pointer exception caught. Please Relogin...";
			status = true;
			npe.printStackTrace();
			System.out.println("Exception : " + npe.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			status = true;
			message = "Exception caught. Please Relogin...";
			e.printStackTrace();
		} finally {

			if (status) {
				status = false;
				conn.rollback();
				String htmlPage = "<html><head> " + "<meta http-equiv='refresh' content='3;url=../index.jsp' /> "
						+ "</head>" + "<body> " + "<h2>" + message + "</h2>" + "</body>" + "</html>";
				return Response.status(400).entity(htmlPage).build();

			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (conn != null) {
				conn.close();
			}

		}

		String htmlPage = "<html><head> " + "<meta http-equiv='refresh' content='3;url=../portalmenu.jsp' /> "
				+ "</head>" + "<body> " + "<h2>Request Submitted succesfully...</h2>" + "</body>" + "</html>";

		//return Response.status(200).entity(htmlPage);
		return Response.status(200).entity(htmlPage).build();
	}

	public Timestamp stringTotimeStamp(String fromDate, String fromFormat) throws ParseException {
		Timestamp timestamp = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat);
		Date parsedDate = dateFormat.parse(fromDate);
		timestamp = new java.sql.Timestamp(parsedDate.getTime());

		return timestamp;
	}

	public Map<Integer, PortalService> getStatus(LoginBean bean) {
		PortalService pService;

		String query = "select report_name as REPORT, status as STATUS, comments as comments "
				+ "FROM TRAVEL_DETAILS WHERE submitter_id = " + "( Select id from accounts where username = ? ) ";

		LinkedHashMap<Integer, String[]> params = new LinkedHashMap<Integer, String[]>();
		params.put(1, new String[] { "String", bean.getUserName() });
		List<Map<String, Object>> resultRow = dbLookUp(query, params);

		int i = 1;
		if (resultRow != null)
			for (Map<String, Object> result : resultRow) {
				pService = new PortalService();
				pService.setReportName((String) result.get("report"));
				pService.setStatus((String) result.get("status"));
				pService.setComments((String) result.get("comments"));
				serviceMap.put(i++, pService);
			}

		return serviceMap;
	}

	public Map<Integer, PortalService> getReceivedApp(LoginBean bean) {
		PortalService pService;

		String query = "select ac.username, ac.fname, ac.lname, to_char(td.start_date, 'YYYY-MM-DD') as startdate, to_char(td.end_date, 'YYYY-MM-DD') as enddate, "
				+ "td.* from travel_details td, accounts ac, employee_group eg "
				+ "where  td.submitter_id = ac.id and td.submitter_id = eg.employee_id and td.status = 'Pending Approval' and "
				+ "ac.id = eg.employee_id and eg.manager_id = (Select id from accounts where username = ?)";

		LinkedHashMap<Integer, String[]> params = new LinkedHashMap<Integer, String[]>();
		params.put(1, new String[] { "String", bean.getUserName() });
		List<Map<String, Object>> resultRow = dbLookUp(query, params);

		int i = 1;

		if (resultRow != null)
			for (Map<String, Object> result : resultRow) {

				pService = new PortalService();
				pService.setUserName((String) result.get("username"));
				pService.setfName((String) result.get("fname"));
				pService.setlName((String) result.get("lname"));
				pService.setPurpose((String) result.get("purpose"));
				pService.setStartDate((String) result.get("startdate"));
				pService.setEndDate((String) result.get("enddate"));
				pService.setMode((String) result.get("mode"));
				pService.setCost((String) result.get("cost"));
				pService.setCostAch((String) result.get("cost_ach"));
				pService.setCostAcd((String) result.get("cost_acd"));
				pService.setCostHotel((String) result.get("cost_hotel"));
				pService.setConveyance((String) result.get("conveyance"));
				pService.setReportName((String) result.get("report_name"));
				serviceMap.put(i++, pService);

			}

		return serviceMap;
	}

}
