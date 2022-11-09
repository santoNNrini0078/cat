<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>공지사항 작성</title>
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
										<h1> 공지사항 작성</h1>
									</div>
									</header>
								
			<%
			String name="currentPage";	
			int i = 15;
			int j = 1;
			%>
			
			
				<form method="post" action="input_notice">
					<div id="content">
						<div id="con_title"><input type="hidden" name="userid" value="${loginUser }"><input type="text" name="con_title" maxlength="50" placeholder="제목을 입력하세요 / 50자 이하"></div>					
							<textarea rows="" cols="" name="con" maxlength="1000" placeholder="내용을 입력하세요/ 1000자 이하"></textarea><br>	
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