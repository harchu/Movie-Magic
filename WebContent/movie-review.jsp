<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.Movie" %>
<%@ page import="databeans.User" %>
<%@ page import="databeans.CurrentSession" %>
<%@ page import="formbeans.ReviewForm" %>
<%@ page import="model.Model" %>
<%	
	User user = (User)session.getAttribute("user");
	if(user == null ){
%>
	<p> Sorry your are not logged in. To login please <a href = "login.do"> click here.</a> </p>
<%	
	}else{
%> 
<%CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
Movie movie = currentSession.getCurrentMovie();
ReviewForm rform = (ReviewForm)request.getAttribute("form"); 
%>
<p><jsp:include page="error-list.jsp" />
</p>
<p align="left"><font size ="+2" ><b><%= movie.getMovieName()%></b></font> </p>
<span class="style2"></span>
<p class="style3">Rating : </p>
<form name="form" method="post" action="movieReview.do">
  <p>
    <% if (rform!=null && rform.getRating()!=null){%>
    <label>
    <% if (rform.getRating().equals("5")){%>
      <input type="radio" name="rating" value="5" checked="checked">Excellent
      	<%} else { %>
      <input type="radio" name="rating" value="5">Excellent
      	<%} %>
    </label>
    <br>
    <label>
      	<%if (rform.getRating().equals("4")){%>
      <input type="radio" name="rating" value="4" checked="checked">Good
      	<%} else { %>
      <input type="radio" name="rating" value="4">Good
      	<%} %>
    </label>
    <br>
    <label>
    	<%if (rform.getRating().equals("3")){%>
      <input type="radio" name="rating" value="3" checked="checked">O.K.
      	<%} else { %>
      <input type="radio" name="rating" value="3">O.K.
      	<%} %>
    </label>
    <br>
    <label>
    	<%if (rform.getRating().equals("2")){%>
      <input type="radio" name="rating" value="2" checked="checked">Bad
      	<%} else { %>
      <input type="radio" name="rating" value="2">Bad
      	<%} %>
    </label>
    <br>
    <label>
    	<%if (rform.getRating().equals("1")){%>
      <input type="radio" name="rating" value="1" checked="checked">Very Bad
      	<%} else { %>
      <input type="radio" name="rating" value="1">Very Bad
      	<%} %>
    </label>
    <br>
    <%} else {%>
    <label>
      <input type="radio" name="rating" value="5">
      Excellent</label>
    <br>
    <label>
      <input type="radio" name="rating" value="4">
      Good</label>
    <br>
    <label>
      <input type="radio" name="rating" value="3" checked="checked">
      O.K.</label>
    <br>
    <label>
      <input type="radio" name="rating" value="2">
      Bad</label>
    <br>
    <label>
      <input type="radio" name="rating" value="1">
      Very Bad</label>
    <br>
    <%} %>
  </p>

<p>
  <label><span class="style3">Review:</span><br />
<textarea name="review" cols="70" rows="15"></textarea>  
</label>
</p>
<p>&nbsp; </p>
<p>
  <label>
  <input type="submit" name="Submit" value="Submit" />
  </label>
</p>
</form>
<% 	}
 %>
<jsp:include page="template-bottom.jsp" />