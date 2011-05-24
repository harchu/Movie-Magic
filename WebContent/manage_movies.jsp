<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.Movie" %>
<%@ page import="databeans.User" %>
<%@ page import="databeans.CurrentSession" %>
<%	
	User user = (User)session.getAttribute("user");
	if(user == null ){
%>
	<p> Sorry your are not logged in. To login please <a href = "login.do"> click here.</a> </p>
<%	
	}else{
%> 
 <p><jsp:include page="error-list.jsp" />
	</p>
<%	CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
 System.out.println("now"+currentSession);
Movie movie = currentSession.getCurrentMovie(); %>
<p>
	<form name="addimageform" enctype="multipart/form-data" method="POST" action = "uploadTrailer.do">
		 <table width="310" border="0">
		 <caption ><font size ="+2" >
			<b> Trailer for <%=movie.getMovieName()  %>:
			</b></font></caption> 
			<tr>
				<td>File: </td>
				<td colspan="1"><input type="file" name="file" value=""/></td>
			</tr>
			<tr>
				<td>Caption*: </td>
				<td><input type="text" name="caption" value="${form.caption}"/></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" name="button" value="Upload File"/>
				</td>
			</tr>
		</table>
	</form>
</p>
<% 	}
 %>

<jsp:include page="template-bottom.jsp" />
