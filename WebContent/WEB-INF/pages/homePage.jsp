<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-2.2.2.min.js"></script>
<!-- Include Mobiscroll -->
<script src="../resources/js/mobiscroll.jquery.min.js"></script>
<link href="../resources/css/mobiscroll.jquery.min.css" rel="stylesheet"
	type="text/css">

<script>
    
    $(function () {
    	
    	fetch('http://localhost:8080/agendaApplication/service/userEvents')
        .then(function(response){
            return response.json();
        })
        .then(function(eventData){
        	
        	for (i = 0; i < eventData.length; i++) {
        		
        		var start = new Date(eventData[i].start.year, eventData[i].start.monthValue-1, eventData[i].start.dayOfMonth, 
        				eventData[i].start.hour-8, eventData[i].start.minute, eventData[i].start.second, eventData[i].start.nano);
        		
        		eventData[i].start = start.toISOString();
        		
        		
        		var end = new Date(eventData[i].end.year, eventData[i].end.monthValue-1, eventData[i].end.dayOfMonth, 
        				eventData[i].end.hour-8, eventData[i].end.minute, eventData[i].end.second, eventData[i].end.nano);
        		
        		eventData[i].end = end.toISOString();
        		
        		eventData[i].color = '#f97f80';
        	}
        	

                var inst = $('#demo-remote-api').mobiscroll().eventcalendar({
       	       
               	   data: eventData,
                   theme: 'ios',
                   themeVariant: 'light',
                   display: 'inline',
                   view: {
                       calendar: { labels: true, type: 'month' },
                       eventList: { type: 'month', scrollable: true }
                   }
           	}).mobiscroll('getInst');
        })
    });

</script>

<body>

	<br />

	<div id="demo-remote-api" style="width: 95%; color: #f97f80"></div>
	<br />
	<br />

</body>

</html>