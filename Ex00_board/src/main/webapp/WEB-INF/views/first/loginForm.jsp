<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body bgcolor="E3F4EA">
<div style="margin : 40px 0px 0px 70px">	
<h2>방명록 대장</h2>
${msg}<br><br>
<form action="login" method="post">
	아이디 <input type="text" name="id"><br>
	비밀번호 <input type="password" name="pw"><br><br />
	<input type="submit" value="로그인"> &nbsp;&nbsp;
	<a href="gaipForm">회원가입</a> <br>
</form>
</div>
</body>
</html>