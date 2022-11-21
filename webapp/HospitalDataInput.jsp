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
						
							i=9001;
							n=i;
							k=n+1000;
						
							for(n; i<k; i++){													
								
								var spl2=spl[i].split("@");
								
								var Hospital = new Object();
							
								Hospital.name=spl2[21]; //사업장명
								Hospital.tel=spl2[15]; //소재지전화
								Hospital.add=spl2[18]; //소재지전체주소
								Hospital.add2=spl2[19]; //도로명전체주소
								Hospital.x=spl2[26];	//좌표정보(x)
								Hospital.y=spl2[27]; //좌표정보(y)
								Hospital.stat=spl2[10]; //상세영업상태명	
								
								var obj = {
										name : Hospital.name,
										tel : Hospital.tel,
										add : Hospital.add,
										add2 : Hospital.add2,
										x : Hospital.x,
										y : Hospital.y,
										stat : Hospital.stat
								}																												
								
								let json = JSON.stringify(obj);
//								document.write(json+"<br>");
//								console.log(json);
								
								$.ajax({
									type: "post",
									url : "Hospital",
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
								
							}//for 문으로 긁어온 파일 // 객체반환						
							document.write("</table>");
							document.write("다음");						
						}
											
					} // 파일 읽어오기
							
			}					
			
		</script>				
	</body>
</html> 