<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.Movie" %>
<%@ page import="databeans.Trailer" %>
<%@ page import="databeans.Photo" %>
<%@ page import="databeans.MovieCrew" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="databeans.CurrentSession" %>
<%
	CurrentSession currentSession = (CurrentSession)request.getAttribute("session");
	
	Movie movie = currentSession.getCurrentMovie();
	float avgRating = currentSession.getAvgRating();
	Trailer trailer = currentSession.getCurrentTrailer();
	Photo photo = currentSession.getCurrentPhoto();
	ArrayList<String> actors = currentSession.getCurrentActors();
	ArrayList<String> actresses = currentSession.getCurrentActresses();
	ArrayList<String> directors = currentSession.getCurrentDirectors();
	ArrayList<String> producers = currentSession.getCurrentProducers();
    //Movie movie = (Movie)session.getAttribute("currentMovie"); 
	//float avgRating = (Float)session.getAttribute("avgRating");
	//Trailer trailer = (Trailer)session.getAttribute("currentTrailer");
	//Photo photo = (Photo)session.getAttribute("currentPhoto");
	//ArrayList<String> actors = (ArrayList<String>)session.getAttribute("currentActors");
	//ArrayList<String> actresses = (ArrayList<String>)session.getAttribute("currentActresses");
	//ArrayList<String> directors = (ArrayList<String>)session.getAttribute("currentDirectors");
	//ArrayList<String> producers = (ArrayList<String>)session.getAttribute("currentProducers");
%>
<p><p align="left"><font size ="+2" ><b><%=movie.getMovieName().toUpperCase()%></b></font> </p>
	
	
	
	
	<table width="500" height="280" border="0">
<tr>
<td rowspan="1" >
<%if(photo == null){ %>
<img src="NoPhotoAvailable.jpg" width = "150" height="200"/>
<%}else{ %>
<img src="image.do?id=<%=photo.getId()%>" width = "150" height="200"/>
<% } %>
</td>
<td width="350" height="300" >
<% if(trailer != null){
	String sr =  "http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/Webapps_Final/images/"  + trailer.getId() + trailer.getFileName().substring(trailer.getFileName().indexOf("."));
%>

<object type="video/x-ms-wmv" data="<%=sr%>" width="350" height="300" >
<param name="src" value="<%=sr%>"/>
<param name="autoplay" value="false"/>
<param name="controller" value="true"/>
</object>
<%} %>
</td>
</tr></table>

        
        
        
        
        
        <table width="600" border="0">
        <tr>
        <td>Movie Rating:</td><td><div class="rating_bar">
  		<div style="width:<%=avgRating*20%>%"></div>
		</div>
		</td></tr>
</tr>
<tr>
    <td width="100">
    Cast: </td><td>
<%
    for (String entry : actors) {
%>
<%=entry%><br>
<%
    }  for (String entry : actresses) {
%>
<%=entry%><br>
<%
    }
%>
 
  </td>
  </tr>
  </tr>
  <tr>
    <td>Director:</td>
    <td>
    <%
    for (String entry : directors) {
	%>
	<%=entry%>
	<%
    	}
	%>  
    </td>
  </tr>
  <tr>
    <td>Producer:</td>
    <td>
	<%
    for (String entry : producers) {
	%>
	<%=entry%>
	<%
    	}
	%>  
	</td>
  </tr>
  <tr>
    <td>Certification:</td>
    <td><%=movie.getCertification()%> </td>
  </tr>
  <tr>
    <td>Genre:</td>
    <td><%=movie.getGenre()%> </td>
  </tr>
  <tr>
    <td>Release Date: </td>
    <td><%=movie.getReleaseDate()%> </td>
  </tr>
  <tr>
    <td>Plot Summary: </td>
    <td><%=movie.getPlotSummary()%> </td>
  </tr>
  <tr>
    <td>Run Time: </td>
    <td><%=movie.getRunningTime()%> </td>
  </tr>
  <tr>
    <td>Language:</td>
    <td><%=movie.getLanguage()%> </td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp; </p>
</body>
</p>

