<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.Photo" %>
<p>
	<table>
<%
    for (Photo photo : (Photo[])request.getAttribute("photoList")) {
%>
		<tr>
			<td><a href="view.do?id=<%=photo.getId()%>"><%=photo.getCaption()%></a></td>
		</tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
