<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<body>
	<form:form class="forms form-horizontal index" method="POST" action="registerUser" modelAttribute="user">
	
	
 		<h2>Registration Page</h2>
		<p style="color: #f97f80">${message}</p>
		<form:errors path="firstName" style="color: #f97f80"/>
		<form:errors path="lastName" style="color: #f97f80"/>
		<form:errors path="UserCredentials.username" style="color: #f97f80"/>
		<form:errors path="UserCredentials.password" style="color: #f97f80"/>
		<form:errors path="email" style="color: #f97f80"/>
		<form:errors path="phoneNumber" style="color: #f97f80"/>
		
		<div class="form-group" style="margin-bottom: 10px">
			<form:label path="firstName" class="control-label col-xs-5 labels">First Name:</form:label> 
			<div class="col-xs-7">
				<form:input path="firstName" class="form-control" placeholder="Enter first name" required="required"/>
			</div>
		</div>
		<div class="form-group" style="margin-bottom: 10px">
			<form:label path="lastName" class="control-label col-xs-5 labels">Last name:</form:label> 
			<div class="col-xs-7">
				<form:input path="lastName" class="form-control" placeholder="Enter last name" required="required"/>
			</div>
		</div>
		<div class="form-group" style="margin-bottom: 10px">
			<form:label path="UserCredentials.username" class="control-label col-xs-5 labels">Username:</form:label> 
			<div class="col-xs-7">
				<form:input path="UserCredentials.username" class="form-control" placeholder="Enter username" required="required"/>
			</div>
		</div>
		<div class="form-group" style="margin-bottom: 10px">
			<form:label path="UserCredentials.password" class="control-label col-xs-5 labels">Password:</form:label> 
			<div class="col-xs-7">
				<form:password path="UserCredentials.password" class="form-control" placeholder="Enter password" required="required"/>
			</div>
		</div>
		<div class="form-group" style="margin-bottom: 10px">
			<form:label path="email" class="control-label col-xs-5 labels">Email:</form:label> 
			<div class="col-xs-7">
				<form:input path="email" class="form-control" placeholder="Enter email" required="required"/>
			</div>
		</div>
		<div class="form-group" style="margin-bottom: 10px">
			<form:label path="phoneNumber" class="control-label col-xs-5 labels">Phone:</form:label> 
			<div class="col-xs-7">
				<form:input path="phoneNumber" class="form-control" placeholder="Enter phone number" required="required"/>
			</div>
		</div>
		
		<div class="form-group">
			<form:button>Register</form:button>
		</div>
		

		<p>Already have an account? <a href="login" class="link">Login</a>.</p>
	
	</form:form>
</body>
</html>