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
	</head>
	<body>
		<div id="top"></div>
		<div id="navi"></div>
		<div id="content">
			<div class="container pull-center">
				<div class="member-border">
					<form method="post" class="form-member" action="${pageContext.request.contextPath}/jsp/member/pwfind">
						<h2 class="form-member-heading">비밀번호찾기</h2>
						<P>
							<c:if test="${not empty error}">
								<hr>
								<strong class="errorMsg"><c:out value="${error}" /></strong>
								<hr>
							</c:if>
						</P>
						<input type="hidden" name="opt" value="findpw">
						<label>이름</label><br>
						<input type="text" class="form-control" name="name" required autofocus>
						<label>이메일(E-mail)</label><br>
						<input type="text" class="form-control" name="email" required>
						<label>휴대전화 번호</label><br>
						<input type="text" class="form-control" name="phoneNo" required><br>
						<button type="submit" class="btn btn-lg btn-primary btn-block">확인</button>
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
		});
		</script>
	</body>
</html>