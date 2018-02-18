<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int no = Integer.parseInt(request.getParameter("libNo"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=no%>
	<script>
		alert('번 글을 수정하셨습니다.');
		location.href='/bitstudy/html/library/librarydetail?libNo='<%=no%>;
	</script>
</body>
</html>