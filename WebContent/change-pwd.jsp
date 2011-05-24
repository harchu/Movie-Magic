<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.User" %>
<%	
	User user = (User)session.getAttribute("user");
	if(user == null ){
%>
	<p> Sorry your are not logged in. To login please <a href = "login.do"> click here.</a> </p>

<%	
	}else{
%> 
<p style="font-size:medium">
	Enter your new password
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="POST" action="change-pwd.do">
		<table>
			<tr>
				<td> New Password: </td>
				<td><input type="password" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td> Confirm New Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Change Password"/>
				</td>
			</tr>
		</table>
	</form>
</p>
<% }
%>

<jsp:include page="template-bottom.jsp" />
