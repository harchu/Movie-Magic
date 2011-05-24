<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="formbeans.RegisterForm" %>
<p style="font-size:medium">
    To register, enter the following information. (All fields required.)
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="post" action="register.do">
		<input type="hidden" name="redirect" value="${redirect}"/>
		<table>
			<tr>
				<td>First Name: </td>
				<td><input type="text" name="firstName" value="${form.firstName}"/></td>
			</tr>
			<tr>
				<td>Last Name: </td>
				<td><input type="text" name="lastName" value="${form.lastName}"/></td>
			</tr>
			<tr>
				<td>User Name: </td>
				<td><input type="text" name="userName" value="${form.userName}"/></td>
			</tr>
			<tr>
				<td>Date of Birth: (mm-dd-yyyy)</td>
				<td><input type="text" name="dateOfBirth" value="${form.dateOfBirth}"/></td>
			</tr>
			<tr>
				<td>Email: </td>
				<td><input type="text" name="email" value="${form.email}"/></td>
			</tr>
			<tr>
				<td>Gender: </td>
				<td>
				<%RegisterForm rform = (RegisterForm)request.getAttribute("form"); 
				if (rform!=null && rform.getSex()!=null){if (rform.getSex().equals("male")) {%>
				<label>
  					<input type="radio" name="sex" value="male" checked="checked" />Male
  				</label>	
  				<label>
  					<input type="radio" name="sex" value="female" />Female
				</label>
  				<%} else { %>	
  				<label>
  					<input type="radio" name="sex" value="male"/>Male
  				</label>	
  				<label>
  					<input type="radio" name="sex" value="female" checked="checked" />Female
  				</label>
  				<% }} else{ %>
  				<label>
  					<input type="radio" name="sex" value="male" checked="checked" />Male
  				</label>	
  				<label>
  					<input type="radio" name="sex" value="female" />Female
				</label>
				<% } %>
				</td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><input type="password" name="password" value=""/></td>
			</tr>
			<tr>
				<td>Confirm Password: </td>
				<td><input type="password" name="confirm" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Register"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />

