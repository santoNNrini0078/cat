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
		<title>게시판</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />		
		<script src="./include.js"></script>		
		
		</script>
		
		<style>			
			li{
				display:inline-block;
			}
		</style>
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
									<h2>동물병원 후기</h2>
									<p>상호를 특정할 수 없도록 이니셜만 적어주세요</p>
								</header>
	
								<div id=table>	
 									<!-- 공지사항 -->
									<c:forEach items="${data2}" var="data2">		
										<form method="get" action="show_content">										
										<div class="line">						
											<label>
											<input type="hidden" name="num" value="${data2.getNum()}">
											<div class="notice_con" style="display:none"><input type="checkbox" class="notice" value="${data2.getNum()}"></div>
											<div class="number">${data2.getNum() }	</div> </label>	
											<div class="contents"><b><input type="submit" name="title" value="${data2.getTitle()}"></b></div>							
											<div class="who">${data2.getId() }</div>
											<div class="view">${data2.getView() }</div>
											<input type="hidden" name="view" value="${data2.getView()}">
											<div class="time">${data2.getTime() }</div>
										</div>
										</form>
									</c:forEach>
									<!-- 게시판 -->				 
									<c:forEach items="${data}" var="data">		
										<form method="post" action="show_content">										
										<div class="line">
											<label>					
											<input type="hidden" name="num" value="${data.getNum()}">
											<div class="del_con" style="display:none"><input type="checkbox" class="del" value="${data.getNum()}"></div>
											<div class="number">${data.getNum() }	</div></label>	
											<div class="contents"><input type="submit" name="title" value="${data.getTitle()}"></div>							
											<div class="who">${data.getId() }</div>
											<div class="view">${data.getView() }</div>
											<input type="hidden" name="view" value="${data.getView()}">
											<div class="time">${data.getTimestamp() }</div>
										</div>
										</form>
									</c:forEach>														
								</div>
				
								<div id="page">
									<ul>										
										<form method="get" action="create">
										<input type="submit" name="create" value="글쓰기" onclick="return createR()"></form>
										<div id="del_admin"></div>
										<div id="notice_admin"></div>
										<c:if test="${currentPage > 5 }">
											<li><a href="show?currentPage=${blockStartNum - 1 }&recordsPerPage=${recordsPerPage }">◀</a></li>
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
									                    <li><a href="show?currentPage=${i}&recordsPerPage=${recordsPerPage }">${ i }</a></li>
									                </c:otherwise>
												</c:choose>													
											</c:forEach>
											<c:if test="${ nOfPage > blockLastNum }">
									            <li><a href="show?currentPage=${blockLastNum + 1 }&recordsPerPage=${recordsPerPage }">▶</a></li>
									        </c:if>
									</ul>
									
									<a href="show?currentPage=${i }&recordsPerPage=${recordsPerPage }">${i }</a>
									
								</div>
							</article>
						</div>
					</div>
				</section>

			<!-- Footer -->
				<footer id="footer"> <jsp:include page="footer.jsp"></jsp:include> </footer>

		</div>

		<!-- Scripts -->
			<script>
				var id="${loginUser}";
				console.log(id);
				
				function createR(){
					if(id.length==0){
						alert("로그인이 필요합니다.");
						return false;
					}
					return true;
				}				

				var grade="${grade}";
				console.log(grade);
				var div = document.getElementsByClassName("del_con");
				var div2 = document.getElementsByClassName("notice_con");
				console.log(div[0]);					
				
				if(id.length!=0){
					if(grade==0){
						
						for(let i=0; i<div.length; i++){
							document.getElementsByClassName("del_con")[i].setAttribute("style","display:inline-block;");							
						}
						
						for(let i=0; i<div2.length; i++){
							document.getElementsByClassName("notice_con")[i].setAttribute("style","display:inline-block;");
						}
						
						var del_admin = document.getElementById("del_admin");
						var write_admin = document.getElementById("notice_admin");
						console.log(del_admin);
						del_admin.innerHTML+='<form method="post" action="admin_del"><div id="check"></div><input type="submit" value="삭제" onclick="return del_check()"></form>';
						write_admin.innerHTML+='<form method="post" action="admin_write"><div id="check2"></div><input type="submit" value="공지작성"></form>';	
					}
				}
				
				var check = document.getElementById("check");
				var check2 = document.getElementById("check2");
				console.log(check);	
				
				function del_check(){					
					if(id.length!=0){
						if(grade==0){
							var val=[];
							for(let i=0; i<div.length; i++){
								
								//let check1=document.querySelectorAll(".del");
								//console.log(check1);
								
								var checkbox = document.getElementsByClassName("del")[i];
								console.log(checkbox);
								var checked = checkbox.checked;	
								console.log(checked);	
									
								if(checked){
									console.log("aaaaaa");
									val.push(document.getElementsByClassName("del")[i].value);
									console.log(val);								
								}
								
								
								var checkbox2 = document.getElementsByClassName("notice")[i];
								console.log(checkbox2);
								var checked2 = checkbox2.checked;	
								console.log(checked2);	
									
								if(checked2){
									console.log("aaaaaa");
									val.push(document.getElementsByClassName("notice")[i].value);
									console.log(val);								
								}
							}
						}
					}
					check.innerHTML+='<input type="hidden" name="del_num" value="'+val+'">';
					check2.innerHTML+='<input type="hidden" name="notice_num" value="'+val+'">';
					return true;
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