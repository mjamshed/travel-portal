<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="main.jsp"%>
<%@page import="org.restapi.portal.bean.LoginBean"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Portal Menu</title>
</head>
<body bgcolor="silver">
	<table border="0" width="20%" cellpadding="3">
		<thead>
			<tr>
				<th colspan="2">Portal Menu</th>
			</tr>
		</thead>
		<%
			LoginBean bean = (LoginBean) session.getAttribute("bean");
			if (bean.getDesignation().equals("Manager")) {
		%>
		<tbody>
			<form method="post" action="receivedapp.jsp">
				<tr>
					<td><input type="submit" value="Received Applications"
						style="height: 30px; width: 350px" /></td>
				</tr>
			</form>

			<%
				} else {
			%>
		
		<tbody>
			<form method="post" action="submittour.jsp">
				<tr>
					<td><input type="submit" value="Submit Tour"
						style="height: 30px; width: 350px" /></td>
				</tr>
			</form>
			<form method="post" action="tourstatus.jsp">
				<tr>
					<td><input type="submit" value="Report Status"
						style="height: 30px; width: 350px" /></td>
				</tr>
			</form>

			<%
				}
			%>
			<form method="post"
				action="${pageContext.request.contextPath}/logout">
				<tr>
					<td><input type="submit" value="Logout"
						style="height: 30px; width: 350px" /></td>
				</tr>

			</form>
		</tbody>
	</table>
</body>
</html>