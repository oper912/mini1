<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="fileForm" id="fileForm" method="POST" action="test03.jsp" enctype="multipart/form-data">
    <input type="text" name="title" id="title">
    <input type="file" name="uploadFile" id="uploadFile">
    <input type="submit" value="전송">
</form>

<a href="down.jsp">파일 다운받기</a>
</body>
</html>