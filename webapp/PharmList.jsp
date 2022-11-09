<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		<title>동물 약품 취급 약국 찾기</title>
		<link rel="stylesheet" href="assets/css/main.css" />
		<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=86p3phynao"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.8.0/proj4.js" integrity="sha512-ha3Is9IgbEyIInSb+4S6IlEwpimz00N5J/dVLQFKhePkZ/HywIbxLeEu5w+hRjVBpbujTogNyT311tluwemy9w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		
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
						<div id="find_pharm">
			
						<div id="map" style="width:80%;height:300px;"></div>
							
			
							<table>
								<tr>
									<th>상호명</th>
									<th>전화번호</th>
									<th>주소</th>  
								</tr>
							<c:forEach items="${data}" var="data">
								<tr>
									<td><input type="button" name="bye_name" value="${data.getName() }"></td>
									<input type="hidden" name="x" value="${data.getX() }">
									<input type="hidden" name="y" value="${data.getY() }">
									<td>${data.getTel() }</td>
									<td>${data.getAdd() }</td>
								</tr>
							</c:forEach>
							</table>
							<ul>
							<c:if test="${currentPage > 5 }">
								<li><a href="ReadPharm?currentPage=${blockStartNum - 1 }&recordsPerPage=${recordsPerPage }&search=${search}">◀</a></li>
							</c:if>
							
								<c:forEach var="i" begin="${blockStartNum }" end="${blockLastNum }" >																	
									<c:choose>
										<c:when test="${ i > nOfPage }">
						                    <li>${ i }</li>
						                </c:when>
						                <c:when test="${ i eq currentPage }">
						                    <li class="selected">${ i }.</li>
						                </c:when>
						                
						                <c:otherwise>
						                    <li><a href="ReadPharm?currentPage=${i}&recordsPerPage=${recordsPerPage }&search=${search}">${ i }</a></li>
						                </c:otherwise>
									</c:choose>													
								</c:forEach>
								<c:if test="${ nOfPage > blockLastNum }">
						            <li><a href="ReadPharm?currentPage=${blockLastNum + 1 }&recordsPerPage=${recordsPerPage }&search=${search}">▶</a></li>
						        </c:if>
						        								
							</ul>
						</div>
					</div>
				</div>
			</section>

		<!-- Footer -->
			<footer id="footer"> <jsp:include page="footer.jsp"></jsp:include> </footer>

		</div>

		<!-- Scripts -->
			
			
			<script>	
			
			var areaArr = new Array();
			
			for(var i =0; i<5; i++){
				var name = document.getElementsByName("bye_name")[i].value;
				console.log(name);
				var bye_x = document.getElementsByName("x")[i].value;							
				var bye_y = document.getElementsByName("y")[i].value; 
				
				console.log(bye_x);
				console.log(bye_y);				
				
				proj4.defs([
				    [
				        'EPSG:2097',
				        '+title=EPSG 2097 (long/lat) +proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs +towgs84=-115.80,474.99,674.11,1.16,-2.31,-1.63,6.43']
				   , [
				        'EPSG:4326',
				        '+title=WGS 84 (long/lat) +proj=longlat +ellps=WGS84 +datum=WGS84 +units=degrees']
				]);								
				
				var x = parseFloat(bye_x);
				var y = parseFloat(bye_y);
					
				if(isNaN(x)){
					x=0;
				}
				if(isNaN(y)){
					y=0
				}
				console.log(x);
				console.log(y);	
				
				if(x!=0||y!=0){					
					var wgs84 = proj4('EPSG:2097', 'EPSG:4326', [x, y]);				
					console.log(wgs84);
					
					var x = wgs84[0];
					var y = wgs84[1];
					
					areaArr.push({name, x, y});
				}
				
											
				/*보정안된 오래된 지리원 표준   중부원점(Bessel): 서울 등 중부지역  EPSG:2097 */									
			}
			console.log(areaArr);
			
			let markers = new Array(); //마커정보
			let infoWindows = new Array(); //정보창
			
			var map = new naver.maps.Map('map',{
				center: new naver.maps.LatLng(y, x),
			    zoom: 15
			})
			
			for(var i=0; i<areaArr.length; i++){
				var marker = new naver.maps.Marker({
					map: map,
					title : areaArr[i].name,
					position : new naver.maps.LatLng(areaArr[i].y, areaArr[i].x),
					
				});
				
				var infoWindow = new naver.maps.InfoWindow({
					content : '<div style="width:200px; text-align:center; padding:10px;"<b>' +areaArr[i].name+ '</b></div>'
				});
				
				markers.push(marker); //생성한 마커를 배열에 담기
				
				infoWindows.push(infoWindow);
				
			}
					
			function getClickHandler(seq){
				return function(e){ //마커 클릭
					var marker = markers[seq], //클릭한 마커의 시퀀스
					infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스
					
					if(infoWindow.getMap()){
						infoWindow.close();
					}else{
						infoWindow.open(map, marker); //표현
					}
				}
			}
			for(var i=0, ii=markers.length; i<ii; i++){
				console.log(markers[i], getClickHandler(i));
				naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); //클릭한 마커 핸들러
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