<%@page import="com.bitstudy.qna.domain.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<style>
.namedate {
	display: inline-block;
	position: absolute;
	right: 0px;
	padding-right: 5px
}

.namedate1 {
	display: inline-block;
	position: absolute;
	right: 200px;
	padding-right: 5px
}
</style>
</head>
<body>
	<div id="top"></div>
	<div id="navi"></div>
	<div id="content">
		<div class="container">
			<input type="hidden" name="no" value="<c:out value="${qna.no}" />" />
			<a href="/bitstudy/html/home/main"><span style="font-size: 20px">Home</a>/
			</span> <a href="/bitstudy/jsp/qna/list"><span style="font-size: 30px">Q&A</a>
			</span> <span style="font-size: 30px">/<c:out value="${qna.no}" />
			</span>
			<h4 class="glyphicon glyphicon-eye-open pull-right">
				<c:out value="${qna.readCount}"></c:out>
			</h4>
			<hr>
			<span style="font-size: 30px"><c:out value="${qna.title}" /></span>
			<hr>
			<div class="container">
				<div class="col-sm-1"></div>
				<div class="col-sm-8">${qna.content}</div>
				<div class="col-sm-3">
					<span> <c:out value="${qna.name}" />
					</span> <span class="namedate"> <fmt:formatDate
							value="${qna.regDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
				</div>
			</div>
		</div>

		<form action="/bitstudy/jsp/qna/anser" method="post">
			<div class="container">
				<input type="hidden" name="groupNo" value="${qna.no}"> <input
					type="hidden" name="no" value="${qna.no}"> <input
					type="hidden" name="name" value="${user.name}">
				<%-- 아이디랑 이름이랑 테스트용임 --%>
				<input type="hidden" name="id" value="${user.id}"> <input
					type="hidden" name="title" value="${qna.title}">

				<textarea id="content" name="content" row="5" col="50"></textarea>

				<a class="btn btn-default btn-outline-dark pull-right" role="button"
					id="golist"> 취소 </a>
				<button type="submit" class="btn btn-default pull-right">작성</button>
			</div>
		</form>
	</div>

	<div id="footer"></div>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var rootPath = "/bitstudy/jsp/home/";
					$("#top").load(rootPath + "top.jsp");
					$("#navi").load(rootPath + "nav.jsp");
					$("#footer").load(rootPath + "footer.jsp");

				});
		
		$("#goList").click(function(){
			location = "/bitstudy/jsp/qna/list";
		});
	</script>
</body>
</html>