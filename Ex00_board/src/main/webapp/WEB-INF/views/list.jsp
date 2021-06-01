<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 목록</title>
</head>
<body bgcolor="E3F4EA">
<div style="margin : 40px 0px 0px 70px">
<h2>게시물 목록</h2>
<form action="home">
<div style="margin: -55px 0px 30px 0px">
	<input style="width:130px; height:30px; opacity:0; cursor:pointer;" type="submit" value="home">
</div>
</form>
<div style="margin:-45px 0px 15px 680px">
	<form action="logout" method="post">
		<input type="submit" value="로그아웃">
	</form>
</div>
<c:set var="ID" value="${cid}"/>
<table width="750" cellpadding="0" cellspacing="0" border="1" style="border-top-left-radius:0.4em; border-top-right-radius:0.4em">
	<tr>
		<td style="width:50px; border-top-left-radius:0.3em"><center>조회수</center></td>
		<td style="width:120px"><center>작성일</center></td>
		<td style="width:100px"><center>작성자</center></td>
		<c:if test="${cid != 'admin'}">
		<td style="border-top-right-radius:0.3em"><center>제 목</center></td>
		</c:if>
		<c:if test="${cid == 'admin'}">
		<td><center>제 목</center></td>
		<td style="width:45px; border-top-right-radius:0.3em"><center>삭 제</center></td>
		</c:if>
		</tr>
		
	<c:forEach items="${list}" var="dto">
		<tr>
			<td><center>${dto.view}</center></td>
			<td><center>${dto.dd}</center></td>
			<td><center>${dto.writer}</center></td>
			<td><center><a style="text-decoration:none" href="view?num=${dto.num}">
			<c:if test="${dto.fflag == 'exist' }">
			<img src="/image/icon_file.png" width="13" height="13"/>
			</c:if>
			${dto.title}
				<c:if test="${dto.review_cnt > 0}">
					<font size="1.5em">[${dto.review_cnt}]</font>
				</c:if></a></center>
			</td>
			<c:if test="${cid == 'admin'}">
			<td><center><a href="delete?num=${dto.num}">X</a></center></td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<div style="margin:15px 0px 0px 0px">
<form action="search" method="post">
	<select name="sch" size="1">
		<option value="writer" selected>작성자</option>
		<option value="id">ID</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="what" placeholder="빈 창 검색 = 모든 게시물" onfocus="this.placeholder=''" onblur="this.placeholder='빈 창 검색 = 모든 게시물'" >
	<input type="submit" value="검색">
	<div style="margin:-34px 0px 0px 700px">
		<a href="writeForm">글작성</a>
	</div>
</form>
</div><br>

<c:if test="${flag == null}" >
<div style="position:absolute; top:525px; left:330px">
<form action="prev" method="post">
	<input style= "line-height:35px; color:blue; border-radius:5px; width:43px; height:43px; font-size:30pt" type="submit" value="<">
</form>
<div style="margin: -43px 0px 0px 230px">
<form action="next" method="post">
	<input style= "line-height:35px; color:blue; border-radius:5px; width:43px; height:43px; font-size:30pt" type="submit" value=">">
</form>
</div>
</div>

<form action="selectPage">
<div style="position:absolute; top:531px; left:380px">
	<c:set var="start" value="${curPage-2}" />
	<c:set var="last" value="${curPage+2}" />
	<c:if test="${curPage == 1 || curPage == 2 || curPage == 3}">
		<c:set var="start" value="1" />
		<c:set var="last" value="5" />
	</c:if>
	<c:if test='${curPage+2 >= lastPage }'>
		<c:set var="last" value="${lastPage}" />
		<c:set var="start" value="${lastPage-4}" />
	</c:if>
	<c:if test='${start <= 0}'>
		<c:set var="start" value="1" />
	</c:if>
	<c:if test='${last > lastPage}'>
		<c:set var="last" value="${lastPage }" />
	</c:if>
	<c:forEach var="i" begin="${start}" end="${last}">
		<c:if test="${i == curPage}">
			<input style="color:red; width:30px; height:30px" type="submit" name="pageNum" value="${i}">
		</c:if>
		<c:if test="${i != curPage }">
			<input style="width:30px; height:30px" type="submit" name="pageNum" value="${i}" />
		</c:if>
	</c:forEach> 
	</div>
</form>
</c:if>
</div>
</body>
</html>