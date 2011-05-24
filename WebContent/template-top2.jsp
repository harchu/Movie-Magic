<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>Photo Sharing Website</title>
	<style>
		.menu-head {font-size: 10pt; font-weight: bold; color: black; }
		.menu-item {font-size: 10pt;  color: black }
    </style>
</head>

<body>
<%@ page import="databeans.User" %>

<table cellpadding="4" cellspacing="0">
    <tr>
	    <!-- Banner row across the top -->
	    <td></td>
        <td width="130" bgcolor="#99FF99">
            <img border="0" src="login2.jpg" height="75">
            <img border="0" src="login3.jpg" height="75"> </td>
        <td bgcolor="#99FF99">&nbsp;  </td>
        <td width="500" bgcolor="#99FF99">
            <p align="center">
<%
	if (request.getAttribute("title") == null) {
%>
		        <font size="7">Photo Sharing Website</font>
<%
    } else {
%>
		        <font size="5"><%=request.getAttribute("title")%></font>
<%
    }
%>
			</p>
		</td>
    </tr>
	
	<!-- Spacer row -->
	<tr>
		<td bgcolor="#99FF99" style="font-size:5px">&nbsp;</td>
		<td colspan="2" style="font-size:5px">&nbsp;</td>
	</tr>
	
	<tr>
		<td bgcolor="#99FF99" valign="top" height="500">
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">
<%
    User user = (User) session.getAttribute("user");
	if (user == null) {
%>				
				<span class="menu-item"><a href="login.do">Login</a></span><br/>
				<span class="menu-item"><a href="register.do">Register</a></span><br/>
				<span class="menu-item"><a href="playQuiz.do">Play Quiz</a></span><br/>
				<span class="menu-item"><a href="view_blogs.do">View All Blogs</a></span><br/>
				<span class="menu-item"><a href="photoGallery.do">Photo Gallery</a></span><br/>
				
<%
    } else {
%>
				<span class="menu-head"><%=user.getFirstName()%> <%=user.getLastName()%></span><br/>
				<span class="menu-item"><a href="add_quiz_question.do">Add Quiz Question</a></span><br/>
				<span class="menu-item"><a href="create_blog.do">Create Blog</a></span><br/>
				<span class="menu-item"><a href="manage.do">Manage Your Photos</a></span><br/>
				<span class="menu-item"><a href="change-pwd.do">Change Password</a></span><br/>
				<span class="menu-item"><a href="playQuiz.do">Play Quiz</a></span><br/>
				<span class="menu-item"><a href="view_blogs.do">View All Blogs</a></span><br/>
				<span class="menu-item"><a href="photoGallery.do">Photo Gallery</a></span><br/>
				<span class="menu-item"><a href="logout.do">Logout</a></span><br/>
				<span class="menu-item">&nbsp;</span><br/>
				<span class="menu-head">Photos From:</span><br/>
<%
    } 
%>
	
			</p>
		</td>
		
		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">
