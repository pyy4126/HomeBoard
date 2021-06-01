<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body bgcolor="E3F4EA">
<div style="margin : 40px 0px 0px 70px">
<h2>회원가입</h2>

<form action="gaip" method="post">
	${msg1}<br>
	이름 : <input type="text" name="name" size="10" placeholder="공백x 2자 이상" onfocus="this.placeholder=''" onblur="this.placeholder='공백x 2자 이상'"><br><br />
	
	ID : <input type="text" name="id" size="10" placeholder="공백x 4자 이상" onfocus="this.placeholder=''" onblur="this.placeholder='공백x 4자 이상'"><br><br />
	
	PW : <input type="password" name="pw" size="10" placeholder="공백x 4자 이상" onfocus="this.placeholder=''" onblur="this.placeholder='공백x 4자 이상'"><br><br />
	
	PW(확인) : <input type="password" name="chk" size="10" placeholder="PW 확인" onfocus="this.placeholder=''" onblur="this.placeholder='PW 확인'"><br><br />
	<input type="submit" value="회원가입">&nbsp;&nbsp;
	
	<a href="loginForm">로그인 페이지로 돌아가기</a><br />

</form>
</div>

</body>
</html>