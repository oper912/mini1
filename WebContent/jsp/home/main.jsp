<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<title>Bit Study</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<style>
			.panel-gray {
				border-color:#999 !important;
			}
			.panel-heading {
				color:#fff !important;
				background-color:#999 !important;
			}
			.panel-body {
				height:160px !important;
			}
			.mypanel-row {
				width:100%;
				padding:3px;
			}
			.mypanel-title {
				display:inline-block;
				width:60%;
				overflow:hidden;
				text-overflow:ellipsis;
				white-space:nowrap;
				float:left;
			}
			.mypanel-title > a {
				text-decoration: none !important;
				color:#333;
			}
			.mypanel-writer {
				display:inline-block;
				width:15%;
			}
			.mypanel-date {
				width:20%;
				float:right;
				text-align:center;
			}
			span.glyphicon-plus {
				cursor:pointer
			}
		</style>
	</head>
	<body>
		<div id="top"></div>
		<div id="navi"></div>
		<div id="content">
			<div class="container">
			  	<div class="row">
			  		<div class="col-lg-6">
				    	<div class="panel panel-gray">
	    					<div class="panel-heading">공지사항
			      				<span class="glyphicon glyphicon-plus pull-right" onclick="moveBoardList('notice')"></span>
			      			</div>
	      					<div class="panel-body">
	      						<c:choose>
	      							<c:when test="${empty noticeList}">
		      							<div class="mypanel-row">
								      		<div>등록된 공지사항이 없습니다.</div>
						      			</div>
	      							</c:when>
	      							<c:otherwise>
			      						<c:forEach var="notice" items="${noticeList}">
				      						<div class="mypanel-row">
									      		<div class="mypanel-title"><a href="" onclick="openDocument('notice',${notice.no});return false;"><c:out value="${notice.title}" /></a></div>
									      		<div class="mypanel-writer"><c:out value="${notice.name}" /></div>
							      				<div class="mypanel-date"><fmt:formatDate value="${notice.regDate}" pattern="yyyy-MM-dd" /></div>
							      			</div>
						      			</c:forEach>
						      		</c:otherwise>
	      						</c:choose>	
				      		</div>
	    				</div>
			   		</div>
			  		
			  		<div class="col-lg-6">
				    	<div class="panel panel-gray">
	    					<div class="panel-heading">일정
			      				<span class="glyphicon glyphicon-plus pull-right" onclick="moveBoardList('calendar')"></span>
			      			</div>
	      					<div class="panel-body">
		      					<c:choose>
	      							<c:when test="${empty calendarList}">
		      							<div class="mypanel-row">
								      		<div>등록된 일정이 없습니다.</div>
						      			</div>
	      							</c:when>
	      							<c:otherwise>
			      						<c:forEach var="calendar" items="${calendarList}">
				      						<div class="mypanel-row">
									      		<div class="mypanel-title"><a href="" onclick="openDocument('calendar',${calendar.calNo});return false;"><c:out value="${calendar.title}" /></a></div>
									      		<div class="mypanel-writer"><c:out value="${calendar.name}" /></div>
							      				<div class="mypanel-date"><c:out value="${calendar.startDate}"/></div>
							      			</div>
						      			</c:forEach>
						      		</c:otherwise>
	      						</c:choose>
				      		</div>
	    				</div>
			   		</div>
			 	</div>
			 	
			  	<div class="row">
			    	<div class="col-lg-6">
				    	<div class="panel panel-gray">
	    					<div class="panel-heading">Q&amp;A
			      				<span class="glyphicon glyphicon-plus pull-right" onclick="moveBoardList('qna')"></span>
			      			</div>
	      					<div class="panel-body">
	      						<c:choose>
	      							<c:when test="${empty qnaList}">
		      							<div class="mypanel-row">
								      		<div>등록된 Q&amp;A가 없습니다.</div>
						      			</div>
	      							</c:when>
	      							<c:otherwise>
			      						<c:forEach var="qna" items="${qnaList}">
				      						<div class="mypanel-row">
									      		<div class="mypanel-title"><a href="" onclick="openDocument('qna',${qna.no});return false;"><c:out value="${qna.title}" /></a></div>
									      		<div class="mypanel-writer"><c:out value="${qna.name}" /></div>
							      				<div class="mypanel-date"><fmt:formatDate value="${qna.regDate}" pattern="yyyy-MM-dd" /></div>
							      			</div>
						      			</c:forEach>
						      		</c:otherwise>
	      						</c:choose>	
				      		</div>
	    				</div>
			   		</div>
			   		
			    	<div class="col-lg-6">
				    	<div class="panel panel-gray">
	    					<div class="panel-heading">자료실
			      				<span class="glyphicon glyphicon-plus pull-right" onclick="moveBoardList('library')"></span>
			      			</div>
	      					<div class="panel-body">
	      						<c:choose>
	      							<c:when test="${empty libraryList}">
		      							<div class="mypanel-row">
								      		<div>등록된 자료가 없습니다.</div>
						      			</div>
	      							</c:when>
	      							<c:otherwise>
			      						<c:forEach var="library" items="${libraryList}">
				      						<div class="mypanel-row">
									      		<div class="mypanel-title"><a href="" onclick="openDocument('library',${library.libNo});return false;"><c:out value="${library.title}" /></a></div>
									      		<div class="mypanel-writer"><c:out value="${library.name}" /></div>
							      				<div class="mypanel-date"><fmt:formatDate value="${library.regDate}" pattern="yyyy-MM-dd" /></div>
							      			</div>
						      			</c:forEach>
						      		</c:otherwise>
	      						</c:choose>	
				      		</div>
	    				</div>
			   		</div>
			 	</div>
			</div>
		</div>	
		<div id="footer"></div>
	
		<script type="text/javascript">
		$(document).ready(function(){
			var rootPath = "${pageContext.request.contextPath}/jsp/home/";
	        $("#top").load(rootPath + "top.jsp");
	        $("#navi").load(rootPath + "nav.jsp");
	        $("#footer").load(rootPath + "footer.jsp");
	    });
		
		function moveBoardList(boardId){
			if(${empty user}){
      			location = "${pageContext.request.contextPath}/jsp/member/loginform";
      			return false;
      		}
			
			switch(boardId){
			case "notice":location = "${pageContext.request.contextPath}/jsp/notice/list";break;
			case "calendar":location = "${pageContext.request.contextPath}/jsp/caleandar/list"; break;
			case "qna":location = "${pageContext.request.contextPath}/jsp/qna/list";break;
			case "library":location = "${pageContext.request.contextPath}/jsp/library/list";break;
			}			
		}
		
      	function openDocument(boardId, no){
      		if(${empty user}){
      			location = "${pageContext.request.contextPath}/jsp/member/loginform";
      			return false;
      		}
      		
      		var popupWidth = 500, popupHeight=500;
      		switch(boardId){
	      		case "notice": popupWidth = 500; popupHeight = 500; break;      		
	      		case "calendar": popupWidth = 500; popupHeight = 500; break;      		
	      		case "qna": popupWidth = 500; popupHeight = 500; break;      		
	      		case "library": popupWidth = 500; popupHeight = 500; break;      		
      		}
      		
      		var winWidth = window.screen.width;
      		var winHeight = window.screen.height;
      		var popX = (winWidth/2)-(popupWidth/2);
      		var popY = (winHeight/2)-(popupHeight/2);
      		
      		window.open("${pageContext.request.contextPath}/jsp/"+boardId+"/detail?popup=1&no="+no,"",
      				"width="+popupWidth+", height="+popupHeight+", left="+popX+", top="+popY);
      	}
		</script>
	</body>
</html>