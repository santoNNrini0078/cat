<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>동물 병원 후기</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
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

								<article>
									<header>
									<div id="title">
										<h1> 동물병원 후기</h1>
										<h5> 상호를 특정할 수 없도록 이니셜만 적어주세요</h5>
									</div>
									</header>
								
			<%
			String name="currentPage";	
			int i = 15;
			int j = 1;
			%>
			
			
				<form method="post" action="modify_R">
					<div id="content">
						<div id="con_title"><input type="hidden" name="userid" value="${loginUser }">
							<input type="text" name="con_title" maxlength="50" placeholder="${review.getTitle()}"></div>					
							<input type="hidden" name="num" value="${review.getNum() }">
							<textarea rows="" cols="" name="con" maxlength="1000">${review.getContent()}</textarea><br>	
							<div class="bbb">			
								<input type="submit" value="작성하기">
								<a href="show?currentPage=<%=j %>&recordsPerPage=<%=i %>"><input type="button" value="작성취소"></a>
							</div>
									
					</div>
				</form>
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

	</body>
</html>