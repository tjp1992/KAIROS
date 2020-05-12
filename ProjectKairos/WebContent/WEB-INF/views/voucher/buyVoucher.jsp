<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<link rel="stylesheet" href="/src/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="/src/css/voucher/voucher.css">
	<script src="/src/js/jquery-3.3.1.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<section class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="/src/imgs/logo/main_logo(no).png" alt width = "250" height="130">
			<h2 class="m-4">이용권 구매</h2>
			<p class="lead m-2">
				회원권을 구매 함으로써 고객님은 무한의 음악을 즐기기 위해 첫 걸음을 밟으십니다.
			</p>
			<p class="lead m-2">
				고객님께서 저희 서비스를 이용 함 으로써 개인 아티스트를 지원하게 되며 보다 나은 음악을 제공하게 됩니다.
			</p>
		</div>
		<div class="row">
		<div class="col-md-4 order-md-2 mb-4">
			<h4 class="d-flex justify-content-between align-items-center mb-3">
				<span class="text-muted">결제 바구니</span>
			</h4>
			<ul class="list-group mb-3">
				<li class="list-group-item d-flex justify-content-between 1h-condensed">
					<div>
						<h6 class="my-0">1달 이용권 구매</h6>
						<small class="text-muted">만료 일자</small>
					</div>
				</li>
			</ul>
			<form class="card p-2">
				<div class="input-group">
					<input type="text" class="form-control" placeholder = "프로모션 코드">
					<div class="input-group-append">
						<button type="submit" class="btn btn-secondary">Redeem</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-8 order-md-1">
			<h4 class="mb-3">결제 상세</h4>
			<form class="needs-validation" novalidate>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="성">성 입력</label>
						<input type="text" class="form-control" id="성" placeholder="성 입력">
						<div class="invalid-feedback">성 입력 필수</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="이름">이름 입력</label>
						<input type="text" class="form-control" id="이름" placeholder="이름 입력">
						<div class="invalid-feedback">이름 입력 필수</div>
					</div>
				</div>
				<div class="mb-3">
					<label for="username">ID</label>
					<div class="input-group">
						<div class="input-group-prepend"><span class="input-group-text">@</span></div>
						<input type="text" class="form-control" id="username" placeholder="아이디 입력" required>
						<div class="invalid-feedback" style="width:100%;"></div>
					</div>
				</div>
				<div class="mb-3">
					<label for="email">Email<span class="text-muted">(Optional)</span></label>
					<input type="email" class="form-control" id="email" placeholder="you@example.com">
					<div class="invalid-feedback">이메일을 다시 확인해주세요</div>
				</div>
				<div class="mb-3">
					
				</div>
			</form>
		</div>
		</div>
	</section>
</body>
</html>