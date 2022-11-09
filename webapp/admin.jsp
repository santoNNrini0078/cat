<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>관리자 페이지</title>
	</head>
	<body>
		<div id="wrap">
			<h1>여기는 관리자 페이지입니다.</h1>
			
		</div>
	</body>
	<script>
	
	var id="${loginUser}";
	var grade="${grade}";
	console.log(id);
	console.log(grade);
	var div = document.getElementById("wrap");
	console.log(div);
	
	if(id.length!=0&&grade==0){			
		div.innerHTML+='<form method="post" action="data"><input type="submit" id="data" value="파일 업로드"></form>';
		div.innerHTML+='<form method="post" action="member"><input type="submit" id="member" value="회원관리"></form>';
	}	
		
</script>
</html>