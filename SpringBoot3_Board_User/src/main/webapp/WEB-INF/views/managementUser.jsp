<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<%@ include file="./header.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("button[name=btnChk]").click(function() {
			event.preventDefault();
			var btnOrder = $(this).val();
			console.log("요청변경 권한 : ", btnOrder);

			var chkRow = $("input[name=chkId]:checked");

			for (let i = 0; i < chkRow.length; i++) {
				if (btnOrder == 'toAuth') {
					chkRow[i].parentNode.parentNode.children[4].textContent = 'A';
				} else {
					chkRow[i].parentNode.parentNode.children[4].textContent = 'U';
				}
			}
			changeAuthAjax(btnOrder);
		});
	});
	function changeAuthAjax(order) {
		var chkIds = document.querySelectorAll("input[name=chkId]:checked");
// 		console.log(chkIds.parentNode.parentNode);
		if (chkIds.length == 0) {
			alert("한개 이상의 값을 선택해 주세요");
		} else {
			/*
				jQuery로 form 데이터 값을 JSON으로 변경 -> JSON 문자열로 생성
			*/
// 			var jsonData = $("form").serializeArray(); // 배열로 가져옴
// 			console.log("serializeArray() : ",jsonData , typeof jsonData);
// 			var json = {};
// 			jsonData.forEach((item) => {
// 				json[item.name] = item.value;
// 			});
// 			console.log(typeof json, json);
// 			console.log("json 문자열로 만들기", JSON.stringify(json));
			/*
			 	javascript 로 form 데이터 값을 JSON으로 변경 -> JSON 문자열로 생성
			*/
			var fms = document.querySelector("form");
			var formData = new FormData(fms);
			var jsonData = {};
			formData.forEach((value, key) => {
				jsonData[key] = value;
			});
			console.log("javascript formData : ", jsonData , typeof jsonData);
			var jsonStr = JSON.stringify(jsonData);
			console.log("문자열로 출력 : ", jsonStr);
			var strToJson = JSON.parse(jsonStr);
			console.log("JSON 객체로 parse : ",strToJson);
			// javascript에서 form 데이터 만드는법
// 			var form = document.querySelector("form");
// 			const formData = new FormData(form);
// 			const query = [];
// 			console.log(formData);
// 			formData.forEach((key,value) => {
// 				query.push(encodeURIComponent(key)+"="+encodeURIComponent(value));
// 			});
// 			console.log(query);
// 			var resultQuery = query.join("&");
// 			console.log(resultQuery);
			var data = $("form").serialize();
			console.log(data);
			var url = "./"+order+".do";
			$.ajax({
				url:url,
				method:"POST",
				data:data,
				success:function (msg) {
					console.log(msg);
					if(msg === 'true') {
						alert("변경 완료");
					} else{
						alert("잘못된 요청");
					}
				},
				error : function(){
					alert("잘못된 요청");
				}
			});
			
		}
	}
</script>
</head>
<body>
	<div class="container">
		<form action="">
			<table class="table">
				<thead>
					<tr>
						<td>선택</td>
						<td>아이디</td>
						<td>이름</td>
						<td>이메일</td>
						<td>권한</td>
						<td>활성여부</td>
						<td>가입일</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList}" var="vo">
						<tr>
							<td><input type="checkbox" name="chkId" value="${vo.id}" /></td>
							<td>${vo.id}</td>
							<td>${vo.name}</td>
							<td>${vo.email}</td>
							<td>${vo.auth}</td>
							<td>${vo.enable}</td>
							<td>${vo.joindate}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<button name="btnChk" value="toAuth">관리자(A)로 변경</button>
							<button name="btnChk" value="toUser">유저(U)로 변경</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>

</html>