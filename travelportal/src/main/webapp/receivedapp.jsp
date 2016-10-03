<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="main.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.restapi.portal.resources.PortalSubmit"%>
<%@page import="org.restapi.portal.bean.LoginBean"%>
<%@page import="org.restapi.portal.service.PortalService"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Received Application</title>
</head>
<body bgcolor="silver">
	<%
		PortalSubmit psubmt = new PortalSubmit();
		LoginBean bean = (LoginBean) session.getAttribute("bean");

		if (bean == null) {
	%><jsp:include page="portalmenu.jsp" flush="true" />
	<%
		}

		java.util.Map<Integer, PortalService> serviceMap = psubmt.getReceivedApp(bean);
		if (serviceMap == null) {
	%>
	<jsp:include page="portalmenu.jsp"></jsp:include>
	<script>
		printMessage("No Records found", "Red");
	</script>
	<%
		} else {
	%>
	<form method="Post" action="webapi/tourupdate">
		<table width="80%" style="color: blue">
			<tr>
				<td>
					<table border="1" width="100%" style="color: blue">
						<thead>
							<tr>
								<th colspan="14">Received Application</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><b>Submitted By</b></td>
								<td><b>Report Name</b></td>
								<td><b>Purpose</b></td>
								<td><b>Start Date</b></td>
								<td><b>End Date</b></td>
								<td><b>Travel Mode</b></td>
								<td><b>Ticket Cost</b></td>
								<td><b>Cost of Airport Cab<br /> at home city
								</b></td>
								<td><b>Cost of Airport Cab<br /> at destination city
								</b></td>
								<td><b>Hotel Cost</b></td>
								<td><b>Local Conveyance<br /> at tour location
								</b></td>
								<td><b>Approve / Reject</b></td>
								<td><b>Comments</b></td>
								<td><b>Submit</b></td>

							</tr>
							<%
								for (java.util.Map.Entry<Integer, PortalService> e : serviceMap.entrySet()) {
										Integer key = (Integer) e.getKey();
							%>
							<tr>
								<td>
									<%
										out.println(serviceMap.get(key).getfName() + " " + serviceMap.get(key).getlName());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getReportName());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getPurpose());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getStartDate());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getEndDate());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getMode());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getCost());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getCostAch());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getCostAcd());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getCostHotel());
									%>
								</td>
								<td>
									<%
										out.println(serviceMap.get(key).getConveyance());
									%>
								</td>
								<input type="hidden" name="reportName" value="<%=serviceMap.get(key).getReportName() %>"/>
								<input type="hidden" name="userName" value="<%=serviceMap.get(key).getUserName() %>"/>
								<td><select id="appStatus" name="appStatus">
										<option value="Approved">Approve</option>
										<option value="Rejected">Reject</option>

								</select></td>
								<td><textarea id="comments" name="comments"
										style="overflow: auto; resize: none" rows="2" cols="20"></textarea></td>
								<td><input type="submit" value="Submit" /></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</td>
			</tr>

		</table>
	</form>
	<%
		}
	%>
	<form method="post" action="portalmenu.jsp">
		<input type="submit" value="Portal Menu"
			style="height: 30px; width: 350px" />
	</form>
</body>
</html>