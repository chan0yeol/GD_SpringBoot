<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글작성화면</title>
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
		<form action="./replyBoard.do" method="POST">
			<input type="hidden" name="seq" value="${boardVo.seq}">
			<div class="form-group">
				<label for="id">아이디</label>
				<div class="form-control">${loginVo.id}</div>
			</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" id="title" name="title" required="required" placeholder="원본제목:${fn:substring(boardVo.title,0,10)}"/>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" name="content" id="content" required="required"></textarea>
			</div>
			<div>
				<button class="btn btn-success" type="submit">답글입력</button>
				<button class="btn btn-info" type="reset">입력초기화</button>
				<button class="btn btn-default" onclick="history.back(-1)">취소</button>
			</div>
		</form>
	</div>
</body>
</html>