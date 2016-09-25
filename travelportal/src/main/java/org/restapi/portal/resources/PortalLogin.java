package org.restapi.portal.resources;

import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.restapi.portal.service.PortalService;

@Path("portallogin")
public class PortalLogin {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("userid") String userid, @FormParam("password") String password) {
		PortalService portalservice = new PortalService();

		if (portalservice.getEmployee(userid) != null) {
			String lpassword = portalservice.getEmployee(userid).getPassword();
			String ldesignation = portalservice.getEmployee(userid).getDesignation();

			System.out.println("UserId - " + userid);
			System.out.println("password - " + password);
			if (lpassword.equals(password)) {
				String jspPage = "employee.jsp";

				if (ldesignation.equals("manager")) {
					jspPage = "manager.jsp";
				} else if (ldesignation.equals("finance manager")) {
					jspPage = "finance.jsp";
				}

				try {
					java.net.URI location = new java.net.URI("../" + jspPage);
					throw new WebApplicationException(Response.temporaryRedirect(location).build());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			} else {
				return "Invalid UserId or Password";
			}
		} else {
			return "Invalid UserId or Password";
		}

		return null;
	}

}
