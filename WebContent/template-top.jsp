<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>MOVIE MAGIC</title>
	<style>
		.menu-head {font-size: 10pt; font-weight: bold; color: black; }
		.menu-item {font-size: 10pt;  color: black }
    </style>
    	<style>
.rating_bar {
  width: 55px;
  background: url(star_x_grey.gif) 0 0 repeat-x;
}

.rating_bar div {
  height: 12px;
  background: url(star_x_orange.gif) 0 0 repeat-x;
}
</style>
</head>

<body>
<%@ page import="databeans.User" %>

<table border="0" cellpadding="4" cellspacing="0">
  
 <%
 User user = (User) session.getAttribute("user");
 %>
  

  <tr>
  <td colspan="4" bgcolor="#FFFFFF"><div align="center">
        <p>
<%
	if (request.getAttribute("title") == null) {
%>
		        <font size="6" color = "#CC3300">MOVIE MAGIC</font>
		      
<%
    } else {
%>
		        <font size="5"><%=request.getAttribute("title")%></font>
<%
    }
%>	
<%  if(user == null){ %>
		<div align="left"><a href="login.do">Login</a></div>
	<% }else if(user.getUserName().equals("admin")){ %>
	
		 <div align="left"> Welcome admin, <a href="logout.do">Logout</a></div>
	<%  }else{ %>
		 <div align="left"> Welcome <a href="homepage.do"><%=user.getFirstName()%></a>, <a href="logout.do">Logout</a></div>
	<%  } %>
 		 
 	<hr>
 		 <hr>
	</p></div>
	</td>
	
    </tr>
    <tr><td colspan ="4"></td></tr>
	
	<!-- Spacer row -->
	
	
	<tr>
	
		<td bgcolor="#FFFFFF" valign="top" height="900" >
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">
<%
   
	if (user == null) {
%>		
	
    <table width="160"  border="1" cellpadding="3" cellspacing="2" id="navigation">
     <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="home.do">Home </a></div></td>
      </tr>
      <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="searchMovie.do">Search Movies </a></div></td>
      </tr>
      <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="playQuiz.do">Play Movie Quiz</a></div></td>
      </tr>
	   <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="view_blogs.do">View All Blogs</a></div></td>
      </tr>
	   <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="photoGallery.do">Photo Gallery</a></div></td>
      </tr>
       <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="listTrailer.do">Trailer Gallery</a></div></td>
      </tr>
      <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="rss.do" target="_blank">Weekend Top 10 Movies</a></div></td>
      </tr>
	 </table>
     

<%
    } else {
%>


<table width="160"  border="1" cellpadding="4" cellspacing="2" id="navigation">
     <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="home.do">Home </a></div></td>
      </tr>
      <tr>
        <td  height="40" bordercolor="#000033"><div align="center"><a href="change-pwd.do">Change Password </a></div></td>
      </tr>
       <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="searchMovie.do">Search Movies </a></div></td>
      </tr>
      <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="searchUsers.do">Search Users </a></div></td>
      </tr>
      <% if(user.getUserName().equals("admin")){ %>
		<tr>
        <td  height="40" bordercolor="#000033"><div align="center"><a href="addMovie.do">Add Movie</a></div></td>
      </tr>
		 
	<%  } %>
      
      <tr>
        <td  height="40" bordercolor="#000033"><div align="center"><a href="playQuiz.do">Play Movie Quiz</a></div></td>
      </tr>
      <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="add_quiz_question.do">Add Questions for Quiz </a></div></td>
      </tr>
     <tr>
        <td  height="40" bordercolor="#000033"><div align="center"><a href="createBlog.do">Create a Blog</a></div></td>
      </tr>
	   <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="view_blogs.do">View All Blogs</a></div></td>
      </tr>
       
	   <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="photoGallery.do">Photo Gallery</a></div></td>
      </tr>
       <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="listTrailer.do">Trailer Gallery</a></div></td>
      </tr>
      <tr>
        <td height="40" bordercolor="#000033"><div align="center"><a href="rss.do" target="_blank">Weekend Top 10 Movies</a></div></td>
      </tr>
	 </table>

<%
    } 
%>
	
	</p>
		</td>
	
		<td  valign="top" width = "10000"  >
