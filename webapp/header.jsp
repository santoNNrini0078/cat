<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<div id="header">		
<!-- Logo -->	
	<h1><a href="index.jsp" id="logo">냐옹아 대학가자 <em>고양이와 건강하게 20년 살기</em></a></h1>
	
	<div id="login">		
		<a href="login.jsp">로그인</a>
		<a href="join.jsp">회원가입</a>
		<form name="logout" action="create" method="post">
			<input type="hidden">
		</form>
	</div>
	
	<!-- Nav -->
	<nav id="nav">
	
		<ul>		
		<%
			String name="currentPage";	
			int i = 15;
			int j = 1;
		%>
			<li><a href="hospital.jsp">고양이 병원 찾기</a></li>
			<li><a href="pharm.jsp">동물 약국</a></li>
			<li><a href="bye.jsp">무지개 다리 건널 때</a></li>
			<li><a href="tip.jsp">팁</a></li>				
			<li><a href="show?currentPage=<%=j %>&recordsPerPage=<%=i %>">후기</a></li>	
			<li><a href="login.jsp" id="blank_login">로그인</a></li>
		</ul>
	</nav>
</div>
<script>
	//화면을 지우고 표시하고 싶다요
	var id="${loginUser}";
	var grade="${grade}";
	console.log(id);
	console.log(grade);
	var div = document.getElementById("login");
	console.log(div);
	
	if(id.length!=0){		
		var tag1 = document.getElementsByTagName("a")[1];
		var tag2 = document.getElementsByTagName("a")[2];
		console.log(tag1);
		console.log(tag2);
		
		tag1.removeChild(tag1.childNodes[0]);
		tag2.removeChild(tag2.childNodes[0]);
	
		var text = document.createTextNode(id+"님이 로그인 하였습니다. ");
		div.appendChild(text);
				
		div.innerHTML+='<span id="logout"><a href="#">로그아웃</a></span>';	
		
		document.getElementById("logout").onclick=function(){
			document.logout.submit();
		}
		if(grade==0){
			div.innerHTML+='<span id="manage"><a href="admin.jsp">관리</a></span>';
			
			
		}
	}
	
		
</script>

