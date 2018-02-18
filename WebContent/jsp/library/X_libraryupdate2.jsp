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
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
table.type07 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border: 1px solid #ccc;
    margin: 20px 10px;
}
table.type07 th{
    border-right: 1px solid #ccc;
    border-left: 1px solid #ccc;
    padding: 10px;
    background: #e7708d;
}
table.type07 td.content{
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: white;
}
table.type07 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #fcf1f4;
}
</style>
</head>
<body>
	<div id="top"></div>
	<div id="navi"></div> 
	<div id="content">
		<div class="container">
			<h2>자료실 글 수정</h2>
			<form method="post" action="/bitstudy/jsp/library/libraryupdateapply?libNo=${library.libNo}">
				<table border=1 class="type07">
					<tr>
						<th>글번호</th>  <td><c:out value="${library.libNo}" /></td>
						<th>제목</th>  <td><input type="text" name="title" value=<c:out value="${library.title}"/>></td>
					</tr>
					<tr>
						<th>작성자</th> <td><c:out value="${library.name}" /></td>
						<th>작성일 </th> <td><fmt:formatDate value="${library.regDate}" pattern="yyyy-MM-dd HH:MM:ss" /></td>
					</tr>
					<tr>
						<td colspan=4 height="100px" class="content">
						<textarea rows="8" cols="55" name="content"><c:out value="${library.content}" /></textarea>
						</td>
					</tr>
					<c:forEach var="list" items="${list}">
						<tr>
							<th colspan=2>첨부파일</th>
							<td colspan=2>
								<c:out value="${list.fileOrgName}" />&nbsp;/&nbsp;<c:out value="${list.fileSize}" />[byte]
							</td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="수정" class="btn btn-gray">
				<button type="button" onclick="location.href='http://localhost:8000/bitstudy/jsp/library/librarylist'" class="btn btn-default">목록</button>
			</form>
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
</body>
</html>