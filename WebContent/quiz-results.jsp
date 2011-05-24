<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.MovieQuiz" %>
<%@ page import="databeans.CurrentSession" %>
<%Integer score = (Integer)request.getAttribute("count");
	String[] ans = new String[5];
	ans = (String[])request.getAttribute("ans");
	CurrentSession currentSession = (CurrentSession)request.getAttribute("session");

	MovieQuiz[] quest = currentSession.getQuizList();
	if(score == null || ans == null || quest == null){
%>
<p> Sorry you need to<a href = playQuiz.do>PLAY</a>the quiz first</p>
<%	
	}else{
%> 
<p><font color = "blue" size="+2"> 
	You scored <%=score%>
</font></p>
<p>
	<form method="post">
	<table>
<%
	
	int answerIndex=0; 
    for (MovieQuiz question : quest) {
    	
    	
%>
		<tr>
			<td><%=question.getQuestion()%></td>
		</tr>
		<tr>
				<td> Answer: 
				<input type="text" name="ans" value="<%=ans[answerIndex]%>"/></td>
		</tr>
<%	if(!question.getAnswer().equalsIgnoreCase(ans[answerIndex])){
%>
		<tr>
			<td><font color = "red">Correct Answer: <%=question.getAnswer()%></font></td>
		</tr>
<%	}
	answerIndex++;	
	}
%>
		
		
	</table>
	</form>
</p>
<%	}
%>
<jsp:include page="template-bottom.jsp" />
