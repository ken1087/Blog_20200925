<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/boardUpdateProc">
	<input type="hidden" name="boardid" value="${board.boardid}" style="display:none">
	<input type="text" name="membername" value="<%=session.getAttribute("membername") %>">
	<input type="text" name="title" value="${board.title }">
	<input type="text" name="content" value="${board.content}">
	<input type="submit" value="수정">
</form>
</body>
</html>