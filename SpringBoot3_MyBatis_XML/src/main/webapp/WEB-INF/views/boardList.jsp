<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 전체 조회</title>
</head>
<body>
<!--  TODO 010 Controller 에서 보낸 model 값 출력 -->
	<c:forEach items="${lists}" var="vo" varStatus="vs">
		<div>${vs.index} / ${vo}</div>
	</c:forEach>
	
	<!-- TODO 011 myBatis Interface Mapper를 통한 호출 -->
	<a href="./boardListInterface.do">interfaceMapper</a>
</body>
</html>