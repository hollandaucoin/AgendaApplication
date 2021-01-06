<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<head>
		<spring:url value="/resources/ErrorImage.png" var="errorImage" />
		<spring:url value="../resources/main.css" var="mainCss" />
		<link href="${mainCss}" rel="stylesheet" type="text/css" />
</head>
  
<div class="errorPage">
<table>
	<tr>
		<td width="300"><h2>Agenda Application</h2></td>
		<td rowspan=2> <img src="${errorImage}" alt="Error Image" height="75" width="75"> </td>
	</tr>
	<tr>
		<td><p>We have seem to run into a problem processing your request! Please go back and try again!</p></td>
	</tr>
</table>
</div>