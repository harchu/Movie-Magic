<!--
Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
For the complete license, please refer to the root-level license.txt document
-->

<jsp:include page="template-top.jsp" />
<%@ page import="databeans.User" %>
<%	
	User user = (User)session.getAttribute("user");
	if(user == null || !user.getUserName().equals("admin")){
%>
	<p> Sorry only an administrator can access this page. <br>
	To login as administrator please <a href = "login.do"> click here.</a> </p>
<%	
	}else{
%> 
<div align="center">
                <p> Movie Cast Details </p> 
                <p align="left"><label></label>
</p>
 <jsp:include page="error-list.jsp" />
<p>
	<form method="post" action = "addMovieCrew.do">
	<input type="hidden" name="redirect" value="${redirect}"/>
              <table width="465" border="0">
                <tr>
                  <td width="113"><p>Director 1:</p>
                  <p>Director 2:  </p></td>
                  <td width="342"><label>
                    <input type="text" name="director1" value="${form.director1}"/>
                    <br />
                    <br />
                    <input type="text" name="director2" value="${form.director2}"/>
                  </label></td>
                </tr>
                <tr>
                  <td><p>Actor 1:</p>
                    <p>Actor 2:</p>
                  <p>Actor 3:   </p></td>
                  <td><label>
                    <input type="text" name="actor1" value="${form.actor1}"/>
                    <br />
                    <br />
                    <input type="text" name="actor2" value="${form.actor2}"/>
                    <br />
                    <br />
                    <input type="text" name="actor3" value="${form.actor3}"/>
                  </label></td>
                </tr>
				<tr>
                  <td height="117"> <p>Lead Actress 1:</p>
                  <p>Lead Actress 2:</p>
                  <p>Lead Actress 3: </p></td>
                  <td><label>
                    <input type="text" name="actress1" value="${form.actress1}"/>
                    <br />
                    <br />
                    <input type="text" name="actress2" value="${form.actress2}"/>
                    <br />
                    <br />
                    <input type="text" name="actress3" value="${form.actress3}"/>
                  </label></td>
                </tr>
				<tr>
                  <td><p class="style9">Producer 1: </p>
                  <p class="style9">Producer 2:</p>                  </td>
                  <td><label>
                    <input type="text" name="producer1" value="${form.producer1}"/>
                    <br />
                    <br />
                    <input type="text" name="producer2" value="${form.producer2}"/>
                  </label></td>
                </tr>
              </table>
              <p>
                <label>
                <input type="submit" name="Submit" value="Submit" />
                </label>
              </p>
              </form>
</p>
<% 	}
 %>
<jsp:include page="template-bottom.jsp" />
