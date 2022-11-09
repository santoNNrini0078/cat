<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		<title>고양이 병원 찾기</title>
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
						<div id="find_hospital">
			
						<div id="map" style="width:80%;height:300px;"></div>
							
							<h5>*냥지수 : 고양이 진료가 가능한 병원에 체크해주세요. 냥지수가 올라갑니다.(로그인 필요)</h5>
							
							<table>
								<tr>
									<th>상호명</th>
									<th>전화번호</th>
									<th>주소</th> 
									<th>냥</th> 
								</tr>
								<script>
									var starr;
									var st;
									var re_star;
									var lstar;
									var element;
									var id;
								</script>
							<c:forEach items="${data}" var="data" varStatus="status">
							<form method="post" action="star">
								<tr>								
									<td><input type="button" name="bye_name" value="${data.getName() }" onclick="zoom()"></td>
									<input type="hidden" class="num" name="num" value="${data.getNum() }">
									<input type="hidden" name="x" value="${data.getX() }">
									<input type="hidden" name="y" value="${data.getY() }">
									<input type="hidden" name="star" value="${data.getStar() }">
									<input type="hidden" id="search" name="search">
									<input type="hidden" class="page" name="page" value="${currentPage }">
									<td>${data.getTel() }</td> 
									<td>${data.getAdd() }</td>
									<td class="starr">${data.getStar() }</td>
									<!-- <td><label><input type="checkbox" name="star" value="1"></label></td>  -->
									<td><input type="button" class="st" name="star" value="냥지수+1"></td>								
									<script>
										//starr=document.getElementsByClassName("starr")[${status.index}].textContent;
										id="${loginUser}";
										st=document.getElementsByClassName("st")[${status.index}];
																				
										st.onclick=function(){
											if(id.length==0){
												alert("로그인이 필요합니다.");
												return;
											}else
											//alert(document.getElementsByClassName("starr")[${status.index}].textContent);
											//starr=document.getElementsByClassName("starr")[${status.index}].textContent;
											num=document.getElementsByClassName("num")[${status.index}].value;
											//page = document.getElementsByClassName("page")[${status.index}].value;
											
											console.log(num);											
											Conn(num);
											lstar=document.getElementsByClassName("starr")[${status.index}];											
										}										
									</script>																		
								</tr></form>
							</c:forEach>							
							</table>
							
							<ul>
							<c:if test="${currentPage > 5 }">
								<li><a href="ReadHospital?currentPage=${blockStartNum - 1 }&recordsPerPage=${recordsPerPage }&search=${search}">◀</a></li>
							</c:if>
							
								<c:forEach var="i" begin="${blockStartNum }" end="${blockLastNum }" >																	
									<c:choose>
										<c:when test="${ i > nOfPage }">
						                    <li>${ i }</li>
						                </c:when>
						                <c:when test="${ i eq currentPage }">
						                    <li class="selected">${ i }_</li>
						                </c:when>
						                
						                <c:otherwise>
						                    <li><a href="ReadHospital?currentPage=${i}&recordsPerPage=${recordsPerPage }&search=${search}">${ i }</a></li>
						                </c:otherwise>
									</c:choose>													
								</c:forEach>
								<c:if test="${ nOfPage > blockLastNum }">
						            <li><a href="ReadHospital?currentPage=${blockLastNum + 1 }&recordsPerPage=${recordsPerPage }&search=${search}">▶</a></li>
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
			function zoom(){
				var x = document.getElementsByName("x").value;							
				var y = document.getElementsByName("y").value;
				console.log(x);
			}
				
			var XHR;
			
			function createXMLHttpRequest(){
				if(window.ActiveXObject){
					XHR = new ActiveXObject("Microsoft.XMLHTTP");
				}else if(window.XMLHttpRequest){
					XHR = new XMLHttpRequest();
					
				}
			}
			function handleStateChange(){
				if(XHR.readyState==4){
					console.log("가냐");
					if(XHR.status==200){	
						console.log(XHR);
						parseResult();
					}else{
						console.log("오류냐222");
					}
				}
			}			
			var num;
			var page;
			
			function createString(starr,num,page){		
				
				var dataString ="num="+num+"&star="+starr+"&currentPage="+page;
				console.log(dataString);
				return dataString;							
			}
			
			function Conn(num){							
				createXMLHttpRequest();
				var url="hosi?num="+num;
				
				XHR.onreadystatechange = handleStateChange;
				XHR.open("GET", url, true);
				XHR.send(null);
				console.log("나오나");
			}
			function parseResult(){						
											
				//var text = document.createTextNode(XHR.responseText);							
				//var text2 = JSON.stringify(text);
				re_star=XHR.responseXML.getElementsByTagName("star")[0].firstChild.data;
				
				reInner(lstar,re_star);				
			}						
			
			function reInner(s,a){
				s.innerHTML=a;
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