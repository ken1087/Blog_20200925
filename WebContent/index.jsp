<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty list and state != 1}">
	<c:redirect url="boardList" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
<script>
	if(<%=session.getAttribute("membername") %> == null){
		window.location.href = "/login";
	}
</script>

<a href="/boardWrite">글쓰기</a>
<a href="/logout">로그아웃</a>
<table class="table table-dark table-hover" border="1">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>수정일</td>
	</tr>
	<tbody>
		<c:forEach var="item" items="${list}">
			<tr class="atable">
				<td>${item.boardid}</td>
				<td><a href="/view?boardid=${item.boardid}&session=<%=session.getAttribute("membername")%>">${item.title}</a></td>
				<td>${item.membername}</td>
				<td>${item.createDate}</td>
				<td>${item.updateDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>