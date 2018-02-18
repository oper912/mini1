<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일정조회페이지</title>
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

<form class="form-inline"  action ="${pageContext.request.contextPath}/jsp/calendar/delete" method = "post" >

<input type="hidden" name = "fwPage" value="popupmodi.jsp">

  <div class="form-group">
       <input values = "calNo"  type="number" name ="calNo" class="form-control" id="exampleInputcalNo" placeholder="글번호">
  </div>


  <div class="form-group">
    <label for="exampleInputName">이름</label>
    <input values = "name" Disabled type="text" name ="name" class="form-control" id="exampleInputName2" placeholder="이름을 입력하세요,">
  </div>
  
  
  <div class="form-group">
    <label for="exampleInputEmail">이메일</label>
    <input type="email" Disabled name = "email"class="form-control" id="exampleInputEmail2" placeholder="bitcamp@bitcamp.com">
  </div>
  
  
  
  
  <div class="form-group">
    <label for="exampleInputTitle">제목</label>
    <input type="text" Disabled name="title" class="form-control" id="exampleInputtitle" placeholder="JSON 강의 있습니다">
  </div>
  <div class="form-group">
    <label for="exampleInputContent">내용</label>
    <input type="text" Disabled name="content" class="form-control" id="exampleInputStartdate" placeholder="헤드 퍼스트 308쪽 -310쪽">
  </div>
  
  
  
  
  
  

  <div class="form-group">
    <label for="exampleInputLocation">장소</label>
    <input type="text" Disabled name="location" class="form-control" id="exampleInputLocation" placeholder="몇층 몇호인지 상세하게 적어주세요!">
  </div>
  <div class="form-group">
    <label for="exampleInputStartDate">날짜</label>
    <input type="date" Disabled name="startDate" value = "2017-01-01" min = "2017-01-01" max="2999-12-31" class="form-control" id="exampleInputStartdate" placeholder="2017-11-05">
  </div>
  
  <div class="form-group">
    <label for="exampleInputStartTime">시작시간</label>
    <input type="time" Disabled name ="startTime" class="form-control" id="exampleInputStartTime" placeholder="오전 00시 00분">
  </div>
  
  
   <div class="form-group">
    <label for="exampleInputEndTime">종료시간</label>
    <input type="time"  Disabled name="endTime" class="form-control" id="exampleInputEndime" placeholder="오후 00시 00분">
  </div>
  
  
  
     <div class="form-group">
    <label for="exampleInputTextColor">글씨색깔</label>
    <input type="color" Disabled name="textColor" class="form-control" id="exampleInputTextColor" placeholder="#80BFFF">
  </div>
  
  
   <div class="form-group">
    <label for="exampleInputBackcolor">바탕색깔</label>
    <input width=50% type="color" Disabled name="backColor" class="form-control" id="exampleInputbackColor"
     placeholder="#80BABF">
  </div>
  
  
  

  <input type = "button" onclick = "window.location='popupmodi.jsp'" name = "updatePlan" class="btn btn-default" value = "수정">
  <button type="submit"  name = "createPlan" class="btn btn-default">삭제</button>
  <input type = "button" class="btn btn-default" onclick="self.close()" value = "취소">
  
</form>
</body>
</html>