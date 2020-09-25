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
<div class="container">
	<div>
		<div>작성자 : ${board.membername }</div>
		<div>작성일 : ${board.createDate}</div>
		<div>수정일 : ${board.updateDate}</div>
	</div>
	<div>
		<div>글번호 : ${board.boardid}</div>
		<div>제목 : ${board.title}</div>
	</div>
	<div>내용 : ${board.content}</div>
	<c:choose>
    	<c:when test="${board.membername eq myname}">
    		<a href="/updateForm?boardid=${board.boardid}&membername=${board.membername}"><input type="button" value="수정"></a>
			<a href="/deleteProc?boardid=${board.boardid}&membername=${board.membername}"><input type="button" value="삭제"></a>
			<input type="button"onClick="javascript:history.go(-1);" value="돌아가기">
    	</c:when>
    	<c:otherwise>
    		<input type="button"onClick="javascript:history.go(-1);" value="돌아가기">
    	</c:otherwise>
    </c:choose>
</div>
</body>
</html>