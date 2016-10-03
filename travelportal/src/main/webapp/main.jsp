<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/main.js"></script>
<div id="message"></div>
<%
	if (session.getAttribute("session") == null || (!session.getAttribute("session").equals("TRUE"))) {
%>
<jsp:include page="index.jsp"></jsp:include>
<%
	return;
	}
%>