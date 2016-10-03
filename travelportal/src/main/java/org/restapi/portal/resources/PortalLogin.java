package org.restapi.portal.resources;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// import com.sun.jersey.api.view.Viewable;

// @Path("/portallogin")
@WebServlet("/portallogin")
public class PortalLogin extends HttpServlet {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/portalmenu.jsp");
		dispatcher.forward(request, response);

		/*PortalService portalservice = new PortalService();
		EmployeeDetails ed = portalservice.getEmployeeDetails(userid, password);
		if (ed != null) {
			if (ed.getPassword().equals(password)) {
				String jspPage = "employee.jsp";
				if (ed.getDesignation().equalsIgnoreCase("manager")) {
					jspPage = "manager.jsp";
				} else if (ed.getDesignation().equalsIgnoreCase("finance manager")) {
					jspPage = "finance.jsp";
				}
		
				try {
					java.net.URI location = new java.net.URI("../" + jspPage);
					throw new WebApplicationException(Response.temporaryRedirect(location).build());
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return "Invalid UserId or Password..";
			}
		} else {
			return "Invalid UserId or Password...";
		}
		
		return null;
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
