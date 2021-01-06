<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<spring:url value="resources/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
</head>

<body>




	<div class="forms index">
		<h2>Welcome!</h2>
		<h5>Please select an option below:</h5>
		<br/>
		<div class="form-group">
			<button style="width: 100%" class="button"
				onclick="location.href='../agendaApplication/user/login';">Login</button>
		</div>
		<div class="form-group">
			<button style="width: 100%" class="button"
				onclick="location.href='../agendaApplication/user/registration';">Register</button>
		</div>
	</div>

</body>
</html>