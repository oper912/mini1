<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.bitstudy.qna.domain.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bit Study</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">

<style type="text/css">
tibold {
	font-size: 1em;
	font-weight: bold;
	text-align: center
}
</style>

</head>
<body>
	<div id="top"></div>
	<div id="navi"></div>
	<div id="content">
		<div class="container">
			<h2>Q &amp; A</h2>
			<div class="text-right">
				<select name="opt" id="opt">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="all">제목+내용</option>
					</select>
					<input type="text" name="keyword" id="keyword">
					<button type="button" class="btn btn-sm" id="searchBtn"><span class="glyphicon glyphicon-search"></span></button>
				</div>
			
			<Table class="table table-striped table-hover">

				<tibold>
				<tr>
					<th class="text-center">번호</th>
					<th class="text-center" width="70%">제목</th>
					<th class="text-center">작성자</th>
					<th class="text-center">날짜</th>
					<th class="text-center">조회수</th>
				</tr>
				</tibold>

				<c:forEach var="qna" items="${list}">
					<tr>
						<td class="text-center"><c:out value="${qna.no}" /></td>
						<td align="center" width="70%"><a href="${pageContext.request.contextPath}/jsp/qna/detail?no=<c:out value="${qna.no}" />"> <c:out value="${qna.title}" /></a></td>
						<td class="text-center"><c:out value="${qna.name}" /></td>
						<td class="text-center"><c:out value="${qna.regDate}" /></td>
						<td class="text-center"><c:out value="${qna.readCount}" /></td>
					</tr>
				</c:forEach>

			</table>
			<c:if test="${not empty list}">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${paging.groupCurPage ne 1}">
							<li><a href="" onclick="searchList(1,'startGroup');return false;">&laquo;</a></li>
							<li><a href="" onclick="searchList(<c:out value="${paging.startPage-1}" />,'prevGroup');return false;">&lt;</a></li>
							</c:if>
							<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
								<li id="page_<c:out value="${i}" />"><a href="" onclick="searchList(<c:out value="${i}" />,'next');return false;"><c:out value="${i}" /></a></li>
							</c:forEach>
							<c:if test="${paging.groupCurPage ne paging.groupEndPage}">
								<li><a href="" onclick="searchList(<c:out value="${paging.endPage+1}" />,'nextGroup');return false;">&gt;</a></li>
								<li><a href="" onclick="searchList(<c:out value="${paging.totalPage}" />,'endGroup');return false;">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</c:if>
			<div class="text-right">
				<button type="button" class="btn btn-default" id="writeBtn">작성</button>
				<button type="button" class="btn btn-default" id="goList">전체목록</button>
			</div>
		</div>
	</div>
	<div id="footer"></div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var rootPath = "/bitstudy/jsp/home/";
					$("#top").load(rootPath + "top.jsp");
					$("#navi").load(rootPath + "nav.jsp");
					$("#footer").load(rootPath + "footer.jsp");

					 //현재 페이지번호 표시
			        $("#page_${paging.curPage}").addClass("active");
					
					$("#keyword").keypress(function(event) {
						if (event.which == 13)
							searchList();
					});
					$("#searchBtn").click(function() {
						if ($("#keyword").val().trim() == "") {
							alert("검색어를 입력하세요.");
							$("#keyword").focus();
							return false;
						}
						searchList();
					});
					$("#writeBtn").click(function() {
						location = "/bitstudy/jsp/qna/editor.jsp";
					});
					$("#goList").click(function() {
						location = "/bitstudy/jsp/qna/list";
					});
				});

		function searchList(no,option) {
			if(no == undefined || no == null) no = 1;
			var url = "${pageContext.request.contextPath}/jsp/qna/list?opt="+$("#opt").val()+"&keyword="+$("#keyword").val()+"&curpage="+no+"&gpage=";
			
			switch(option){
				case "prevGroup": url += "${paging.groupCurPage-1}"; break;
				case "nextGroup": url += "${paging.groupCurPage+1}"; break;
				case "startGroup": url += "1"; break;
				case "endGroup": url += "${paging.groupEndPage}";break;
				case "next": url += "${paging.groupCurPage}";break;
				case undefined: url += "1";
			}
			location = url; 
		};

	</script>
</body>
</html>