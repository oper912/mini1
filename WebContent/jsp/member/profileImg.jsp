<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bit Study</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<style>
			.img-border {
				border:1px solid #ccc;
				margin-bottom:3px;
			}
		</style>
	</head>
	<body>
		<div id="top"></div>
		<div id="navi"></div>
		<div id="content">
			<div class="container pull-center">
				<div class="member-border">
					<form method="post" class="form-member" action="${pageContext.request.contextPath}/jsp/member/image" enctype="multipart/form-data">
						<h2 class="form-member-heading text-center">프로필 사진 관리</h2>
						<P>
							<c:if test="${not empty error}">
								<hr>
								<strong class="errorMsg"><c:out value="${error}" /></strong>
								<hr>
							</c:if>
						</P>
						<c:choose>
							<c:when test="${empty user.image}">
								<img src="http://localhost:8000/bitstudy/profile/noImage.png" class="img-rounded img-border" width="300" height="300" />
							</c:when>
							<c:otherwise>
								<img src="http://localhost:8000/bitstudy/profile/${user.image}" class="img-rounded img-border" width="300" height="300" />
							</c:otherwise>
						</c:choose>
						<input type="file" class="form-control" name="attachImg"/><br>
						<button type="submit" class="btn btn-lg btn-primary btn-block">확인</button>
						<button type="button" id ="deleteBtn" class="btn btn-lg btn-primary btn-block">삭제</button>
					</form>
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
	        
	        $("#deleteBtn").click(function(){
				location = "${pageContext.request.contextPath}/jsp/member/deleteimage";
			});
		});
		</script>
	</body>
</html>