/**
 * 
 */

	var areaArr = new Array();
	var bye_x = document.getElementsByName("bye_name")[i].value;			
	for(var i =0; i<5; i++){
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

