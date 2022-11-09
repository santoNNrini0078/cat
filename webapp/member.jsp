<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>사용자확인페이지</title>
	</head>
	<body>
		사용자 확인 페이지입니다.
		
		<table style="border:1px solid black">
			<tr>
				<th>가입순서</th>
				<th>아이디</th>
				<th>이메일</th>
				<th>거주지 시</th>
				<th>거주지 군</th>
				<th>고양이 종</th>   
				<th>가입시간</th>
				<th>등급</th> 
			</tr>
			<c:forEach items="${data}" var="data">
				<tr>
					<form method="post" action="grade">
						
						<td><input type="hidden" name="num" value="${data.getNum() }">${data.getNum() }</td>					
						<td>${data.getUser_id() }</td>
						<td>${data.getEmail() }</td>
						<td>${data.getAddress() }</td>
						<td>${data.getRace() }</td>
						<td>${data.getAge() }</td>
						<td>${data.getTimestamp() }</td>
						<td><input type="text" name="grade" value="${data.getGrade() }"></td>
						<td><input type="submit" value="수정"></td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>