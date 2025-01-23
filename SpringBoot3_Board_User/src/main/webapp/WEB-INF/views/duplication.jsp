<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복검사</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	window.onload = function () {
		document.getElementById('btnUse').style.display = "none"; 
	}
	
	function useId(){
		var id = document.getElementById('id').value;
		opener.document.getElementById('id').value = id;
		opener.document.getElementById('pw').focus();
		opener.document.getElementById('id').removeAttribute('onclick');
		self.close();
	}
	
	function checkId1() {
		var id = document.getElementById('id').value;
		console.log("화면의 요청 id : ",id);
		if(id != ""){
			$.ajax({
				url:"./duplicationAjax.do",
				type:"POST",
				data:"checkId="+id,
				dataType:"json",
				success: function(msg) {
					console.log(typeof msg, msg);
					if(msg.isc === "true"){
						document.getElementById("condition").textContent="사용가능한 아이디 입니다.";
						document.getElementById('btnUse').style.display = "block";
					} else{
						document.getElementById("condition").textContent="중복된 아이디 입니다.";
					}
				},
				error: function() {
					alert("잘못된 요청 처리");
				}
			});
			
		}
	} // jquery ajax
	function checkId2() {
		var id = document.getElementById('id').value;
		console.log("화면의 요청 id : ",id);
		
		fetch('./duplicationFetch.do', {
			method:"POST",
			headers:{
				'Content-Type':'application/x-www-form-urlencoded'
			},
			body:"checkId="+id
		})
		.then((response) => {
			if(!response.ok){
				throw new Error('잘못된 요청');
			}
			return response.text();
		})
		.then((msg) => {
			console.log(typeof msg, msg);
			if(msg === "true"){
				document.getElementById("condition").textContent="사용가능한 아이디 입니다.";
				document.getElementById('btnUse').style.display = "block";
			} else{
				document.getElementById("condition").textContent="중복된 아이디 입니다.";
				document.getElementById('btnUse').style.display = "none";
			}
		})
		.catch((error) => {
			console.log(error);
		});
	}
</script>
</head>
<body>
	<div class="container">
		<h4>아이디 중복 검사</h4>
		<h4>아이디를 입력해 주세요</h4>
		<input class="form-control" type="text" id="id">
		<input class="btn btn-success" type="button" value="확인(jQuery)" onclick="checkId1()">
		<input class="btn btn-success" type="button" value="확인(Fetch)" onclick="checkId2()">
		
		<input class="btn btn-info" type="button" id="btnUse" value="사용" onclick="useId()">
		<div id="condition"></div>
	</div>
</body>
</html>