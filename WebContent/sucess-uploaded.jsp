<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.CurrentSession" %>
<%	
	CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
%>
<p style="font-size:medium"><font size="+1">
     <%=request.getAttribute("message") %></font>
</p>
 <p align="left"><a href="displayMovie.do?id=<%=currentSession.getCurrentMovie().getMovieId()%>">Back</a> </p>
<jsp:include page="template-bottom.jsp" />