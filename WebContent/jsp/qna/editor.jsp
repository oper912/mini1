<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Study</title>
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.7/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.7/summernote.js"></script>
</head>
<body>
	<div id="top"></div>
	<div id="navi"></div>
	<div id="qnacontent">
<form action="/bitstudy/jsp/qna/editor" method="post">
<div class="container">
  <input type="hidden" name="name" value="${user.name}">
  <input type="hidden" name="id" value="${user.id}">
<input class="form-control" id="title" name="title" type="text" placeholder="제목을 입력하세요"><br>
  <textarea id="content" name="content"></textarea>
  <script>
    $(document).ready(function() {
        $('#content').summernote({
        		  
        height: 300,                 
		minHeight: 100,             
		maxHeight: 500,             
		focus: false 		
        }
         );
    });
  </script>
  	<a class="btn btn-default btn-outline-dark pull-right" role="button" id="golist"> 취소 </a>
	<button type="submit" class="btn btn-default pull-right">작성</button>
   </div>
    </form>
  </div>
  <div id="footer"></div>
		
		<script type="text/javascript">
		$(document).ready(function(){
			var rootPath = "/bitstudy/jsp/home/";
	        $("#top").load(rootPath + "top.jsp");
	        $("#navi").load(rootPath + "nav.jsp");
	        $("#footer").load(rootPath + "footer.jsp");
	        
			$("#goList").click(function(){
				location = "/bitstudy/html/qna/list";
			});
		});
		</script>
</body>
</html>