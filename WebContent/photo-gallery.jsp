<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.User" %>
<%@ page import="databeans.Photo" %>
<p align ="center"><font size="+2"><b> Photo Gallery </font>
	<table cellpadding="7" cellspacing="3">
		
<%
	User user = (User)session.getAttribute("user");
	Photo[] photoList = (Photo[])request.getAttribute("photoList");
	System.out.println(photoList.length);
    for (int j=0;j<photoList.length;j=j+3) {
    	System.out.println("photo is = " + photoList[j].getId());
%>
<tr>
<%
    	for (int i=0;i<3;i++){if ((j+i)>=photoList.length){break;}
%>
		
			<td align="center" width ="180" height = "220">
		
		<a href="displayMovie.do?id=<%=photoList[j+i].getMovieId()%>">
		<img src="image.do?id=<%=photoList[j+i].getId()%>" width = "120" height="180"/>
		 </a><br>
		 <b><%=photoList[j+i].getCaption()%></b>
		 <% if( user!= null && user.getUserName().equals("admin")){%>
		 
		 <form method="POST" action="remove.do">
                    <input type="hidden" name="id" value="<%=photoList[j+i].getId()%>"/>
                    <input type="submit" value="X"/>
         </form>
         <%} %>
		 </td>
<%} %>
		</tr>
<%
		}
%>
		</tr>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
