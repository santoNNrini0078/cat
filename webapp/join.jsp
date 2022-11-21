<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		
		<title>회원 가입</title>
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="is-preload">
		<div id="page-wrapper">

			<!-- Header -->
				<header><jsp:include page="header.jsp"></jsp:include></header>

			<!-- Main -->
				<section class="wrapper style1">
					<div class="container">
						<div id="content">

							<!-- Content -->			
							<div id="join">
								<form method="post" action="join" name="frm">
									<table>
										<tr>
											<td>아이디 : </td>
											<td><input type="text" name="user_id" placeholder="6자 이상"><input type="hidden" name="reid"></td>											
											<td><input type="button" value="아이디 중복 확인" onclick="idCheck()"></td>
										</tr>
										<tr>
											<td>비밀번호 :</td>
											<td><input type="password" name="pass"></td>
										</tr>
										<tr>
											<td>비밀번호 확인 :</td>
											<td><input type="password" name="re_pw"></td>
										</tr>
										<tr>
											<td>이메일 :</td>
											<td><input type="email" name="email"></td>
										</tr>
										<tr>
											<td>주소 :</td>
											<td><input type="text" name="address"></td>
										</tr>
										<tr>
											<td>고양이 종 :</td>
											<td><input type="text" name="race"></td>
										</tr>
										<tr>
											<td>고양이 나이 :</td>
											<td><input type="number" name="age" placeholder="숫자만입력"></td>
										</tr>
										<tr>
											<td colspan="3">
												<div id="btn_ctr">
													<input type="submit" value="가입하기" onclick="return joinCheck()">
													<input type="reset" value="초기화">
												</div>
											</td>
										</tr>
									</table>																	
								</form>				
							</div>					
						</div>
					</div>
				</section>
			<!-- Footer -->
				<footer id="footer"> <jsp:include page="footer.jsp"></jsp:include> </footer>
		</div>

		<!-- Scripts -->
		
			<script>
			function idCheck(){
				if(document.frm.user_id.value==""){
					alert("아이디를 입력해 주세요");
					document.frm.user_id.focus();
					return;
				}
				var url="idCheck?user_id="+document.frm.user_id.value;
				var popupX=(window.screen.width/2)-(450/2);
				var popupY=(window.screen.height/2)-(200/2);
				
				console.log(popupX);
				window.open(url, "_blank_1", "toolbar=no, menubar=no, resizable=no, scrollbar=yes, width=450, height=200, left="+popupX+", top="+popupY);
			} //입력창에 보안을 위한 내용을 추가하자. $,/,\ 등을 사용하지 못하게, 대소문자, 비밀번호 어렵게
				
				function joinCheck(){
					if(document.frm.user_id.value.length==0){
						alert("아이디를 입력해 주세요");
						frm.user_id.focus();
						return false;
					}
					if(document.frm.user_id.value.length<6){
						alert("아이디는 6자 이상이어야 합니다.");
						frm.user_id.focus();
						return false;
					}
					if(document.frm.pass.value==""){
						alert("비밀번호를 입력하셔야 합니다.");
						frm.pass.focus();
						return false;
					}
					if(document.frm.pass.value!=document.frm.re_pw.value){
						alert("비밀번호가 일치하지 않습니다.");
						frm.re_pw.focus();
						return false;
					}
					if(document.frm.reid.value.length==0){
						alert("아이디 중복체크가 이루어지지 않았습니다.");
						frm.user_id.focus();
						return false;
					}
					return true;
				}
			</script>		
		
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>			
	
	</body>
</html>