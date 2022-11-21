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
						
						i=13001;
						n=i;
						k=n+1000;
						
						for(n; i<k; i++){													
												
							var spl2=spl[i].split("@");
							
							var Pharm = new Object();
						
							Pharm.name=spl2[21]; //사업장명
							Pharm.tel=spl2[15]; //소재지전화
							Pharm.add=spl2[18]; //소재지전체주소
							Pharm.add2=spl2[19]; //도로명전체주소
							Pharm.x=spl2[26];	//좌표정보(x)
							Pharm.y=spl2[27]; //좌표정보(y)
							Pharm.stat=spl2[10]; //상세영업상태명	
							
							var obj = {
									name : Pharm.name,
									tel : Pharm.tel,
									add : Pharm.add,
									add2 : Pharm.add2,
									x : Pharm.x,
									y : Pharm.y,
									stat : Pharm.stat
							}																												
							
							let json = JSON.stringify(obj);
//							document.write(json+"<br>");
//							console.log(json);
							
							$.ajax({
								type: "post",
								url : "Pharm",
								headers : {'Content-Type':'application/json'},
								data : json,
								success : function(data){	
									console.log("성공");
								},
								error :function(e){									
								}
							});
							
							if(i==spl.length-2){
								break;
							}
																																			
//							document.write("<tr><td><input type='text' id='name' value="+spl2[21]+"></td>");
//							document.write("<td><input type='text' id='tel' value="+spl2[15]+"></td>");
//							document.write("<td><input type='text' id='add' value="+spl2[18]+"></td>");
//							document.write("<td><input type='text' id='add2' value="+spl2[19]+"></td>");
//							document.write("<td><input type='text' id='x' value="+spl2[26]+"></td>");
//							document.write("<td><input type='text' id='y' value="+spl2[27]+"></td>");
//							document.write("<td><input type='text' id='stat' value="+spl2[10]+"></td></tr>");								
							}//for 문으로 긁어온 파일 // 객체반환						
						document.write("</table>");						
					} // 파일 읽어오기
				}			
			}					
			
		</script>				
	</body>
</html> 