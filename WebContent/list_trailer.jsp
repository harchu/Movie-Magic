<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.Trailer" %>
	<table cellpadding="3" cellspacing="7">
	<caption align="center"><font size ="+2" >
		<b> Trailer Gallery
	</b></font></caption> 
	
<%	
    for (Trailer trailer : (Trailer[])request.getAttribute("trailerList")) {
    	String sr =  "http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/Webapps_Final/images/" + trailer.getId() + trailer.getFileName().substring(trailer.getFileName().indexOf("."));
    	String rs = "http://localhost:"+request.getLocalPort()+"/Webapps_Final/images/"  + trailer.getId() + trailer.getFileName().substring(trailer.getFileName().indexOf("."));
    	if (request.getLocalAddr().equals("0.0.0.0"))
    		sr=rs;
%>
		<tr>
		<td><%=trailer.getCaption()%><br>
		<object type="video/x-ms-wmv" data="<%=sr%>" width="350" height="300" >
        	<param name="src" value="<%=sr%>"/>
        	<param name="autoplay" value="false"/>
        	<param name="controller" value="true"/>
        	</object>
        </td>
		</tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />