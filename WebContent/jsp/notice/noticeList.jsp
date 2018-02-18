<%@page import="java.util.ArrayList"%>
<%@page import="com.bitstudy.notice.domain.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bit Study</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>
	<body>
		<div id="top"></div>
		<div id="navi"></div>
		<div id="content">
			<div class="container">
				<h2>공지사항</h2>
				<div class="pull-right">
					<select name="opt" id="opt">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="all">제목+내용</option>
					</select>
				    <input type="text" name="keyword" id="keyword" value="${search.keyword}" placeholder="Search">
				    <button class="btn btn-default" id="searchBtn" type="button">
				    	<i class="glyphicon glyphicon-search"></i>
				    </button>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-lg-1 text-center">번호</th>
							<th class="col-lg-6 text-center">제목</th>
							<th class="col-lg-2 text-center">작성일</th>
							<th class="col-lg-1 text-center">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty list}">
								<tr><td colspan="4" class="no-list">등록된 게시물이 없습니다.</td></tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="notice" items="${list}">
								<tr>
									<td class="text-center"><c:out value="${notice.no}" /></td>
									<td><a href="${pageContext.request.contextPath}/jsp/notice/detail?no=<c:out value="${notice.no}" />"><c:out value="${notice.title}" /></a></td>
									<td class="text-center"><c:out value="${notice.regDate}" /></td>
									<td class="text-center"><c:out value="${notice.readCount}" /></td>
								</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
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
					<c:if test="${user.admin}">
						<button type="button" class="btn btn-gray" onclick="">작성</button>
					</c:if>
					<c:if test="${not empty search.keyword}">
						<button type="button" class="btn btn-primary" id="goList">전체목록</button>
					</c:if>
				</div>
			</div>
		</div>
		<div id="footer"></div>
		
		<script type="text/javascript">
			$(document).ready(function(){
				var rootPath = "${pageContext.request.contextPath}/jsp/home/";
		        $("#top").load(rootPath + "top.jsp");
		        $("#navi").load(rootPath + "nav.jsp");
		        $("#footer").load(rootPath + "footer.jsp");
		        
		        //검색 시 화면에 선택된 select값 표시  
		        $("#opt option").each(function(){
		        	if($(this).val() == "${search.opt}"){
		        		$(this).prop("selected","selected");
		        	}
		        });
		        
		        //현재 페이지번호 표시
		        $("#page_${paging.curPage}").addClass("active");
		        
				$("#keyword").keypress(function(event){
					if(event.which == 13) searchList();
				});
				
				$("#searchBtn").click(function(){
					if($("#keyword").val().trim() == ""){
						alert("검색어를 입력하세요.");
						$("#keyword").focus();
						return false;
					}
					searchList();				
				});
				
				$("#writeBtn").click(function(){
					location = "${pageContext.request.contextPath}/jsp/notice/write?mode=write";
				});
				
				$("#goList").click(function(){
					location = "${pageContext.request.contextPath}/jsp/notice/list";
				});
			});
			function searchList(no,option){
				if(no == undefined || no == null) no = 1;
				var url = "${pageContext.request.contextPath}/jsp/notice/list?opt="+$("#opt").val()+"&keyword="+$("#keyword").val()+"&curpage="+no+"&gpage=";
				
				switch(option){
					case "prevGroup": url += "${paging.groupCurPage-1}"; break;
					case "nextGroup": url += "${paging.groupCurPage+1}"; break;
					case "startGroup": url += "1"; break;
					case "endGroup": url += "${paging.groupEndPage}";break;
					case "next": url += "${paging.groupCurPage}";break;
					case undefined: url += "1";
				}
				location = url; 
			}
		</script>
	</body>
</html>