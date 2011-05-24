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
	<p> Sorry only an administrator can access this page.<br>
	To login as administrator please <a href = "login.do"> click here.</a> </p>
<%	
	}else{
%> 
              <div align="center"><p> Add Movie Details </p> 
              <p align="left"><label></label>
              </p>
<jsp:include page="error-list.jsp" />

<p>
	<form method="post" action = "addMovie.do">
	<input type="hidden" name="redirect" value="${redirect}"/>
              <table width="420" border="0">
                <tr>
                  <td width="140"><span class="style9">Movie Name </span></td>
                  <td width="154"><label>
                    <input type="text" name="movieName" value="${form.movieName}" />
                  </label></td>
                </tr>
				<tr>
                  <td><span class="style9">Release Date </span></td>
                  <td><label>
                    <input type="text" name="releaseDate" value="${form.releaseDate}" />
                  </label></td>
                </tr>
                <tr>
                  <td><span class="style9">Genre</span></td>
                  <td><label>
                    <input type="text" name="genre" value="${form.genre}" />
                  </label></td>
                </tr>
                <tr>
                  <td><span class="style9">Plot Summary </span></td>
                  <td><label>
                    <input type="text" name="plotSummary" value="${form.plotSummary}" />
                  </label></td>
                </tr>
                <tr>
                  <td><span class="style9">Run Time (in minutes)</span></td>
                  <td><label>
                    <input type="text" name="runTime" value="${form.runTime}" />
                  </label></td>
                </tr>
                <tr>
                  <td><span class="style9">Language</span></td>
                  <td><label>
                    <input type="text" name="language" value="${form.language}" />
                  </label></td>
                </tr>
                <tr>
                  <td><span class="style9">Certification </span></td>
                  <td>
                  <label>
  					<input type="radio" name="certification" value="G " />
					G  </label>
  				  <label>
  					<input type="radio" name="certification" value="PG " />
					PG</label>
               	  <label>
  					<input type="radio" name="certification" value="PG-13 "/>
					PG-13  </label> 
                   <label>
  					<input type="radio" name="certification" value="R " />
					R  </label>
				   <label>
  					<input type="radio" name="certification" value="NC-17" />
					NC-17  </label>
                  </td>              
              </table>
              <p>
                <label>
                <input type="submit" name="Submit" value="Submit" />
                </label>
              </p>
              </form>
</p>
<%	}
%>
<jsp:include page="template-bottom.jsp" />
