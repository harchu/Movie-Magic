<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!(empty errors)}">
		<p style="font-size:medium; color:red">
				<c:forEach var="entry" items="${errors}">
					${entry}
					<br>
				</c:forEach>
			</p>
</c:if>
