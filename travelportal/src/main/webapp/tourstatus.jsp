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
<title>Report Status</title>
</head>
<body bgcolor="silver">
	<%
		PortalSubmit psubmt = new PortalSubmit();

		LoginBean bean = (LoginBean) session.getAttribute("bean");

		if (bean == null) {
	%><jsp:include page="portalmenu.jsp" flush="true" />
	<%
		}
		java.util.Map<Integer, PortalService> serviceMap = psubmt.getStatus(bean);
		if (serviceMap == null) {
	%>
	<jsp:include page="portalmenu.jsp"></jsp:include>
	<script>
		printMessage("No previous submitted records", "Red");
	</script>
	<%
		} else {

			/* for (java.util.Map.Entry<Integer, PortalService> e : serviceMap.entrySet()) {
				Integer key = (Integer) e.getKey();
				out.println("000000 " + key);
				out.println(serviceMap.get(key).getReportName());
			} */
	%>


	<table width="50%" style="color: blue">
		<tr>
			<td>
				<table border="1" width="75%" style="color: blue">
					<thead>
						<tr>
							<th colspan="3">Portal Menu</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><b>Report Name</b></td>
							<td><b>Status</b></td>
							<td><b>Comments</b></td>
						</tr>
						<%
							for (java.util.Map.Entry<Integer, PortalService> e : serviceMap.entrySet()) {
									Integer key = (Integer) e.getKey();
						%>
						<tr>
							<td>
								<%
									out.println(serviceMap.get(key).getReportName());
								%>
							</td>
							<td>
								<%
									out.println(serviceMap.get(key).getStatus());
								%>
							</td>
							<td>
								<%
									out.println(serviceMap.get(key).getComments());
								%>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
		</tr>

	</table>
	<%
		}
	%>
	<form method="post" action="portalmenu.jsp">
		<input type="submit" value="Portal Menu" style="height: 30px; width: 350px" />
	</form>
</body>
</html>