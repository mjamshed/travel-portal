package org.restapi.portal.resources;

import java.io.FileWriter;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.simple.JSONObject;

@Path("registration")
public class PortalRegistration {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String registration(MultivaluedMap<String, String> form) throws IOException {
		System.out.println("am here at registration");
		System.out.println(form.getFirst("firstName"));

		JSONObject jObj = new JSONObject(form);
		System.out.println("--- > " + jObj);

		/*Map<String, String> parameters = new HashMap<String, String>();
		Iterator<String> it = form.keySet().iterator();
		while (it.hasNext()) {
			String theKey = (String) it.next();
			System.out.println(theKey + "--- " + form.getFirst(theKey));
			parameters.put(theKey, form.getFirst(theKey));
		}
		System.out.println("--- > " + new JSONObject(parameters));
		*/

		try (FileWriter file = new FileWriter("./file1.txt")) {
			file.write(jObj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + jObj);
		}
		return "success";
	}
}
