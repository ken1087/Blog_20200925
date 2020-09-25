<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/boardWriteProc">
	<input type="text" name="membername" value="<%=session.getAttribute("membername") %>">
	<input type="text" name="title">
	<input type="text" name="content">
	<input type="submit" value="등록">
	<input type="button"onClick="javascript:history.go(-1);" value="돌아가기">	
</form>
</body>
</html>