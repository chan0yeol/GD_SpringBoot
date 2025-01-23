<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 화면</title>
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
		<h3>게시글 상세</h3>
		<div>
			<div class="form-control">아이디 : ${boardVo.id}</div>
			<div class="form-control">제목 : ${boardVo.title}</div>
			<div class="form-control">내용 :${boardVo.content}</div>
			<div class="form-control">작성일 : ${boardVo.regdate}</div>
		</div>
		<div>
			<input type="button" value="삭제" onclick="deleteOne()">
			<input type="button" value="수정">
			<input type="button" value="답글" onclick="answerboardOne()">
		</div>
	</div>
</body>
<script type="text/javascript">
	function deleteOne() {
		console.log(${boardVo.seq});
		var seq = ${boardVo.seq};
		location.href="./multiDelete.do?chkVal="+seq;
	}
	function answerboardOne() {
		console.log(${boardVo.seq});
		var seq = ${boardVo.seq};
		location.href="./replyBoard.do?seq="+seq
	}
</script>
</html>