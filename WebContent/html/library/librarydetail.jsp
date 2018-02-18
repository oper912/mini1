<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실 글번호 조회</title>
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
	<c:if test="${!empty recommend}">
		<%
			String errorMsg = (String)request.getAttribute("recommend");
			out.println("<script>alert('"+errorMsg+"');</script>");
		%>
	</c:if>
<body>
	<div id="top"></div>
	<div id="navi"></div> 
	<div id="content">
	
	<table width="50%" border=1>
		<tr>
			<th width = "70%" align = left colspan=2><h2>자료실 상세 조회</h2></th>
		</tr>
		<tr>
			<td>글번호 : <c:out value="${library.libNo}" /></td>
			<td>제목 : <c:out value="${library.title}" /></td>
		</tr>
		<tr>
			<td>작성자 : <c:out value="${library.id}" /></td>
			<td>작성일 : <fmt:formatDate value="${library.regDate}" pattern="yyyy-MM-dd HH:MM:ss" /></td>
		</tr>
		<tr>
			<td colspan=2 height="100px">내용 : <c:out value="${library.content}" /></td>
		</tr>
		<tr>
		<td>첨부파일</td>
		<td><a href="http://localhost:8000/bitstudy/html/library/libraryfiledown?libNo=<c:out value="${library.libNo}" />"><c:out value="${library.fileName}" /></a></td>
		</tr>
	</table>
	<div>
		<button type="button" onclick="location.href='http://localhost:8000/bitstudy/html/library/recommend?libNo=<c:out value="${library.libNo}" />&reid=<c:out value="${user.id}" />'">추천</button>
		<button type="button" onclick="location.href='http://localhost:8000/bitstudy/html/library/libraryupdate?libNo=<c:out value="${library.libNo}" />'">수정</button>
		<button type="button" onclick="location.href='http://localhost:8000/bitstudy/html/library/librarydelete?libNo=<c:out value="${library.libNo}" />'">삭제</button>
		<button type="button" onclick="location.href='http://localhost:8000/bitstudy/html/library/librarylist?paging=1'">목록</button>
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