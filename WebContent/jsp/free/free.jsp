<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/bitstudy/css/default.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
	$(document).ready(function() {
		//보여주기
		$('.on').click(function() {
			var _this = $(this);
			_this.parent().find(('.section-1')).css('display', 'block');
		});

		//숨기기 
		$('.off').click(function() {
			var _this = $(this);
			_this.parent().find(('.section-1')).css('display', 'none');
		});
	});
</script>
<style>
.section-1 {
	display: none;
}
</style>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	

	
	
</head>
<body>

	<div id="top"></div>
	<div id="navi"></div>
	<div id="content">
		<%-- 글 등록--%>
		<div class="container">
			<span style="float: left"></span><span><h3 class="text-center">자유게시판</h3></span>
			<form action="/bitstudy/jsp/free/call">
				<p>
					<textarea class="form-control" rows="7" name="content"></textarea>
					<p>
					<button type="submit" class="btn btn-primary">등록</button>
					
					<!--  <input type="hidden" name="id" value="${b.id}" />-->
			
			</form>
			<%-- 글 출력--%>

			<c:forEach var="b" items="${list}">
				<ul>
					<c:choose>
						<c:when test="${b.no == b.groupNo}">
							<li>
								<c:choose>
   	  								<c:when test="${empty user.image}">
   	  									<img src="http://localhost:8000/bitstudy/profile/noImage.png" class="img-rounded" width="30" height="30" />
   	  								</c:when>
   	  								<c:otherwise>
   	  									<img src="http://localhost:8000/bitstudy/profile/${user.image}" class="img-rounded" width="30" height="30" />
   	  								</c:otherwise>
   	  							</c:choose>
								<p><c:out value="${b.name}" /></p>						
								<c:out value="${b.content}" />
								<c:out value="${b.regDate }" />
							
							<table>
								<tr>
						<%-- 글 수정버튼--%>
									<td>
										<div class="section">
											<form action="/bitstudy/jsp/free/update">
												<div class="section-1">
													<p>
														<textarea name="content" cols="80" rows="1"></textarea>
														<p><button type="submit" name="update" value="${b.no}">등록</button></p>
														<input type="hidden" name="id" value="${b.id}"/>
													</p>
												</div>		
											</form>
											<c:if test="${b.id==user.id}">
											<button class="on">수정</button>
											</c:if>
										</div>
									</td>

									<%-- 댓글 버튼--%>
									<td>
										<div class="section">
											<form action="/bitstudy/jsp/free/commentcall">
												<div class="section-1">
													<button type="submit" name=group_no value="${b.no}">등록</button>
													<textarea name="content" cols="80" rows="1"></textarea>
												</div>
											</form>
											<button class="on">댓글</button>
										</div>
									</td>
									<%-- 글 삭제버튼--%>
			
									<td><form action="/bitstudy/jsp/free/delect">
											<c:if test="${b.id==user.id}">
												<button type="submit" name="delete" value="${b.groupNo}">삭제</button>
											</c:if>
										</form>
									</td>
								</tr>
							</table>
						</li>
					</c:when>
						
					<c:when test="${b.no != b.groupNo}">
					<ul> 
						<li>
							<p><c:out value="${b.name}" /> </p>
							<c:out value="${b.content }" />
							<c:out value="${b.regDate }" />
								
							<table>
								<tr>
									<%-- 글 수정버튼--%>
									<td><div class="section">
											<form action="/bitstudy/jsp/free/update">
												<div class="section-1">
													<p>
														<textarea name="content" cols="80" rows="1"></textarea>
													
																<p>
														<button type="submit" name="update" value="${b.no}">등록</button>
												
															</div>
											</form>
											<c:if test="${b.id==user.id}">
											<button class="on">수정</button>
											</c:if>
										</div></td>
			
									<%-- 글 삭제버튼--%>
			
									<td><form action="/bitstudy/jsp/free/commentdelect">
											<c:if test="${b.id==user.id}">
											<button type="submit" name="delete" value="${b.no}">삭제</button>
											</c:if>
										</form>
									</td>
								</tr>
							</table>
						</li>
					</ul>	
				</c:when>
			</c:choose>
		</ul>
				<%--class="btn btn-default btn-outline-dark pull-right" --%>
				
			</c:forEach>
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			var rootPath = "/bitstudy/jsp/home/";
			$("#top").load(rootPath + "top.jsp");
			$("#navi").load(rootPath + "nav.jsp");
			$("#footer").load(rootPath + "footer.jsp");
		});
	</script>
	
</body>
</html>