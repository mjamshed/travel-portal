package org.restapi.portal.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("register")
public class PortalRegister {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it sir!";
	}

}
