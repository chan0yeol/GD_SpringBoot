<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인 화면 이동</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" style="margin-top:100px;">
		<form action="./login.do" method="POST">
			<table class="table table-hover">
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="id" required="required">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="password" required="required">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" value="로그인">
						<input type="button" value="회원가입" onclick="javascript:location.href='./signupForm.do'">
						<input type="button" value="아이디 찾기" onclick="findId()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function findId() {
		window.open('./findIdWindow.do','_blank',"width=500px,height=300px");
	}
</script>
</html>