<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일정수정페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>

<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
	}
</style>



<body>

<form class="form-inline" action ="${pageContext.request.contextPath}/jsp/calendar/update"  method="post">






 <div class="form-group">
    <input type="number" Disabled name = "calNo"class="form-control" id="exampleUpdatecalNo" >
 </div>


  <div class="form-group">
    <label for="exampleInputName">이름</label>
 
    <input  type="text" Disabled name ="name" class="form-control" id="exampleInputName2">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail">이메일</label>
    <input type="email" Disabled name = "email"class="form-control" id="exampleInputEmail2" >
  </div>
  
  
  
  
  <div class="form-group">
    <label for="exampleInputTitle">제목</label>
    <input type="text" name="title" class="form-control" id="exampleInputtitle" >
  </div>
  <div class="form-group">
    <label for="exampleInputContent">내용</label>
    <input type="text" name="content" class="form-control" id="exampleInputStartdate" >
  </div>
  
  
  
  
  
  

  <div class="form-group">
    <label for="exampleInputLocation">장소</label>
    <input type="text" name="location" class="form-control" id="exampleInputLocation" >
  </div>
  <div class="form-group">
    <label for="exampleInputStartDate">날짜</label>
    <input type="date" name="startDate" value = "2017-01-01" min = "2017-01-01" max="2999-12-31" class="form-control" id="exampleInputStartdate">
  </div>
  
  <div class="form-group">
    <label for="exampleInputStartTime">시작시간</label>
    <input type="time" name ="startTime" class="form-control" id="exampleInputStartTime">
  </div>
  
  
   <div class="form-group">
    <label for="exampleInputEndTime">종료시간</label>
    <input type="time" name="endTime" class="form-control" id="exampleInputEndime">
  </div>
  
  
  <!-- 글씨 색깔을 지정해주지만, 널일 경우에 랜덤으로 -->
     <div class="form-group">
    <label for="exampleInputTextColor">글씨색깔</label>
    <input type="color" name="textColor" class="form-control" id="exampleInputTextColor" >
  </div>
  
  
   <div class="form-group">
    <label for="exampleInputBackcolor">바탕색깔</label>
    <input width=50% type="color" name="backColor" class="form-control" id="exampleInputbackColor" >
  </div>
  
  

  <button type="submit" name = "modiPlan" class="btn btn-default" >수정</button>
  <input type = "button" class="btn btn-default" onclick="self.close()" value = "취소">

</form>
</body>
</html>