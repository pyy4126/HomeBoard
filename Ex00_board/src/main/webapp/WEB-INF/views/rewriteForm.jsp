<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정</title>
</head>
<body bgcolor="E3F4EA">
<c:set var="day" value="<%= sf.format(nowTime) %>" />
<div style="margin : 0px 0px 0px 70px">	
<h2>게시물 수정</h2>
<h4>${msg}</h4>
<c:choose>
<c:when test="${ID != cur.id }">
비정상적인 접근입니다
<a href="list">돌아가기</a><br />
</c:when>
<c:when test="${cur.dd != day}">
게시물 수정은 당일 작성한 글만 가능합니다 
<a href="list">돌아가기</a><br />
</c:when>
<c:when test="${cur.dd == day}">
<form action="rewrite" method="post" enctype="multipart/form-data">
	<input type="hidden" name = "dd" value=<%= sf.format(nowTime) %>>
	<input type="hidden" name = "num" value="${cur.num }">
	제 목 <input type="text" name="title" size="30" value="${cur.title}" placeholder="최대 20자" onfocus="this.placeholder=''" onblur="this.placeholder='최대 20자'"><br><br />
	
	<textarea type="text" name="content" rows="10" cols="70" placeholder="최대 300자" onfocus="this.placeholder=''" onblur="this.placeholder='최대 300자'">${cur.content}</textarea><br />
	
	<c:choose>
	<c:when test="${cur.bimil == 'true'}">비밀글<input type="checkbox" name="bimil" value="true" checked="on"></c:when>
	<c:otherwise>비밀글<input type="checkbox" name="bimil" value="true"></c:otherwise>
	</c:choose>
	<div style="position:absolute; margin:-53px 0px 0px 433px;">
	<input type="submit" value="작성">
	</div>
	<a style="margin-left:342px" href="list">목록보기</a><br><br />

	<div style="margin:0px 0px -39px 284px">
	<font color="red" size="1pt">*첨부파일 추가/제거는 바로 이루어집니다</font>
	</div>
	<c:if test="${fn:length(filelist) != 0}">
	<br>
	&nbsp;<h5 style="display:inline">첨부</h5>
		<div style="height:80px; width:473px; border:2px solid #ffffff; border-radius: 0.5em;">
			<div style="overflow: auto; width:473px; height:80px;">
				<c:forEach items="${filelist}" var="name">
					<input type="hidden" name="filename" value="${name.name} " />
					&nbsp;<a style="color:red; display:inline" href="deletefile?idx=${name.idx}">X</a>&nbsp;${name.simple_name}
					<br/>
				</c:forEach>
			</div>
		</div>
	</c:if>
	<c:if test="${fn:length(filelist) == 0}">
	<br>
	&nbsp;<h5 style="display:inline">첨부</h5>
		<div style="height:80px; width:473px; border:2px solid #ffffff; border-radius: 0.5em;">
		</div>
	</c:if>
</form>

<div style="margin: 10px 0px 0px 0px;">
	<form action="rewritefile" method="post" enctype="multipart/form-data">
		<input multiple="multiple" type="file" name="files" id="files" accept="image/*" />
		<input type="submit" value="파일업로드" />
	</form>
</div>
</c:when>
</c:choose>
</div>

</body>
</html>