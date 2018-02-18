<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실 수정</title>
<!-- 4줄이 부트스트랩 사용할 준비가 끝 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 기본영역 잡아주는?? -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<script src="//cdn.ckeditor.com/4.7.2/basic/ckeditor.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
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
			<h3>자료실 수정</h3>
			<hr>
			<form method="post" action="/bitstudy/jsp/library/libraryupdateapply?libNo=${library.libNo}">
			제목 <input type="text" name="title" value=<c:out value="${library.title}" />>
				<button class="btn btn-gray">수정완료</button>
				<button type="button" onclick="location.href='/bitstudy/jsp/library/librarylist?paging=1'" class="btn btn-default">목록</button>
				<textarea rows="8" cols="50" name="content" id="editor1">${library.content}</textarea>
			<hr>
			<c:forEach var="list" items="${list}">
				<c:out value="${list.fileOrgName}" />&nbsp;/
				&nbsp;<c:out value="${list.fileSize}" />[byte]
			</c:forEach>
		</div>
	</div>
	<div id="footer"></div>
<script type="text/javascript">
	$(document).ready(function(){
		var rootPath = "/bitstudy/jsp/home/";
        $("#top").load(rootPath + "top.jsp");
        $("#navi").load(rootPath + "nav.jsp");
        $("#footer").load(rootPath + "footer.jsp");
	});
</script>
<script>
   	CKEDITOR.replace( 'editor1' );
</script>
</body>
</html>