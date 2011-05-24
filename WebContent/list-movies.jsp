<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.Movie" %>
<%@ page import="databeans.CurrentSession" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<p>
<% 
	CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
	ArrayList<String> nameList = currentSession.getNameList();
	ArrayList<Integer> idList = currentSession.getIdList();
	ArrayList<String> movieList = currentSession.getMovieList();
	
%>
	<table>
	<th><font size="+2"><%=movieList.size() %> results for "<%=request.getAttribute("search") %>"</font>
	<%
		//HashMap<Integer,String> movieList = (HashMap<Integer,String>)session.getAttribute("movieList");
		for (int index = 0; index<movieList.size();index++) {
	%>
		<tr>
	<%
		if (nameList.size()!=0) {
	%>
			<td><%= nameList.get(index)%></td>
	<% } %>
			<td><a href="displayMovie.do?id=<%= idList.get(index)%>"><%=movieList.get(index)%></a></td>
		</tr>
	<% } %>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
