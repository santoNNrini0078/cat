<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>아이디 중복확인</title>
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body>
		<h1>아이디 중복확인</h1>
		<form method="get" action="idCheck" name="frm">
			아이디 <input type="text" name="user_id" value="${user_id }">
			<input type="submit" value="중복체크">
			<br>
			
			<c:if test="${result==1 }">
				<script>
					opener.document.ftm.user_id.value="";
				</script>
					${user_id}는 이미 사용중인 아이디 입니다.
			</c:if>
			<c:if test="${result==-1 }">
				${user_id }는 사용 가능한 아이디 입니다.
				<input type="button" value="사용하기" onclick="idok()">
			</c:if>			
		</form>
		<script>
			function idok(){
				opener.document.frm.user_id.value = document.frm.user_id.value;
				opener.document.frm.reid.value = document.frm.user_id.value;
				self.close()
			}
		</script>
	</body>
</html>