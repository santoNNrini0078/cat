<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE HTML>
<!--
	Arcana by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>냐옹아 대학가자</title>
		<meta charset="utf-8" />
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
			
			<!-- Banner -->
				<section id="banner">
					<header>
						<form method="post" action="ReadHospital">
							<input type="hidden" name="currentPage" value="1">
							<input type="hidden" name="recordsPerPage" value="15">
							<h2>검색 : <input type="search" id="search" name="search"></h2>
							<!-- 냥지수가 높은 병원 검색 -->
							<input type="submit" class="submit" value="검색">
						</form>
					</header>
				</section>

			<!-- Highlights -->
				<section class="wrapper style1">
					<div class="container">
						<div class="row gtr-200">
							<section class="col-4 col-12-narrower">
								<div class="box highlight">
									<i class="icon solid major fas fa-cat"></i>									
									<a href="hospital.jsp"><h3>고양이 병원</h3></a>
									<p>많은 동물병원 중 고양이를 치료할 수 있는 병원을 찾기위해 만든 목록입니다. 집단지성이 필요합니다.</p>
								</div>
							</section>
							<section class="col-4 col-12-narrower">
								<div class="box highlight">
									<i class="icon solid major fas fa-pills"></i>									
									<a href="pharm.jsp"><h3>동물약품취급점</h3></a>
									<p>동물약품을 취급하는 약국의 목록입니다.</p>
								</div>
							</section>
							<section class="col-4 col-12-narrower">
								<div class="box highlight">
									<i class="icon solid major fa-paper-plane"></i>
									<a href="bye.jsp"><h3>무지개 다리</h3></a>
									<p>반려동물을 떠나보낼 때 필요한 장묘업체 목록입니다.</p>
								</div>
							</section>
						</div>
					</div>
				</section>
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