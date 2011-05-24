<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.Blog" %>
<%@ page import="databeans.User" %>
<%@ page import="databeans.CurrentSession" %>
<%	
CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
String blogname = currentSession.getBlogSubject();
Blog[] blogList = currentSession.getBlogList();
	User user = (User)session.getAttribute("user");
	if(user == null ){
%>
	<p> Sorry your are not logged in. To login please <a href = "login.do"> click here.</a> </p>
<%	
	}if(blogname == null){
%> 
<p> Please access blogs <a href = "view_blogs.do">here.</a> </p>
<%	
	}else{
%> 
 <p><jsp:include page="error-list.jsp" />
<p>
	<table border = "1" width="95%">
	<caption align="left"><font size ="+2" >
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
	</table><br>
	<a href = "view_blog_posts.do?reply=true&blogSubject=<%=blogname%>">Reply</a>
<%
	 if (((String)session.getAttribute("reply")).equals("true")) {
%>
			<form method="post" action="replyBlog.do" enctype="multipart/form-data">
			<p>Your Response: </p>
              <p>
                <label>
                <textarea name="replyPost" value="${form.replyPost}" cols="55" rows="10"></textarea>
                </label>
              </p>
              <p>
                <label>
                <input type="submit" name="Submit" value="Submit" />
                </label>
              </p>
            </form>
<%
	} 
%>
</p>
<%
	} 
%>
<jsp:include page="template-bottom.jsp" />
