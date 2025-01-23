<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 전체 리스트 [관리자]</title>
<%@ include file="./header.jsp" %>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>회원검색</legend>
			<form name="search-frm" autocomplete="on">
				<select name="opt" id="type">
					<option value="id">아이디</option>
					<option value="name">이름</option>
				</select>
				<input type="text" name="keyword" placeholder="검색어 입력하세요">
				<input type="button" value="검색" onclick="getSearchUser(event)">
			</form>
			<div>
				<h4>검색된 회원</h4>
				<div id="viewDetail"></div>
			</div>
		</fieldset>
		<script type="text/javascript">
			function getSearchUser(event){ // jquery 요청을 통한 CreateDom
				console.log(event);
				event.preventDefault();
				
				var opt = document.getElementById('type');
				var keyword = document.getElementsByName('keyword')[0].value;
				var optValue = opt.options[opt.selectedIndex].value;
				console.log(keyword, optValue);
				$.ajax({
					url:"./getSearchUser.do",
					method:"POST",
					data:{opt:optValue,"keyword":keyword},
					dataType:"json",
					success: function(msg){
						console.log(typeof msg, msg);
						var html = "<table class='table'>"
						$.each(msg,function(idx,value){
							html += "<tr>";
							html += "<td>"+(idx+1)+"</td>";
							html += "<td>"+value.id+"</td>";
							html += "<td>"+value.name+"</td>";
							html += "</tr>";
							$("#viewDetail").html(html);
							
						});
							html += "</table>";
						// javascript createElement 방식
						var table = document.createElement("table");
						table.className="table";
						
						msg.forEach((value,index)=>{
							var tr = document.createElement("tr");
							var indexTd = document.createElement("td");
							indexTd.textContent = index+1;
							tr.appendChild(indexTd);
							var idTd = document.createElement("td");
							idTd.textContent = value.id;
							tr.appendChild(idTd);
							var nameTd = document.createElement("td");
							nameTd.textContent = value.name;
							tr.appendChild(nameTd);
							table.appendChild(tr);
						});
						var viewDetail = document.getElementById("viewDetail");
// 						viewDetail.innerHTML = "";
						viewDetail.appendChild(table);
					},
					error :function() {
						console.log("잘못된 요청");
					}
				});
			}
		// --- end
		
		//javascript 방식 createDome
		
		</script>
		
		<h4>[관리자] 회원전체 조회</h4>
		<table class="table">
			<thead>
				<tr>
					<td>번호</td>
					<td>아이디</td>
					<td>이름</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${userList}" varStatus="vs"> 
					<tr>
						<td>${vs.count}</td>
						<td>${vo.id}</td>
						<td>${vo.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>