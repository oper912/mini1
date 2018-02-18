<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
tibold {
	font-size: 1em;
	font-weight: bold;
	text-align: center
}
</style>
</head>
	<c:if test="${!empty msg}">
		<%
			String msg = (String)request.getAttribute("msg");
			out.print("<script>alert('"+msg+"');</script>");
		%>
	</c:if>
	<c:if test="${!empty error}">
		<%
			String msg = (String)request.getAttribute("error");
			out.print("<script>alert('"+msg+"');</script>");
		%>
	</c:if>
<body>
	<div id="top"></div>
	<div id="navi"></div>
	<div id="content">
		<div class="container">
			<h2>자 료 실<button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="규칙" data-content="1. 불법 및 불건전한 자료 업로드 금지">자료실 이용 규칙</button></h2></h2>
			<div class="text-right">
				<form action="${pageContext.request.contextPath}/jsp/library/librarysearch">
					<select name="opt" id="opt">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="name">작성자</option>
					</select>
					<input type="text" name="keyword" id="keyword">
					<button class="btn btn-sm"><span class="glyphicon glyphicon-search"></span></button>
				</form>
		<table class="table table-striped table-hover">
		
			<tibold>
			<tr>
				<th class="text-center">글번호</th>
				<th class="text-center">제목</th>
				<th class="text-center">작성자</th>
				<th class="text-center">작성일</th>
				<th class="text-center">조회수</th>
				<th class="text-center">추천수</th>
			</tr>
			</tibold>
			
			<c:forEach var="library" items="${list}">
 			<tr>
 				<td class="text-center"><c:out value="${library.libNo}" /></td>
 				<td align="center" width="40%"><a href="/bitstudy/jsp/library/detail?no=<c:out value="${library.libNo}" />"><c:out value="${library.title}" /></a></td>
 				<td class="text-center">
 					<c:out value="${library.name}" />
<%-- 					<c:choose>
					<c:when test="${library.name == '홍길동'}">
						<img src="http://localhost:8000/bitstudy/image/adminlogo.jpg">
					</c:when>
					<c:otherwise>
						<c:out value="${library.name}" />
					</c:otherwise>
					</c:choose> --%>
 				</td>
 				<td class="text-center"><c:out value="${library.regDate}" /></td>
 				<td class="text-center"><c:out value="${library.readCount}" /></td>
 				<td class="text-center"><c:out value="${library.recommentCount}" /></td>
 			</tr>
			</c:forEach>
		</table>
		<div>
		<table align="center">
			<tr align="center">
				<td width="50px"><a href="/bitstudy/jsp/library/librarylist?paging=1">처음 ▶</a></td>
					<c:if test="${!empty cnt}">
						<c:forEach var="i" begin="1" end="${cnt}" >
							<td width="30px"><a href="/bitstudy/jsp/library/librarylist?paging=${i}">[ ${i} ]</a></td>
						</c:forEach>
					</c:if>
				<td width="60px"><a href="/bitstudy/jsp/library/librarylist?paging=<c:out value="${cnt}" />">◀ 마지막</a></td>
			</tr>
		</table>
		</div>
		<div align="right"><button type="button" class="btn btn-default" onclick="location.href='/bitstudy/jsp/library/libraryinsertcall'">등록</button></div>
		</div>
	</div>
	<br><br>
<%-- 	<c:import url="/jsp/library/carousel.jsp">
	</c:import> --%>
	<div id="footer"></div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var rootPath = "/bitstudy/jsp/home/";
	        $("#top").load(rootPath + "top.jsp");
	        $("#navi").load(rootPath + "nav.jsp");
	        $("#footer").load(rootPath + "footer.jsp");
		});
		$(function () {
		    $('[data-toggle="popover"]').popover()
		    })
	</script>
</body>
</html>