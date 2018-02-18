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
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>
	<body>
		<div id="top"></div>
		<div id="navi"></div>
		<div id="content">
			<div class="container">
				<form method="post" action="/bitstudy/jsp/notice/modify">
					<input type="hidden" name="no" value="<c:out value="${notice.no}" />"/>
					<div class="form-group">
						<label>제목</label>
						<input type="text" class="form-control" name="title" value="<c:out value="${notice.title}" />" required autofocus>
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="8" name="content" required><c:out value="${notice.content}" /></textarea>
					</div>
					<div class="text-right">
						<c:if test="${user.admin}">
							<button type="submit" class="btn btn-gray">저장</button>
							<button type="button" class="btn btn-default" id="cancelBtn">취소</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
		<div id="footer"></div>	
		
		<script type="text/javascript">
		$(document).ready(function(){
			var rootPath = "${pageContext.request.contextPath}/jsp/home/";
	        $("#top").load(rootPath + "top.jsp");
	        $("#navi").load(rootPath + "nav.jsp");
	        $("#footer").load(rootPath + "footer.jsp");
	        
			$("#cancelBtn").click(function(){
				history.back();
			});
		});
		</script>
	</body>
</html>