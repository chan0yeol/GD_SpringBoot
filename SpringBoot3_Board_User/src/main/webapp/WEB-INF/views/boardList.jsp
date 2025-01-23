<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function checkBox(){
		console.log("submit 동작 제어");
		var chkVals_isc = document.querySelectorAll('input[type=checkbox][name=chkVal]:checked');
		console.log(chkVals_isc.length);
		if(chkVals_isc.length == 0) {
			alert('한개 이상의 게시글을 선택해야 합니다.');
			return false;
		}
		
	}
	function allCheckValues(bool){
		console.log("allCheck 상태",typeof bool,bool);
		var chkVals = document.getElementsByName('chkVal');
		console.log("chkVals 갯수", chkVals.length);
		for(let i =0; i<chkVals.length;i++){
			chkVals[i].checked = bool;
		}
		
	}
	
</script>
</head>
<body>
	<div class="container">
		<div>
			<c:if test="${loginVo ne null}">
				${loginVo.name}님 반갑습니다.
				<input type="button" value="로그아웃" onclick="location.href='./logout.do'"/>
			</c:if>
			<c:if test="${loginVo.auth eq 'A'}">
				<input type="button" value="회원관리" onclick="location.href='./managementUser.do'">
			</c:if>
		</div>
		<form action="./multiDelete.do" method="POST" onsubmit="return checkBox()">
			<input type="submit" value="글삭제">
			<input type="button" value="글작성" onclick="location.href='./insertBoard.do'">
			<c:if test="${loginVo.auth eq 'A'}">
				<input type="button" value="회원전체조회" onclick="location.href='./userSelectAll.do'">
				<input type="button" value="삭제글 복구" onclick="location.href='./restoreBoard.do'">
			</c:if>
			<table class="table">
				<thead>
					<tr class="success">
						<td><input type="checkbox" onclick="allCheckValues(this.checked)"></td>
						<td>연번</td>
						<td>아이디</td>
						<td>제목</td>
						<td>등록일</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardList}" var="vo" varStatus="vs">
						<tr>
							<td><input type="checkbox" name="chkVal" value="${vo.seq}"></td>
							<td>${fn:length(boardList)-vs.index}</td>
							<td>${vo.id}</td>
							<td>
								<c:choose>
									<c:when test="${vo.delflag eq 'Y' }">
									<span style="color:#ccc; font-size:9px;" >--- 관리자에 의해 삭제된 게시글 입니다. ---</span>									
									</c:when>
									<c:otherwise>
										<a href="./detailBoard.do?seq=${vo.seq}">${vo.title}</a>
									</c:otherwise>
								</c:choose>
							
							</td>
							<td>
								
								<fmt:parseDate var="cDate" value="${vo.regdate}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${cDate}"/>
							</td>
						</tr>	
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
</html>