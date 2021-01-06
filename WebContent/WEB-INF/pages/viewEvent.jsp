<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
<!DOCTYPE html>
<html>

<head>
		<spring:url value="../resources/main.css" var="mainCss" />
		<link href="${mainCss}" rel="stylesheet" type="text/css" />
</head>

<script>

function confirmationDeletion() {
	if(confirm("Are you sure you would like to delete this event?")) {
		document.forms["delete"].submit();
	}
	else {
		
	}
}

//Method to convert the passed in date and time variables into user friendly strings
function editDateTime(passedStartEnd) {
	
	var d = new Date(passedStartEnd);
	
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
	<br/><br/>
			
		<!-- Create table -->
		<table>
			<tr valign="bottom">
				<td style="font-size: 28px"><b>Details</b></td>
				<td align="right" style="vertical-align: bottom !important">
					<!-- Form to edit --> 
					<form:form method="POST" action="edit" modelAttribute="event" style="padding-right: 10px; padding-bottom: 0px; margin: 0px">
						<form:button type="submit">Edit</form:button>
						<input type="hidden" name="eventId" id="eventId" value="${event.id}">
					</form:form>
				</td>
 				<td align="right" style="width: 75px">
					<form:form method="POST" id="delete" action="deleteEvent" modelAttribute="event" style="padding-right: 10px; padding-bottom: 0px; margin: 0px">
						<form:button type="button" value="Delete" onclick="confirmationDeletion()">Delete</form:button>
						<input type="hidden" name="eventId" id="eventId" value="${event.id}">
						<input type="hidden" name="title" id="title" value="All Upcoming Events">
					</form:form>
				</td>
			</tr>
			<tr>
				<td colspan="3"><hr align="left" width="500px"></hr></td>
			</tr>
		</table>

	<form:form class="form form-horizontal" modelAttribute="event" method="POST" style="margin-top: 20px; width: 500px">
		<div class="form-group">
			<form:label path="text" class="control-label col-xs-4 labels">Name:</form:label>
			<div class="col-xs-8" align="left">
				<p>${event.text}</p>
			</div>
		</div>

		<div class="form-group">
			<form:label path="start" class="control-label col-xs-4 labels">Start Date:</form:label>
			<div class="col-xs-8" align="left">
				<p id="startDateTime"></p>
			</div>
			<script type="text/javascript">
				document.getElementById("startDateTime").innerHTML = editDateTime('${event.start}');
			</script>
		</div>

		<div class="form-group">
			<form:label path="end" class="control-label col-xs-4 labels">End Date:</form:label>
			<div class="col-xs-8" align="left">
				<p id="endDateTime"></p>
			</div>
			<script type="text/javascript">
				document.getElementById("endDateTime").innerHTML = editDateTime('${event.end}');
			</script>
		</div>

		<div class="form-group">
			<form:label path="location" class="control-label col-xs-4 labels">Location:</form:label>
			<div class="col-xs-8" align="left">
				<p>${event.location}</p>
			</div>
		</div>

		<div class="form-group">
			<form:label path="description" class="control-label col-xs-4 labels">Description:</form:label>
			<div class="col-xs-8" align="left">
				<p>${event.description}</p>
			</div>
		</div>
	</form:form>
			
			
			
</body>


</html>