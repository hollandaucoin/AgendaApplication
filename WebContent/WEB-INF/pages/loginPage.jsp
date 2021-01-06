<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<body>
	
	<form:form class="forms form-horizontal index" method="POST" action="loginUser" modelAttribute="userCredential">
		
 			<h2>Login Page</h2>
			<p style="color: #f97f80">${message}</p>
			<form:errors path="username" style="color: #f97f80"/>
			<br/>
			<form:errors path="password" style="color: #f97f80"/>
			
			<div class="form-group">
				<form:label path="username" class="control-label col-xs-4 labels">Username:</form:label> 
				<div class="col-xs-8">
					<form:input path="username" class="form-control" placeholder="Enter username" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<form:label path="password" class="control-label col-xs-4 labels">Password:</form:label> 
				<div class="col-xs-8">
					<form:password path="password" class="form-control" placeholder="Enter password" required="required"/>
				</div>
			</div>
			
			<div class="form-group">
				<form:button>Login</form:button>
			</div>
			

			<p>Don't have an account? <a href="registration" class="link">Register</a>.</p>


<%-- 
 		<table style="text-align: center" class="index">
			<tr>
				<td colspan="2"><h2 style="margin: 0px">Login Page</h2></td>
			</tr>
			<tr>
				<td colspan="2"><p style="color: #f97f80">${message}</p></td>
			</tr>
 			<tr>
				<td colspan="2"><form:errors path="username" style="color: #f97f80"/></td>
			</tr>
			<tr>
				<td colspan="2"><form:errors path="password" style="color: #f97f80"/></td>
			</tr>
			<tr>
				<td class="labels">Username:</td>
				<td><form:input path="username" required="required"/></td>
			</tr>
			<tr>
				<td class="labels">Password:</td>
				<td><form:password path="password" required="required"/></td>
			</tr>
			<tr>
				<td colspan="2"><br/><form:button>Login</form:button><br/><br/><br/></td>
			</tr>
			<tr>
				<td colspan="2"><p>Don't have an account? <a href="registration" class="link">Register</a>.</p></td>
			</tr>
		</table> --%>
	</form:form>


</body>
</html>