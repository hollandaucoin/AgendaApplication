<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<body>
	<br/>
	<!-- Start form and create table -->
	<form:form class="form form-horizontal" method="POST" action="editEvent" modelAttribute="event" style="margin-top: 20px; width: 500px">

		<table>
			<tr valign="bottom">
				<td style="font-size: 28px"><b>Edit Event</b></td>
				<td align="right"><form:button type="submit">Save</form:button>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr align="left" width="500px"></hr></td>
			</tr>
		</table>

		<p style="color: #f97f80">${message}</p>
		<form:errors path="text" style="color: #f97f80" />
		<form:errors path="location" style="color: #f97f80" />
		<form:errors path="description" style="color: #f97f80" />


		<div class="form-group">
			<form:label path="text" class="control-label col-xs-4 labels">Name:</form:label>
			<div class="col-xs-8">
				<form:input path="text" class="form-control"
					placeholder="Enter a name" value="${event.text}" required="required" />
				<form:input type="hidden" path="id" name="eventId" id="eventId" value="${event.id}"/>
			</div>
		</div>

		<div class="form-group">
			<form:label path="start" class="control-label col-xs-4 labels">Start Date:</form:label>
			<div class="col-xs-8">
				<form:input type="datetime-local" class="form-control" path="start" id="start"
					placeholder="Enter a start date and time" value="${event.start}" required="required" />
			</div>
		</div>

		<div class="form-group">
			<form:label path="end" class="control-label col-xs-4 labels">End Date:</form:label>
			<div class="col-xs-8">
				<form:input type="datetime-local" class="form-control" path="end" id="end"
					placeholder="Enter a end date and time" value="${event.end}" required="required" />
			</div>
		</div>

		<div class="form-group">
			<form:label path="location" class="control-label col-xs-4 labels">Location:</form:label>
			<div class="col-xs-8">
				<form:input path="location" class="form-control"
					placeholder="Enter a location" value="${event.location}" required="required" />
			</div>
		</div>

		<div class="form-group">
			<form:label path="description" class="control-label col-xs-4 labels">Description:</form:label>
			<div class="col-xs-8">
				<form:textarea path="description" class="form-control" rows="6"
					cols="44" placeholder="Enter a description" value="${event.description}" />
			</div>
		</div>
	</form:form>
		
		
	
</body>
</html>