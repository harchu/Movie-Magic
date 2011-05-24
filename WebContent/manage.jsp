<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.Movie" %>
<%@ page import="databeans.CurrentSession" %>
<% CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
 
Movie movie = currentSession.getCurrentMovie();%>
<p style="font-size:medium">
	Add a new picture for :
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="post" action="upload.do" enctype="multipart/form-data">
		<table>
			<tr>
				<td>File: </td>
				<td colspan="2"><input type="file" name="file" value=""/></td>
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

<jsp:include page="template-bottom.jsp" />
