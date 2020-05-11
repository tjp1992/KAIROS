<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="/js/bootstrap.js"></script>
	<link rel="stylesheet" href="/src/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="/src/css/voucher/voucher.css">
    <meta charset="UTF-8" />
	<title>Voucher</title>
	
  </head>
  <style>
    
  </style>
  <body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <section class="container">
		<c:if test="${empty sessionScope.userId}">
			<div class="jumbotron">
			  <h1>이용권 구매</h1>
			  <p class="lead">이용권 구매를 하시면 혜택이 많습니다</p>
			  <p>
				<a class="btn btn-lg btn-success">Get Started Today</a>
			  </p>
			</div>
			<div class="container">
			  <div class="row marketing">
				<div class="col-lg-4">
				  <h4>라이센스 최신 음악</h4>
				  <p>라이센스 음반을 무제한 감상 무료 음반을 넘어선 최신 음악을 감상할 수 있는 추가 기능이 부여 됩니다.</p>
				  <h4></h4>
				</div>
				<div class="col-lg-4">
				  <h4>음악 다운로드</h4>
				  <p>무료 음원의 </p>
				</div>
				<div class="col-lg-4">
				  <h4>스트리밍 속도</h4>
				  <p>이용권 구매시 무제한 고속 스트리밍 이용 가능</p>
				</div>
			  </div>
			</div>
		</c:if>
		<c:if test="${not empty sessionScope.userId}">
			<!-- forward to my page voucher -->
		</c:if>
    </section>
  </body>
</html>
