<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.User" %>

<p>
<% 
		User currentUser = (User)session.getAttribute("user");
		User[] users = (User[])request.getAttribute("searchedUserList");
%>
	<table>
	<th><font size="+2"><%=users.length %> results for "<%=request.getAttribute("search") %>"</font>
	<%
		for (int index = 0; index<users.length;index++) {
			//if (users[index].getUserName().equals("admin") || users[index].getUserName().equals(currentUser.getUserName())){
			//	continue;
			//}
	%>
		<tr>
			<td><a href="homepage.do?id=<%= users[index].getUserName()%>"><%=users[index].getUserName()%></a></td>
		</tr>
	<% } %>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
