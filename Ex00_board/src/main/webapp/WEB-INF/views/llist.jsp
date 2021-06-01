<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>좋아요 리스트</title>
</head>
<body bgcolor="E3F4EA">

<h3>★좋아요★</h3>

<c:forEach items="${llist}" var="name">
	<div style="line-height:160%">
	&nbsp;&nbsp;&nbsp;<font color="blue">${name}</font>
	</div>
</c:forEach>

</body>
</html>