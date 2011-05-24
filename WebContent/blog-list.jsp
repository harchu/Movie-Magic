<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.UserBlog" %>
<p>
	<table width="40%" >
	<caption ><font size ="+2" >
		<b>Current Active Blogs 
	</b></font></caption> 
	<tr><th>Index</th><th>Blog Subject</th><th>Started By</th>
	</tr>
<%
	int i=0;
    for (UserBlog userBlog : (UserBlog[])request.getAttribute("userBlogList")) {
    	i++;
%>
		<tr>
			<td align = "center"><%= i  %></td>
			<td align = "center"v><a href="view_blog_posts.do?blogSubject=<%=userBlog.getBlogSubject()%>"><%=userBlog.getBlogSubject()%>
			</a></td>
			<td align = "center"><%=userBlog.getUserName()%></td>
		</tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
