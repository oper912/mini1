<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="/bitstudy/css/default.css">
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="top"></div>
	<div id="navi"></div> 
	<div id="content">
	<h2>자료실 수정</h2>
	<hr>
	<form method="post" action="/bitstudy/html/library/libraryupdateapply?libNo=${library.libNo}">
		<div>
			제목 : <input type="text" name="title" value="${library.title}">  
			아이디 : ${library.id}
		</div>
		<div>
			내용 : <textarea rows="8" cols="55" name="content" value="${library.content}"></textarea>
		</div>
		<input type="submit" value="수정">
		<button type="button" onclick="location.href='http://localhost:8000/bitstudy/html/library/librarylist?paging=1'">목록</button>
	</form>
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
</body>
</html>