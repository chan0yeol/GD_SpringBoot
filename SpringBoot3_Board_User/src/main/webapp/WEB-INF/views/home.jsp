<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h2>home.do 요청처리 페이지</h2>
	<form action="./test.do" method="post">
		<input type="submit" value="test">
	</form>
	
	${test}
</body>
</html>