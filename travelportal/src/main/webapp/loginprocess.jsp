<%@page import="org.restapi.portal.bean.LoginDao"%>
<%@page import="org.restapi.portal.bean.LoginBean"%>
<script src="js/main.js"></script>
<div id="message"></div>
<jsp:useBean id="obj" class="org.restapi.portal.bean.LoginBean" />

<jsp:setProperty property="*" name="obj" />

<%
	LoginBean bean = LoginDao.getLoginDetails(obj);
	if (bean != null) {
		String fName = bean.getfName();
		%><script>printMessage("Hello - <%=fName%>","Blue");</script><%
		session.setAttribute("session", "TRUE");
		session.setAttribute("bean", bean);
		
		String designation = bean.getDesignation();
		String jspPage = "portalmenu.jsp";
		/* if (bean.getDesignation().equalsIgnoreCase("manager")) {
			jspPage = "WEB-INF/manager.jsp";
		} else if (bean.getDesignation().equalsIgnoreCase("finance manager")) {
			jspPage = "WEB-INF/finance.jsp";
		} */
%>

<jsp:include page="<%=jspPage%>" flush="true" />

<%
	} else {
%>
<script>printMessage("Invalid Username or password","Red");</script>
<jsp:include page="index.jsp"></jsp:include>
<%
	}
%>
