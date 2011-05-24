<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.User" %>
<%	
	User user = (User)session.getAttribute("user");
	if(user == null ){
%>
	<p> Sorry your are not logged in. To login please <a href = "login.do"> click here.</a> </p>
<%	
	}else{
%> 
<p><jsp:include page="error-list.jsp" />
	</p>
<form method="post" action="add_quiz_question.do" >   
     <table width = "70%">
		<caption ><font size ="+2" >
		<b> Add Movie Question
	</b></font></caption> 
                <tr>
                  <td align="center">Question
                 <label>
                    <input type="text" name="question" value="${form.question}"/>
                  </label></td>
                </tr>
				<tr>
                  <td align="center">Answer
                 <label>
                    <input type="text" name="answer" value="${form.answer}"/>
                  </label></td>
                </tr>
                <tr>
             <td colspan="3" align="center">
					 <input type="submit" name="Submit" value="Submit" />
				</td>
             </tr>
             
            </table>    
             </form>
 <% 	}
 %>
<jsp:include page="template-bottom.jsp" />
