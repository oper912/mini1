<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<form action="">
<div class="container">
<input class="form-control" id="title" name="title" type="text" placeholder="제목을 입력하세요"><br>
  <textarea id="content" name="content"></textarea>
  <script>
    $(document).ready(function() {
        $('#content').summernote({

        height: 500,                 
		  minHeight: 100,             
		  maxHeight: 700,             
		  focus: true 		

        }
         );
    });
  </script>
  <a href="" class="btn btn-default btn-outline-dark pull-right" role="button"> 취소 </a>
                        <button type="submit" class="btn btn-default pull-right">작성</button>

   </div>
    </form>
</body>
</html>