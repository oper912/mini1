<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>풀캘린더</title>


<style type="text/css">
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}

.fc-day-number.fc-sat.fc-past {
	color: #0000FF;
} /* 토요일 */-
.fc-day-number.fc-sun.fc-past {
	color: #FF0000;
} /* 일요일 */
}
</style>






<!-- // jquery link... -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="\bitstudy\jsp\calendar\fullcalendar-2.9.1\lib\jquery.min.js"></script>





<link href="\bitstudy\jsp\calendar\fullcalendar-2.9.1\fullcalendar.print.css" rel="stylesheet" media="print" />
<link href="\bitstudy\jsp\calendar\fullcalendar-2.9.1\fullcalendar.css" rel="stylesheet" />


<script type="text/javascript" src="\bitstudy\jsp\calendar\fullcalendar-2.9.1\lib\moment.min.js"></script>
<script type="text/javascript" src="\bitstudy\jsp\calendar\fullcalendar-2.9.1\fullcalendar.js" charset="euc-kr"></script>
<script type="text/javascript" src="\bitstudy\jsp\calendar\fullcalendar-2.9.1\lang-all.js"></script>

<script language = "javascript"></script>



<script type="text/javascript">
   	 	jQuery(document).ready(function() {

   			var list = ${list};
   	   	 	console.log("list", list);
			
   	 	
   	 	
        jQuery("#calendar").fullCalendar({
   	   	 
        	
        	 customButtons: {
        	        myCustomButton: {
        	            text: '일정등록',
        	            click: function() {
        	            	window.open('${pageContext.request.contextPath}/jsp/calendar/popupcreate.jsp', 'new', 'width=400, height=810');
        	            }
        	        }
        	}
             , defaultDate : new Date() , lang : "ko" , button : "create"
	         , editable : true , eventLimit : true
			 , events: list
			 ,eventClick:function(event) {
	        		 if(event) {
	                	window.open('${pageContext.request.contextPath}/jsp/calendar/readplan.jsp', 'new', 'width=400 height=810 location=no resizable =no');
	                	//alert(event.title + "\n" + event.url, "wicked", "width=700,height=600");
	                    //window.open(event.url); 확인을 누르면 해당 링크로 이동하는 메소드
	                    return false;
                }
            }
        
          
                
                
        });
    });
</script>


<body>
	<div id="calendar"></div>
</body>
</html>