<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<!--
	Arcana by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>무지개 다리 건널 때</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<script src="./include.js"></script>
		<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=86p3phynao&submodules=geocoder"></script>
		
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
										<h2>동물 장묘업체 찾기</h2>
										<p>이름이나 지역명을 입력하세요.</p>
									</header>

									<div id="find_bye">			
										<form method="get" action="ReadBye">
											<input type="hidden" name="currentPage" value="1">
											<input type="hidden" name="recordsPerPage" value="15">
											<input type="search" class="search" name="search">
											<input type="submit" class="submit" value="검색">
										</form>
			
			
										<div id="map" style="width:80%;height:300px;"></div>
							
											<script>
											
											var vurBtn='';
											//현재 위치 위도, 경도 변수
											var curtLoca="";
											
											
											//서울 시청 위경도
											var sCityHallLoca = new naver.maps.LatLng(37.5666805, 126.9784147);
											//맵 초기화
											var map = new naver.maps.Map('map',{
												center : sCityHallLoca, 
												scaleControl : false, //우측하단 스케일표시
												mapDataControl : false, //좌측하단 네이버표시
												zoom : 17
											});
											
											//getCurrentPosition 성공 콜백 함수
											var onSuccessGeolocation = function(position){
												curtLoca = new naver.maps.LatLng(position.coords.latitude, position.coords.longitude);
												map.setCenter(curtLoca);
												
												new naver.maps.Marker({
													position : curtLoca,
													map : map,
													
												});
											}
											//getCurrentPosition 에러 콜백 함수
											var onErrorGeolocation = function(){
												var agent= navigator.userAgent.toLowerCase(), name = navigator.appName;
												
												if(name==="Microsoft Internet Explorer" || agent.indexOf("trident") > -1 || agent.indexOf("edge/") > -1){
													alert("지원하지 않는 브라우져입니다");
												}else{
													console.log("현재 위치를 가져오는데 에러가 발생하였습니다.");
												}
											}
											if(navigator.geolocation){
												navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);
												
											}else{
												conseol.log("Geolocation Not supported Required");
											}
											
											/*
											var mapOptions = {
											    center: new naver.maps.LatLng(37.3595704, 127.105399),
											    zoom: 15
											};
												
							/*				var map = new naver.maps.Map('map',{
												center:new naver.maps.LatLang(37.3595704, 127.105399),
												zoom: 10
											});
											
											var marker = new naver.maps.Marker({
												position : new naver.maps.LatLng(37.3595704, 129.105399),
												map: map
											});
											
											var map = new naver.maps.Map('map', mapOptions);
											*/
										</script>
									</div>
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