<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실 입력</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script><!-- 4줄이 부트스트랩 사용할 준비가 끝 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"><!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"><!-- 부가적인 테마 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css"><!-- 기본영역 잡아주는?? -->
 	<script src="//cdn.ckeditor.com/4.7.2/basic/ckeditor.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script><!-- 합쳐지고 최소화된 최신 자바스크립트 -->
</head>
<body>
	<div id="top"></div>
	<div id="navi"></div>
	<div id="content">
		<div class="container">
			<h3>자료실 등록</h3>
			<hr>
			<form method="post" action="/bitstudy/jsp/library/libraryinsert" enctype="multipart/form-data">
				제목 <input type="text" name="title" >
					<button class="btn btn-gray">등록</button>
					<button type="button" onclick="location.href='http://localhost:8000/bitstudy/jsp/library/librarylist?paging=1'" class="btn btn-default">목록</button>
				<textarea rows="8" cols="50" name="content" id="editor1"></textarea>
				<input type="file" name="attachFile1" >
				<input type="file" name="attachFile2" >
				<input type="file" name="attachFile3" >
			</form>
		</div>
	</div>
	<%-- <div id="footer"></div>--%>
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