<%@page import="com.bitstudy.notice.domain.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bit Study</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>
	<body>
	<c:if test="${not popup}">
		<div id="top"></div>
		<div id="navi"></div>
	</c:if>	
		<div id="content">
			<div class="container">
				<form method="post" action="${pageContext.request.contextPath}/jsp/notice/delete">
					<input type="hidden" name="no" value="<c:out value="${notice.no}" />"/>
					<table class="table table-bordered">
						<tr>
							<th class="text-center bg-default">제목</th>
							<td colspan="3"><c:out value="${notice.title}" /></td>
						</tr>
						<tr>
							<th class="text-center bg-default">작성자</th>
							<td><c:out value="${notice.name}" /></td>
							<th class="text-center bg-default">작성일</th>
							<td><fmt:formatDate value="${notice.regDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td colspan="4" style="padding:20px"><c:out value="${notice.content}" /></td>
						</tr>
					</table>
			
					<div class="text-right">
						<c:if test="${not popup}">
							<c:if test="${user.admin}">
								<button type="button" class="btn btn-gray" id="modifyBtn">수정</button>
								<button type="submit" class="btn btn-gray" id="deleteBtn">삭제</button>
							</c:if>
							<button type="button" class="btn btn-default" id="goList">목록</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
		<c:if test="${not popup}">
			<div id="footer"></div>
		</c:if>
		<script type="text/javascript">
		$(document).ready(function(){
			var rootPath = "${pageContext.request.contextPath}/jsp/home/";
			if(${not popup}){
		        $("#top").load(rootPath + "top.jsp");
		        $("#navi").load(rootPath + "nav.jsp");
		        $("#footer").load(rootPath + "footer.jsp");
			}
			$("#modifyBtn").click(function(){
				location = "${pageContext.request.contextPath}/jsp/notice/write?mode=mod&no="+$("input[name='no']").val();
			});
			$("#deleteBtn").click(function(){
				location = "${pageContext.request.contextPath}/jsp/notice/delete?no="+$("input[name='no']").val();
			});
			$("#goList").click(function(){
				location = "${pageContext.request.contextPath}/jsp/notice/list";
			});
		});
		</script>
	</body>
</html>