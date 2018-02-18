<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실</title>
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
	<table width="100%">
		<tr bgcolor="#FFFFE9">
			<td width = "70%" align = left ><h2>자료실</h2></td>
			<td align = center>
			<form action="/bitstudy/">	 	 
				<select name="opt" id="opt">
					<option value="title">제목</option>
					<option value="content">제목+내용</option>
					<option value="name">작성자</option>
				</select>
		 	</td>
		 	<td>
 				<input type="text" name="keyword" id="keyword">
 				<button type="button">검색</button>
 			</form>
		 	</td>
		</tr>
	</table>
<table width="50%" align="center" class="table table-hover">
	<tr align="left">
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
		<th>추천수</th>
	</tr>
	<c:forEach var="library" items="${list}">
	 	<tr>
	 		<td><c:out value="${library.libNo}" /> </td>
	 		<td><a href="http://localhost:8000/bitstudy/html/library/librarydetail?libNo=<c:out value="${library.libNo}" />"><c:out value="${library.title}" /></a></td>
	 		<td><c:out value="${library.name}" /> </td>
	 		<td><c:out value="${library.regDate}" /></td>
	 		<td><c:out value="${library.readCount}" /></td>
	 		<td><c:out value="${library.recommentCount}" /></td>
	 	</tr>
	</c:forEach>
</table>
<div>
	<table align="center">
		<tr align="center">
			<td width="50px"><a href="http://localhost:8000/bitstudy/html/library/librarylist?paging=1">처음 ▶</a></td>
				<c:forEach var="i" begin="1" end="${cnt}" >
					<td width="30px"><a href="http://localhost:8000/bitstudy/html/library/librarylist?paging=${i}">[ ${i} ]</a></td>
				</c:forEach>
			<td width="60px"><a href="http://localhost:8000/bitstudy/html/library/librarylist?paging=<c:out value="${cnt}" />">◀ 마지막</a></td>
		</tr>
	</table>
</div>
<div align="right"><button type="button" onclick="location.href='http://localhost:8000/bitstudy/html/library/libraryinsertcall'">등록</button></div>
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