<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>CSV파일 읽기</title>		
	</head>
	<body>		
		<input type="file" name="input_file" id="input_file" accept=".*">
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

		<script>
			var formFile = document.getElementById("input_file");
			formFile.addEventListener("change", test);			
			
			function test(){
				if(!formFile.files){
					console.log("파일이 없어요!!");
				}else if(!formFile.files[0]){
					console.log("파일을 선택해야 합니다.");
				}else{
					var file=formFile.files[0];
					var fr=new FileReader();
					fr.onload=receive;
					fr.readAsText(file, "utf8");
					function receive(){
												
						var spl=fr.result.split("\n");	
										
						document.write("<table><tr> <td>name</td> <td>tel</td> <td>add</td> <td>add2</td> <td>x</td> <td>y</td> <td>stat</td> </tr>")
						for(i=1; i<spl.length-1; i++){													
												
							var spl2=spl[i].split("@");
							
							var bye = new Object();
						
							bye.name=spl2[21]; //사업장명
							bye.tel=spl2[15]; //소재지전화
							bye.add=spl2[18]; //소재지전체주소
							bye.add2=spl2[19]; //도로명전체주소
							bye.x=spl2[26];	//좌표정보(x)
							bye.y=spl2[27]; //좌표정보(y)
							bye.stat=spl2[10]; //상세영업상태명	
							
							var obj = {
									name : bye.name,
									tel : bye.tel,
									add : bye.add,
									add2 : bye.add2,
									x : bye.x,
									y : bye.y,
									stat : bye.stat
							}																												
							
							let json = JSON.stringify(obj);
							document.write(json+"<br>");
							console.log(json);
							
							//url 주소만 바꿔서 병원, 약국, 장묘 업체 추가하기
							$.ajax({
								type: "post",
								url : "Test",
								headers : {'Content-Type':'application/json'},
								data : json,
								success : function(data){	
									console.log("성공");
								},
								error :function(e){									
								}
							});
																																			
							document.write("<tr><td><input type='text' id='name' value="+spl2[21]+"></td>");
							document.write("<td><input type='text' id='tel' value="+spl2[15]+"></td>");
							document.write("<td><input type='text' id='add' value="+spl2[18]+"></td>");
							document.write("<td><input type='text' id='add2' value="+spl2[19]+"></td>");
							document.write("<td><input type='text' id='x' value="+spl2[26]+"></td>");
							document.write("<td><input type='text' id='y' value="+spl2[27]+"></td>");
							document.write("<td><input type='text' id='stat' value="+spl2[10]+"></td></tr>");								
							}//for 문으로 긁어온 파일 // 객체반환						
						document.write("</table>");						
					} // 파일 읽어오기
				}			
			}					
			
		</script>				
	</body>
</html> 