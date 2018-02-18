<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bit Study</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
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
	<c:if test="${!empty recommend}">
		<%
			String errorMsg = (String)request.getAttribute("recommend");
			out.print("<script>alert('"+errorMsg+"');</script>");
		%>
	</c:if>
<body>
	<c:if test="${not popup}">
	<div id="top"></div>
	<div id="navi"></div> 
	</c:if>	
	<div id="content">
		<div class="container">
			<div class="detail">
				<div class="text-right">
				
				</div>
		<table class="type07">
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<tr>
				<td><c:out value="${library.libNo}" /></td>
				<td><c:out value="${library.name}" /></td>
				<td><c:out value="${library.title}" /></td>
				<td><fmt:formatDate value="${library.regDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<td colspan=4 height="100px" class="content"><c:out value="${library.content}" /></td>
			</tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<th colspan=2>첨부파일</th>
					<td colspan=2><a href="${pageContext.request.contextPath}/jsp/library/librarydownload?path=<c:out value="${list.filePath}" />&sname=<c:out value="${list.fileSystemName}" />&dname=<c:out value="${list.fileOrgName}" />">
						<c:out value="${list.fileOrgName}" />&nbsp;/
						&nbsp;<c:out value="${list.fileSize}" />[byte]
					</a>
					</td>
				</tr>
			</c:forEach>
			</table>
			<div>
				<c:if test="${not popup}">
					<c:if test="${!empty user}">
						<button type="button" onclick="location.href='http://localhost:8000/bitstudy/jsp/library/recommend?libNo=<c:out value="${library.libNo}" />&reid=<c:out value="${user.id}" />'" class="btn btn-primary">추천</button>
					</c:if>
					<c:if test="${user.id == library.id || user.admin == 1}">
						<button type="button" onclick="location.href='http://localhost:8000/bitstudy/jsp/library/libraryupdate?libNo=<c:out value="${library.libNo}" />'" class="btn btn-warning">수정</button>
					</c:if>
					<c:if test="${user.id == library.id || user.admin == 1}">
						<button type="button" onclick="location.href='http://localhost:8000/bitstudy/jsp/library/librarydelete?libNo=<c:out value="${library.libNo}" />'" class="btn btn-danger">삭제</button>
					</c:if>
					<button type="button" onclick="location.href='http://localhost:8000/bitstudy/jsp/library/librarylist?paging=1'" class="btn btn-default">목록</button>
				</c:if>
			</div>
		</div>
	</div>
	<c:if test="${not popup}">
		<div id="footer"></div>
	</c:if>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var rootPath = "/bitstudy/jsp/home/";
			if(${not popup}){
	       		$("#top").load(rootPath + "top.jsp");
	        	$("#navi").load(rootPath + "nav.jsp");
	        	$("#footer").load(rootPath + "footer.jsp");
			}
		});
	</script>
</body>
</html>