<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="/src/js/bootstrap.js"></script>
	<link rel="stylesheet" href="/src/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="/src/css/voucher/voucher.css">
	<script src="/src/js/jquery-3.3.1.js"></script>
    <meta charset="UTF-8" />
	<title>Voucher</title>
  </head>
  <script>
	  $(function(){
		  $(".purchase").click(function(){
			  if(${not empty sessionScope.user}){
					if(confirm("구매 하시겠습니까?")){
						if(${sessionScope.user.expiredDate eq null}){
							location.href= "/buyVoucherFrm";
						}else{
							location.href="/mypageFrm"
						}
					}
				}else{
					location.href= "/loginFrm";
				}
			});
	  });
	  $(function(){
		$(".player").click(function(){
			window.open("/player","","width=366px , height=650px , resizable=false");
		});
	  });
  </script>
  <body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <section class="container">
			<div class="jumbotron p-0 m-2">
				<div class="view overlay rounded-top">
					<img src="/src/imgs/TJImg/voucherImg2.jpg" class="img-fluid" alt="Sample image">
					<a href="#">
					  <div class="mask rgba-white-slight"></div>
					</a>
				  </div>
				  <!-- Card content -->
				<div class="card-body text-center mb-3">

					<!-- Title -->
					<h3 class="card-title h3 my-4"><strong>이용권 구매</strong></h3>
					<!-- Text -->
					<p class="lead">이용권 구매를 하셔서 다양한 혜택을 같이 이용 가능합니다</p>
					<!-- Button -->
					<button class="btn btn-lg btn-success purchase">구매 하기</button>
				</div>
			  
			</div>
			<div class="container">
			  <div class="row marketing">
				<div class="col-lg-4">
					<div class="card m-2" style="width: 20rem;">
						<img src="/src/imgs/TJImg/licensedMusic.jpg" class="card-img-top" alt="...">
						<div class="card-body">
						  <h5 class="card-title">라이센스 최신 음악</h5>
						  <p class="card-text">라이센스 음반을 무제한 감상 무료 음반을 넘어선 최신 음악을 감상할 수 있는 추가 기능이 부여 됩니다.</p>
						</div>
					  </div>
				  <div class="card m-2" style="width: 20rem;">
						<img src="/src/imgs/TJImg/event.jpg" class="card-img-top" alt="...">
						<div class="card-body">
						  <h5 class="card-title">개인 아티스트 지원</h5>
						  <p class="card-text">웹사이트에서 수익의 몇프로를 개인 아티스트의 지원금으로 투자되며 개인 아티스트들이 새로운 음원을 만들기 위한 도움에 사용됩니다.</p>
						</div>
					  </div>
				</div>
				<div class="col-lg-4">
				<div class="card m-2" style="width: 20rem;">
						<img src="/src/imgs/TJImg/download.png" class="card-img-top" alt="...">
						<div class="card-body">
						  <h5 class="card-title">음악 다운로드</h5>
						  <p class="card-text">본인이 원하는 개인 아티스트의 무료 음원을 무제한 다운로드. 소장하고 싶은 음원을 오프라인으로 어디서나 감상 가능!</p>
						</div>
					  </div>
				</div>
				<div class="col-lg-4">
					<div class="card m-2" style="width: 20rem;">
						<img src="/src/imgs/TJImg/speed.jpg" class="card-img-top" alt="...">
						<div class="card-body">
						  <h5 class="card-title">스트리밍 속도</h5>
						  <p class="card-text">이용권 구매시 무제한 고속 스트리밍 이용 가능. 음원을 어디서나 제일 빠른 속도로 감상!</p>
						</div>
					  </div>
				</div>
			  </div>
			</div>
    </section>
  </body>
</html>
