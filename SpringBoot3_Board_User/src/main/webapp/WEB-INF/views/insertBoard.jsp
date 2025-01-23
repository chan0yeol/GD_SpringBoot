<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성 Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form action="./insertBoard.do" method="post">
			<div class="form-group">
				<label for="id">아이디</label>
				<div class="form-control">${sessionScope.loginVo.id}</div>
			</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input class="form-control" type="text" id="title" name="title" required="required">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" id="content" name="content"></textarea>
			</div>
			<button class="btn btn-success" type="submit">글작성</button>
			<button class="btn btn-default" onclick="javascript:history.back(-1)">취소</button>
		</form>
	</div>
</body>
</html>