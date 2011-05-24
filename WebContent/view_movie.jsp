<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.Trailer" %>
<!--<img src="movie.do?id=${photo.id}"/>

<object data="viewTrailer.do?id=${movie.movieId}" type="video/x-ms-wmv" />
--> 
<% Trailer t = (Trailer)request.getAttribute("currentTrailer");
System.out.println("trailer:"+t.getId()+" " + t.getFileName());
String sr =  "http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/Webapps_Final/images/"  + t.getId() + t.getFileName().substring(t.getFileName().indexOf("."));
String rs = "http://localhost:"+request.getLocalPort()+"/Webapps_Final/images/"  + t.getId() + t.getFileName().substring(t.getFileName().indexOf("."));
if (request.getLocalAddr().equals("0.0.0.0"))
	sr=rs;
%>
		<object type="video/x-ms-wmv" data="<%=sr%>" width="350" height="300" >
        	<param name="src" value="<%=sr%>"/>
        	<param name="autoplay" value="false"/>
        	<param name="controller" value="true"/>
        </object>
        <p align="left"><a href="homepage.do?id=<%=request.getAttribute("owner")%>">Back</a> </p>
<jsp:include page="template-bottom.jsp" />
