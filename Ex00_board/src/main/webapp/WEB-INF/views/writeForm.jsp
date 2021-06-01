<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 작성</title>
</head>
<body bgcolor="E3F4EA">
<div style="margin : 40px 0px 0px 70px">	
<h2>게시물 작성</h2>
<h4 style="color:red">${msg}</h4>
<form action="write" method="post" enctype="multipart/form-data">
	<input type="hidden" name = "id" value="${cid}">
	<input type="hidden" name = "writer" value="${cname}">
	<input type="hidden" name = "dd" value=<%= sf.format(nowTime) %>>
	
	제 목 <input type="text" name="title" size="20" placeholder="최대 20자" onfocus="this.placeholder=''" onblur="this.placeholder='최대 20자'"><br><br />
	
	<textarea type="text" name="content" rows="10" cols="70"placeholder="최대 300자" onfocus="this.placeholder=''" onblur="this.placeholder='최대 300자'"></textarea><br />
	
	비밀글<input type="checkbox" name="bimil" value="true">&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input multiple="multiple" type="file" name="files" id="files" accept="image/*" />
	<div style="position:absolute; margin:-56px 0px 0px 433px;">
	<input type="submit" value="작성">
	</div>
	<a style="margin-left:30px" href="list">목록보기</a><br />

</form>
</div>

</body>
</html>