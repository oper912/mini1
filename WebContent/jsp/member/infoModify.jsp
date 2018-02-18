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
					<form method="post" class="form-member" action="${pageContext.request.contextPath}/jsp/member/pwcheckform">
						<h2 class="form-member-heading">개인정보관리</h2>
						<P>
							<c:if test="${not empty error}">
								<hr>
								<strong class="errorMsg"><c:out value="${error}" /></strong>
								<hr>
							</c:if>
						</P>
						<label>이름</label><br>
						<input type="text" class="form-control" name="name" value="<c:out value="${member.name}" />" required autofocus>
						<label>휴대전화 번호</label><br>
						<input type="text" class="form-control" name="phoneNo" value="<c:out value="${member.phoneNo}" />" required>
						<label>새 비밀번호</label><br>
						<input type="password" class="form-control" name="newPassword">
						<label>새 비밀번호 확인</label><br>
						<input type="password" class="form-control" name="passwordCheck">
						<label>비밀번호 힌트 질문</label><br>
						<input type="text" class="form-control" name="pwHintQuestion" value="<c:out value="${member.pwHintQuestion}" />" required>
						<label>비밀번호 힌트 답변</label><br>
						<input type="text" class="form-control" name="pwHintAnswer" value="<c:out value="${member.pwHintAnswer}" />" required><br>
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