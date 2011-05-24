<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.User" %>
<%	
	User user1 = (User)session.getAttribute("user");
	if(user1 == null ){
%>
	<p> Sorry your are not logged in. To login please <a href = "login.do"> click here.</a> </p>

<%	
	}else{
%> 
<%@ page import="databeans.Wall" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.MovieDAO" %>
<%@ page import="databeans.Photo" %>
<%@ page import="databeans.UserBlog" %>
<%@ page import="databeans.UserMovie" %>
<%@ page import="databeans.Trailer" %>
<%@ page import="databeans.CurrentSession" %>
<%
	CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
	HashMap<String,String> movieReview = currentSession.getMovieReview();

	String user = currentSession.getPageOwner(); 
	Wall[] toWall = currentSession.getUserPosts();
	
	UserBlog[] blogList = currentSession.getUserBlogList();
	UserMovie[] user_reviews = currentSession.getUser_reviews();
	Photo[] user_photos = currentSession.getUser_photos();
	Trailer[] user_trailer = currentSession.getUser_trailer();
%>
<p><p align="left"><font size ="+2" ><b><%=user%>'s Homepage
</b></font>
</p>
<jsp:include page="error-list.jsp" />
<p><p align="left"><font size ="+1" ><b>Wall </b></font></p>
<table width = "80%" border="1" >
	<tr>
		<form id="form1" name="form1" method="post" action="homepage.do" >
		<input type="hidden" name="toName" value="<%=user%>">
		<label>
                <textarea name="post" value="" cols="80%" rows="3"></textarea>
        </label> <div align = "right">
         <label >
    <input type="submit" name="button" value="Scratch" />
    </label></div>
        </form>
	</tr>
<%
	if(toWall.length != 0){
		int i=0;
		Wall userpost;
    for (i=toWall.length-1; i>=0;i-- ) {
    	userpost = toWall[i];
%>
		<tr ><td bgcolor = "#CCFF33">
			<%=userpost.getFromSender()%> says: </td>
			</tr>
		<tr><td><%=userpost.getPost().replace("\n", "<br>")%></td>
		</tr>
<%} %>
</table>
<hr>

<% }if(blogList.length != 0){ %>

<p><p align="left"><font size ="+1" ><b>Blogs</b></font></p>

<table> 
	<%
 	   for (UserBlog userBlog : blogList) {
	%>
			<tr>
				<td><a href="view_blog_posts.do?blogSubject=<%=userBlog.getBlogSubject()%>"><%=userBlog.getBlogSubject()%></a></td>
			</tr>
	<%}%>
</table>
<hr>
<%}if(movieReview.size() != 0){

%>
<p align="left"><font size ="+1" ><b>Movie Reviews</b></font></p>
<table width = "80%" border="1">

<%
	int i=0;
	String movie;
	String review;
	for ( i=0; i< movieReview.size();i++ ) {
	movie = movieReview.keySet().toArray()[i].toString();
	review = movieReview.get(movie);
%>
	<tr><td bgcolor = "#FF66FF">
		<b>Movie Name: <%=movie%></b> </td></tr>
	<tr><td><%=review.replace("\n", "<br>")%></td>
	</tr>
<%
}
%>

</table>
<hr>
<%	}if(user_trailer.length != 0){
%>
<p><p align="left"><font size ="+1" ><b>Trailers</b></font></p>
<table>
<%
    for (Trailer trailer : user_trailer) {
%>
		<tr>
			<td><a href="viewTrailer.do?id=<%=trailer.getCaption()%>"><%=trailer.getCaption()%></a></td>
		</tr>
<% } %>
</table>
<hr>
<% }if(user_photos.length != 0){
%>
<p><p align="left"><font size ="+1" ><b>Photos</b></font></p>

<table cellpadding="7" cellspacing="3" width = "70%">
	<tr>
<%
for (int j=0;j<user_photos.length;j=j+3) {
	System.out.println("photo is = " + user_photos[j].getId());
%>
<tr>
<%
	for (int i=0;i<3;i++){if ((j+i)>=user_photos.length){break;}
%>
	
		<td align="center" width ="180" height = "220">
	
	<a href="displayMovie.do?id=<%=user_photos[j+i].getMovieId()%>">
	<img src="image.do?id=<%=user_photos[j+i].getId()%>" width = "120" height="180"/>
	 </a><br>
	 <b><%=user_photos[j+i].getCaption()%></b>
	 </td>
<%} %>
	</tr>
<%
	}
%>
	</tr>
</table>

<hr>
<%}
%>

<% }
%>
<jsp:include page="template-bottom.jsp" />