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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>게시물 내용</title>
	<script>
	    function popup(){
	        var url = "llist?num=${cnum}";
	        var name = "popup llist";
	        var option = "width = 300, height = 300, top = 160, left = 200, location = no"
	        window.open(url, name, option);
	    }
	</script>
<style>

@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
	.menu{
		background-color: #E3F4EA;
		font-size: 30px;
		border: 0px;
		color: gray;
		font-family: 'Nanum Pen Script', cursive;
	}
	.menu-like {
        color: #6495ED;
    }
</style>


</head>

<body bgcolor="E3F4EA">
<div style="position:absolute; z-index:2; top:358px; left:280px">
<input type="button" class="menu" data-menu="1" value="좋아요" />
</div>

<form action="lbutton">
	<div style="position:absolute; z-index:3; top:353px; left:225px">
	<input type="submit" style="opacity:0; cursor:pointer; background-color:#E3F4EA; font-size:13pt; border:3px solid blue; width:100px; height:48px">
	</div>
</form>

<c:if test="${lchk == 'like'}">
<div style="position:absolute; z-index:1; top:353px; left:225px">
<img src=/image/color_thumbsup.png width="50" height="50"/>
</div>
	<script>	
		var menuLinks = document.querySelectorAll('.menu');
	    menuLinks[0].classList.add('menu-like');
	</script>
</c:if>
<c:if test="${lchk == 'unlike' }">
<div style="position:absolute; z-index:1; top:353px; left:225px">
<img src=/image/gray_thumbsup.png width="50" height="50"/>
</div>
	<script>	
		var menuLinks = document.querySelectorAll('.menu');
	    menuLinks[0].classList.remove('menu-like');
	</script>
</c:if>

	<div style="position:absolute; opacity:0; z-index:2; top:383px; left:355px">
    	<a href = "javascript:popup()" >팝업</a>
	</div>

<div style="position:absolute; z-index:1; top:363px; left:365px">
<h6>${lcnt}명</h6>
</div>

<div style="position:absolute; top:40px; left:70px">

	<h2>${dto.title}</h2>

	<div style="margin: -5px 0px 5px 0px">
		작성자 : <h4 style="display:inline">[${dto.writer}]</h4>
	</div>
	<div style="margin: -25px 0px 15px 362px">
		${dto.dd} 
	</div>
		<%-- pre태그는 입력받은 문자열을 개행문자 포함 그대로 받아오기 위한 태그 --%>
		<div style="height:200px; width:440px; border:2px solid #3CB354; border-radius: 0.5em;">
			<div style="overflow: auto; width:440px; height:200px;"> 
				<pre><c:out value="${dto.content}" /></pre>
				<br><br>
			</div>
		</div>
		
	<a href="list">목록보기</a>
	<c:choose>
	<c:when test="${cid == dto.id}">
		<div style="margin : -15px 0px 0px 410px">
			<a href="rewriteForm?num=${dto.num}">수정</a>
		</div>
	</c:when>
	<c:otherwise>
	<br>
	</c:otherwise>
	</c:choose>
	<br><br>
	
	<c:if test="${fn:length(filelist) != 0}">
	<h5 style="display:inline">첨부</h5>
		<div style="height:80px; width:440px; border:2px solid #ffffff; border-radius: 0.5em;">
			<div style="overflow: auto; width:440px; height:80px;">
				<c:forEach items="${filelist}" var="name">
					<input type="button" value="${name.simple_name}" onclick="window.open('/guest/${dto.dd}/${name.name}', '_blank', 'width=400, height=400, top=160, left=200, location=no, fullscreen=no, toolbar=no, menubar=no');"><br>
				</c:forEach>
			</div>
		</div>
		<br>
	</c:if>
	
	<c:forEach items="${look}" var="dto">
		
			<h5 style="display:inline">${dto.writer}</h5>
			<h6 style="display:inline">${dto.dd}</h6>
			<c:if test="${cid == dto.id || cid == 'admin'}">
			<a style="color:red;" href="deletereview?idx=${dto.idx}">X</a>
			</c:if>
			<pre><h4>${dto.coment}</h4></pre>
			<div style="margin : -10px 0px 0px -400px">
			<hr style="width:440px; margin:0px 0px 0px 400px">
			</div>
	</c:forEach> 

	<form action="reviewWrite">
		<div style="margin : -10px 0px -20px 0px">
		<h5>${cname}</h5>
		</div>
		<input type="hidden" name = "id" value="${cid}">
		<input type="hidden" name = "num" value="${cnum}">
		<input type="hidden" name = "writer" value="${cname}">
		<input type="hidden" name = "dd" value=<%= sf.format(nowTime) %>>
		<textarea type="text" name="coment" rows="4" cols="65" placeholder="댓글을 남겨보세요" onfocus="this.placeholder=''" onblur="this.placeholder='댓글을 남겨보세요'"></textarea><br />
		<div style="margin : -33px 0px 50px 400px; position:relative">
			<input type="submit" value="등록">
		</div>
	</form>
	
	
</div>
</body>
</html>