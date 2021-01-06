<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
<!DOCTYPE html>
<html>

<head>
		<spring:url value="../resources/main.css" var="mainCss" />
		<link href="${mainCss}" rel="stylesheet" type="text/css" />
</head>

<script>
	function confirmationDeletion(eventId) {
		if(confirm("Are you sure you would like to delete this event?")) {
			document.forms["delete" + eventId].submit();
		}
		else {
			
		}
	}
	
	// Method to convert the passed in date and time variables into user friendly strings
	function editDateTime(passedStartDateTime) {
		
		var d = new Date(passedStartDateTime);
		
		var date = d.getMonth()+1 + "/" + d.getDate() + "/" + d.getFullYear();
		
		var hours = d.getHours();
		var minutes = d.getMinutes();
		
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		
		var newTime;
		
		// If the hour (position 0 of array) is greater than 12, mod it by 12 and add PM
		if(hours > 12) {
			newTime = hours % 12;
			newTime = newTime + ":" + minutes + "PM";
		}
		// If the hour is 12 add PM
		else if (hours == 12) {
			newTime = hours + ":" + minutes + "PM";
		}
		// If the hour is 0 make it 12
		else if (hours == 0) {
			newTime = "12:" + minutes + "AM";
		}
		// Else, the hour is less than 12 and add AM
		else {
			newTime = hours + ":" + minutes + "AM";
		}
		
		// Return date and time in new format
        return date + "  at  " + newTime;
       
	}
</script>

<body>
	<br/>
	
		<!-- Create table for title and add button-->
		<table style="border-spacing:8px">
			<tr valign="bottom">
				<br/>
				<td style="font-size:28px"><p><b>${title}</b></p></td>
				<td align="right">
					<button><a href="../events/new" style="text-decoration: none; color: black; padding-right: 5; padding-left: 5">+</a></button>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr align="left" width="500px"></hr></td>
			</tr>
			<tr>
				<td colspan="2"><p style="color: #a2a1a3">${message}</p></td>
			</tr>
		</table>

		<table style="border-spacing:8px">
			<!-- Iterate through all events -->					
			<c:forEach var="event" items="${events}">
						<tr>
							<form:form method="POST" action="viewEvent" modelAttribute="events">
								<input type="hidden" name="eventId" id="eventId" value="${event.id}">
									<td align="left">
										<input type="submit" class="eventButtons" value="${event.text}">
									</td>
									<td align="right">	
										<input type="submit" class="eventButtons" id="id${event.id}" value="">
										<script type="text/javascript">
											document.getElementById("id${event.id}").value = editDateTime('${event.start}');
										</script>
									</td>
							</form:form>
							
							<form:form method="POST" name="deleteForm" id="delete${event.id}" action="deleteEvent" modelAttribute="events">
								<td align="right">
									<input type="button" class="delete" value="x" onclick="confirmationDeletion('${event.id}')"/>
									<input type="hidden" name="eventId" id="eventId" value="${event.id}">
									<input type="hidden" name="title" id="title" value="${title}">
								</td>
							</form:form>
						</tr>
						<tr>
							<td colspan="3"><hr align="left" width="475px"></hr></td>
						</tr>
			</c:forEach>
			
		</table>

</body>


</html>