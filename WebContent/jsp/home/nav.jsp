<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bit Study</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<style>
			.navbar {
				z-index:99;
			}
			.affix {
			    top: 0;
			    width: 100%;
			}
	    	.affix + .container {
			    padding-top: 70px;
			}
		</style>
	</head>
	<body>
		<div class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
      		<div class="container">
      			<div class="navbar-header">
			    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			        	<span class="icon-bar"></span>
			        	<span class="icon-bar"></span>
			        	<span class="icon-bar"></span>                        
			      	</button>
		    	</div>
		    	<div class="collapse navbar-collapse" id="myNavbar">
	        		<ul class="nav navbar-nav">
			            <li><a href="" onclick="moveMenu(this,'home');return false;">Home</a></li>
			            <li><a href="" onclick="moveMenu(this,'notice');return false;">공지사항</a></li>
			            <li><a href="" onclick="moveMenu(this,'calendar');return false;">일정</a></li>
			            <li><a href="" onclick="moveMenu(this,'qna');return false;">Q&amp;A</a></li>
			            <li><a href="" onclick="moveMenu(this,'freeboard');return false;">자유게시판</a></li>
			            <li><a href="" onclick="moveMenu(this,'library');return false;">자료실</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<c:choose>
							<c:when test="${empty user}">
							 	<li><a href="" onclick="moveMenu(this,'login');return false;"><span class="glyphicon glyphicon-log-in">&nbsp;로그인</span></a></li>
		      				</c:when>
	    	  				<c:otherwise>
	    	  					<li class="dropdown" role="presentation">
	    	  						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	    	  							<c:choose>
	    	  								<c:when test="${empty user.image}">
	    	  									<img src="http://localhost:8000/bitstudy/profile/noImage.png" class="img-rounded" width="30" height="30" />
	    	  								</c:when>
	    	  								<c:otherwise>
	    	  									<img src="http://localhost:8000/bitstudy/profile/${user.image}" class="img-rounded" width="30" height="30" />
	    	  								</c:otherwise>
	    	  							</c:choose>
	    	  							<c:out value="${user.name}" />&nbsp;님<span class="caret"></span>
	    	  						</a>
	    	  						<ul class="dropdown-menu" role="menu">
	    	  							<li><a href="" onclick="moveMenu(this,'myinfo');return false;">개인정보관리</a></li>
	    	  							<li><a href="" onclick="moveMenu(this,'myimg');return false;">프로필사진관리</a></li>
	          							<li><a href="" onclick="moveMenu(this,'logout');return false;">로그아웃</a></li>
	    	  						</ul>
	    	  					</li>
	    					</c:otherwise>
	    				</c:choose>
					</ul>
				</div>
			</div>
	    </div>
       	<script type="text/javascript">
       		function moveMenu(obj, option){
       			if(option!= "home" && ${empty user}){
          			location = "${pageContext.request.contextPath}/jsp/member/loginform";
          			return false;
          		}
				
				var rootPath = "${pageContext.request.contextPath}/jsp";
				switch(option.toLowerCase()){
				case "home": 
					location = rootPath + "/home/main";
					break;
				case "notice":
					location = rootPath + "/notice/list";
					break;
				case "calendar": 
					location = rootPath + "/calendar/calendar";
					break;
				case "qna": 
					location = rootPath + "/qna/list";
					break;
				case "freeboard":
					location = rootPath + "/free/list";
					break;
				case "library":
					location = rootPath + "/library/librarylist?paging=1";
					break;
				case "login":
					location = rootPath + "/member/loginform";
					break;
				case "myinfo":
					location = rootPath + "/member/modifyform";
					break;
				case "myimg":
					location = rootPath + "/member/imageform";
					break;
				case "logout":
					location = rootPath + "/member/logout";
					break;
				}
			} 
		</script>
	</body>
</html>