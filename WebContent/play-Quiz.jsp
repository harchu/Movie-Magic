<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.MovieQuiz" %>
<%@ page import="databeans.CurrentSession" %>
<%
CurrentSession currentSession = (CurrentSession)request.getAttribute("session");

	MovieQuiz[] quest = currentSession.getQuizList();
	if(quest == null){
%>
<p> Sorry you need to<a href = playQuiz.do>PLAY</a>the quiz first</p>
<%	
	}else{
%> 
<p>
	<form method="post" action = "evaluateQuiz.do">
	<table cellpadding="4" cellspacing="2">
<%
	
	int answerIndex=0;
    for (MovieQuiz question : quest) {
    	answerIndex++;	
%>
		<tr>
			<td>Q<%=answerIndex%>. <%=question.getQuestion()%></td>
		</tr>
		<tr>
				<td> Answer: <input type="text" name="<%="answer"+answerIndex%>" value=""/></td>
		</tr>
		
<%
	}
%>
		<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Finish"/>
				</td>
		</tr>
		
	</table>
	</form>
</p>

<%
}
%>
<jsp:include page="template-bottom.jsp" />