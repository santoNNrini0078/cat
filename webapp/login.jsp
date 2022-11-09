<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		
		<title>로그인</title>
		<link rel="stylesheet" href="assets/css/main.css" />	
	</head>
	<body>
		<body class="is-preload">
		<div id="page-wrapper">

			<!-- Header -->
				<header><jsp:include page="header.jsp"></jsp:include></header>

			<!-- Main -->
				<section class="wrapper style1">
					<div class="container">
						<div id="content">

					<!-- Content -->
							<article>
								<header>
									<div class="login">
										<form method="post" action="login">
											<table>
												<tr>
													<td>id</td>
													<td><input type="text" name="user_id" id="inputid"></td>
												</tr>
												<tr>
													<td>password</td>
													<td><input type="password" name="pass" id="inputpw"></td>
												</tr>
												<tr>
													<td></td>
													<td><input type="submit" value="로그인" onclick="return check()"></td>
												</tr>
												<tr>
													<td></td>
													<td><a href="join.jsp"><input type="button" value="회원가입"></a></td>
												</tr>
											</table>										
										</form>
									</div>
								</header>
							</article>
						</div>
					</div>
				</section>

			<!-- Footer -->
				<footer id="footer"> <jsp:include page="footer.jsp"></jsp:include> </footer>

		</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>	
			
			<script>
				function check(){
					if(document.getElementById("inputid").value.length==0){
						alert("아이디를 입력해주세요");
						document.getElementById("inputid").focus();
						return false;
					}else if(document.getElementById("inputpw").value.length==0){
						alert("비밀번호를 입력하셔야합니다.");
						document.getElementById("inputpw").focus();
						return false;
					}
					return true;
				}
			</script>
			
	</body>
</html>