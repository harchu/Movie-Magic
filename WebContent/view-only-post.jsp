<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.Blog" %>
<%@ page import="databeans.CurrentSession" %>
<%	
	CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
	String blogname = currentSession.getBlogSubject();
	Blog[] blogList = currentSession.getBlogList();
	if(blogname == null){
%>
<p> Please access blogs <a href = "view_blogs.do"> here.</a> </p>
<%	
	}else{
%> 
	
	<table border = "1" width="75%" >
	<caption align="center"><font size ="+2" >
		<b>Thread Topic: <%=blogname%>
	</b></font></caption> 
<%
    for (Blog blog : blogList) {
%>
			<tr>
			<td  bgcolor="#66FFFF"><%=blog.getUserName()%> says:</td></tr>
			<tr>
			<td ><%=blog.getBlogPost().replace("\n", "<br>")%></td>
			</tr>
		
<%
  	}
%>
	</table>
	
<% }
 %>
<jsp:include page="template-bottom.jsp" />