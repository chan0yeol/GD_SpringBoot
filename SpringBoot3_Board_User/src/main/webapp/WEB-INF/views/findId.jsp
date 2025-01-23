<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 조회 화면</title>
<%@ include file="./header.jsp" %>
</head>
<body>
	<div class="container">
		이름 : <input class="form-control" id="name"><br>
		이메일 : <input class="form-control" id="email"><br>
		<input type="button" value="아이디 찾기" onclick="findId()">
		<input type="button" value="닫기" onclick="self.close()"><br>
		<p id="info"></p>
<script type="text/javascript">
	function findId(){
		var name = document.getElementById('name').value;
		var email = document.getElementById('email').value;
		var info = document.getElementById('info');
		
		const emailRegex=/^[a-zA-Z0-9,_%+-]+@[a-zA-Z0-9.-]+\.[a-zA-z]{2,}$/;
		if(email.match(emailRegex)) {
			$.ajax({
				url:"./findId.do",
				type:"POST",
				async:true,
				data:{name:name,"email":email},
				success : function(data) {
					console.log(typeof data, data);
					if(data == '') {
						info.innerHTML ="아이디를 찾을 수 없습니다.";
					} else{
						info.innerHTML = "회원님의 아이디는 ["+data+"]입니다.";
					}
				},
				error : function(err) {
					alert("관리자에게 문의");
				}
			});
		} else{
			info.innerHTML = "잘못된 이메일 형식입니다.";
		}
	}
</script>
	</div>
</body>

</html>