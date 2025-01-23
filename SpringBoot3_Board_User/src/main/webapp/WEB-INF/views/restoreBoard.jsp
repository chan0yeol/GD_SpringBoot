<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제된 글 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		글 갯수 : ${fn:length(restoreList)}
		<table class="table">
			<thead>
				<tr class="info">
					<th>연번</th>
					<th>아이디</th>
					<th>제목</th>
					<th>등록일</th>
					<th>복구</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${restoreList}" var="vo" varStatus="vs">
					<tr>
						<td>${vo.seq}</td>
						<td>${vo.id}</td>
						<td>${vo.content}</td>
						<td>${vo.regdate}</td>
						<td>
							<button onclick="restoreJquery(this)">jQuery 복구</button>
							<button onclick="restoreFetch(this)">Fetch 복구</button>
							<button onclick="restoreAxios(this)">axios 복구</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
	function restoreJquery(t){
		console.log(t);
		console.log(t.parentNode.parentNode.children[0].textContent);
		console.log(t.closest('tr').children[0].textContent);
		var parentTr = t.closest('tr');
		var seq = t.closest('tr').children[0].textContent;
		$.ajax({
			url:"./restore.do",
			type:"GET",
			data:{seqs:seq},
			success:function(msg){
				console.log(msg);
				if(msg === 'true') {
					parentTr.remove();
				}
			},
			error: function(){
				alert('잘못된 요청입니다.');
			}
		});
	}
	function restoreFetch(t) {
		var parentTr = t.closest('tr');
		var seq = t.closest('tr').children[0].textContent;
		fetch('./restore.do?seqs='+seq)
			.then(response => response.text())
			.then(res => {
				if(res === 'true') {
					parentTr.remove();
				}
			})
			.catch(err => alert("잘못된요청"));
	}
	function restoreAxios(t){
		var parentTr = t.closest('tr');
		var seq = t.closest('tr').children[0].textContent;
		axios.get('./restore.do',{
			params:{seqs:seq}
		})
			.then(response => {
				console.log(response);
				let data = response.data;
				console.log(typeof data, data);
				if(data === true){
					console.log("삭제");
					parentTr.remove();
				}
			})
			.catch(error => console.log("잘못된 요청",err));
	}
</script>
</html>