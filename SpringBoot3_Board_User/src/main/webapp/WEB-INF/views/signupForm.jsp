<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>
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
		<form action="./signUp.do" method="post">
			<h3>회원가입</h3>
			<h3>필수 값을 반드시 입력해 주세요</h3>
			<div>
				<input class="form-control" name="name" id="name" placeholder="이름">
				<input class="form-control" name="id" id="id" placeholder="아이디 작성 (클릭시 중복체크)" maxlength="10" onclick="duplicate()">
				<input class="form-control" type="password" name="password" id="pw" placeholder="비밀번호">
				<input class="form-control" type="password" id="pwOk" placeholder="비밀번호 확인">
				<input class="form-control" name="email" id="email" placeholder="ex)info@info.com">
			</div>
			<div>
				<input class="btn btn-primary" type="submit" value="회원가입">
				<input class="btn btn-danger" type="button" value="가입 취소" onclick="javascript:history.back(-1);">
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	document.forms[0].addEventListener('submit', (event)=> {
		event.preventDefault();
		
		var name = document.getElementById('name');
		var id = document.getElementById('id');
		var password = document.getElementById('pw');
		var pwOk = document.getElementById('pwOk');
		var email = document.getElementById('email');
		
		const emailRegex = /^[a-zA-Z0-9,_%+-]+@[a-zA-Z0-9.-]+\.[a-zA-z]{2,}$/;
		if(name.value.length<2) {
			alert("정확한 성명을 작성해 주세요");
			return;
		}
		if(password.value != pwOk.value) {
			alert("작성한 비밀번호가 일치하지 않습니다.");
			return;
		}
		if(!email.value.match(emailRegex)){
			alert("잘못된 이메일 형식 입니다.");
			return;
		}
		document.forms[0].submit();
	});
	function duplicate(){
		window.open("./duplication.do","중복검사","width=500px,height=500px");
	}
</script>
</html>