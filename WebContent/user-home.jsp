<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.UserBlog" %>
<p> My Blogs:
	<table> 
<%
    for (UserBlog userBlog : (UserBlog[])request.getAttribute("userBlogList")) {
%>
		<tr>
			<td><a href="view_blog_posts.do?blogSubject=<%=userBlog.getBlogSubject()%>"><%=userBlog.getBlogSubject()%></a></td>
		</tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
