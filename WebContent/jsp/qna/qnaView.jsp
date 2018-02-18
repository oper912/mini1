<%@page import="com.bitstudy.qna.domain.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bit Study</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/bitstudy/css/default.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
			<div class="detail">
				<form method="post" name="qnaq" action="/bitstudy/jsp/qna/qdelete">
					<div class="text-right">
						<button type="button" class="btn btn-gray" id="anserBtn">답변</button>
						<c:if test="${qnal.id==user.id}">
						<button type="button" class="btn btn-gray" id="modifyBtn">수정</button>
						<button type="submit" class="btn btn-gray" id="deleteBtn">삭제</button>
						</c:if>
						<button type="button" class="btn btn-default" id="goList">목록</button>
					</div>
					<div class="detail-title">
						<span><img src="${pageContext.request.contextPath}/image/question.png" class="img-rounded" width="30" height="30" /></span> 
						<span class="title-line">|</span> 
						<span class="title"><c:out value="${qnal.title}" /></span>  
						<span class="glyphicon glyphicon-eye-open pull-right">
							<c:out value="${qnal.readCount}" />
						</span>
					</div>
				
					<input type="hidden" name="no" value="<c:out value="${qnal.no}" />" />
					<input type="hidden" name="id" value="<c:out value="${qnal.id}" />" />
					<div class="detail-content">
						<div class="write-info">
							<span>
								<c:choose>
  								<c:when test="${empty user.image}">
  									<img src="http://localhost:8000/bitstudy/profile/noImage.png" class="img-rounded" width="30" height="30" />
  								</c:when>
  								<c:otherwise>
  									<img src="http://localhost:8000/bitstudy/profile/${user.image}" class="img-rounded" width="30" height="30" />
  								</c:otherwise>
  								</c:choose>
								<c:out value="${qnal.name}" />
							</span>
							<span class="pull-right">
								<fmt:formatDate value="${qnal.regDate}"	pattern="yyyy-MM-dd HH:mm:ss" />
							</span>
						</div>
						<div>${qnal.content}</div>
					</div>
				</form>
			</div>
			<hr>
			
			<c:forEach var="qna" items="${list}">
				<div class="detail">
					<span><img src="${pageContext.request.contextPath}/image/answer.png" class="img-rounded" width="30" height="30" /></span> 
					<span>|</span> 
					<span class="title"><c:out value="${qna.title}" /></span>
					<hr>
					<div class="col-sm-1"></div>
					<div class="col-sm-8">${qna.content}</div>
					<div class="col-sm-3">
						<span><c:out value="${qna.name}" /></span> 
						<span class="namedate">
							<fmt:formatDate value="${qna.regDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</span>
					</div>
					<hr>${qnal.id} ${user.id}
					${user.name}
					<c:if test="${qnal.id == user.id}">
					<div class="text-right">
						<a href="/bitstudy/jsp/qna/modifyaview?no=<c:out value="${qna.no}" />" class="btn btn-default" role="button">수정</a> 
						<a href="/bitstudy/jsp/qna/adelete?no=<c:out value="${qna.no}" />" class="btn btn-default" role="button">삭제</a>
					</div>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>

	<div id="footer"></div>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var rootPath = "/bitstudy/jsp/home/";
					$("#top").load(rootPath + "top.jsp");
					$("#navi").load(rootPath + "nav.jsp");
					$("#qnaview").load("/bitstudy/jsp/qna/qnaView.jsp");
					$("#footer").load(rootPath + "footer.jsp");

					$("#anserBtn").click(
							function() {
								location = "/bitstudy/jsp/qna/anserview?no="
										+ $("input[name='no']").val();
							});
					$("#modifyBtn").click(
							function() {
								location = "/bitstudy/jsp/qna/modifyview?no="
										+ $("input[name='no']").val();
							});
					$("#deleteBtn").click(
							function() {
								location = "/bitstudy/jsp/qna/qdelete?no="
										+ $("input[name='no']").val();
							});
					$("#goList").click(function() {
						location = "/bitstudy/jsp/qna/list";
					});
					
				});
	</script>
</body>
</html>