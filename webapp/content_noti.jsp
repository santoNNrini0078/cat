<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		
		<title>${review.getTitle() }</title>
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
			
							<div id="title">
								<h1> 동물병원 후기</h1>
								<h5> 상호를 특정할 수 없도록 이니셜만 적어주세요</h5>
							</div>
							<form method="post" action="modify_noti">
								<div id="content">
									<div id="con_title">제목 : ${noti.getTitle() },<input type="hidden" name="num" value="${noti.getNum() }"> * 작성자 : ${review.getId() }</div>	
													
									<textarea rows="" cols="" name="con" maxlength="1000">${noti.getContent() }</textarea><br>	
									<div class="bbb"></div>
										
								</div>					
							</form>
							<form method="get" action="show?currentPage=1&recordsPerPage=15">
								<input type="submit" value="뒤로가기">
							</form>
						</div>
					</div>
				</section>

			<!-- Footer -->
				<footer id="footer"> <jsp:include page="footer.jsp"></jsp:include> </footer>

		</div>

		<!-- Scripts -->
			<script>
				var id="${loginUser}";
				var con_id="${noti.getId()}"
				console.log(id);
				console.log(con_id);
				
				if(id==con_id){
					var div = document.getElementsByClassName("bbb")[0];
					console.log(div);
											
					var modi = '<input type="submit" id="modify_noti" value="수정하기" >';
					
					var num = document.getElementsByName("num")[0].value;
					console.log(num);
					var del = '<a href="del?num='+num+'"><input type="button" id="del" value="삭제"></a>';
					div.innerHTML = modi + ' ' + del;
						
					document.getElementById("modify_noti").value.onclick=function(){
						document.modify_noti.submit();
					}
					
				}
			</script><!--  del?num=?는 아무나 지울 수 있네... -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
	</body>
</html>