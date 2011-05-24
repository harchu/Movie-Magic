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
 <p><jsp:include page="error-list.jsp" />
	</p>
        <form method="post" action="createBlog.do" enctype="multipart/form-data">
         <table width="310" border="0">
              <caption ><font size ="+2" >
			<b> Create A Blog
			</b></font></caption> 
                <tr>
                </tr>
                <tr>
                  <td>Subject:         
                  <label>
                    <input type="text" name="blogSubject" value="${form.blogSubject}"/>
                  </label></td>
                </tr>
              <tr>
              <td>Blog Post: 
                <label>
                <textarea  name="blogPost" value="${form.blogPost}" cols="55" rows="10"></textarea>
                </label></td>
                </tr>
            </table>
              <p>
                <label>
                <input type="submit" name="Submit" value="Submit" />
                </label>
              </p>
            </form>
<% 	}
 %>
<jsp:include page="template-bottom.jsp" />