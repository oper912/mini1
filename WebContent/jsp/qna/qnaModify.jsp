<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bit Study</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/bitstudy/css/default.css">

</head>
<body>
	<div id="top"></div>
	<div id="navi"></div>
	<div id="content">
		<div class="container">
			<form method="post" action="/bitstudy/jsp/qna/modify">
				<input type="hidden" name="no" value="<c:out value="${qna.no}" />" />
				<input type="hidden" name="id" value="<c:out value="${user.id}" />" />
				<input type="hidden" name="groupNo" value="1">
				<input class="form-control" id="title" name="title" type="text"
					value="<c:out value="${qna.title}" />"><br>
				<textarea id="content" name="content"><c:out value="${qna.content}" /></textarea>
				<script>
					$(document).ready(function() {
						$('#content').summernote({
							height : 300,
							minHeight : 100,
							maxHeight : 500,
							focus : false
						});
					});
				</script>
				<div class="text-right">
					<button type="submit" class="btn btn-default">저장</button>
					<button type="button" class="btn btn-default" id="cancelBtn">취소</button>
				</div>
			</form>
		</div>
	</div>
	<div id="footer"></div>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var rootPath = "/bitstudy/jsp/home/";
			$("#top").load(rootPath + "top.jsp");
			$("#navi").load(rootPath + "nav.jsp");
			$("#footer").load(rootPath + "footer.jsp");

			$("#cancelBtn").click(function() {
				history.back();
			});
		});
	</script>
</body>
</html>