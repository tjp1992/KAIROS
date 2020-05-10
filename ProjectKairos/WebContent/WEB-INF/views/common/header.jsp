<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="/src/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/src/css/styles.css" />
<link rel="stylesheet" href="/src/css/header/header.css" />
<header>
	<c:if test="${empty sessionScope.user }">
		<div class="ld-header_top_wrapper">
			<a href="/loginFrm"> <img src="/src/imgs/icons/user.png" alt="" /></a>
			<span><a href="/loginFrm">로그인</a></span> <span><a
				href="/joinFrm">회원가입</a></span>
		</div>
	</c:if>
	<c:if test="${not empty sessionScope.user }">
		<div class="ld-header_top_wrapper">
			<a href="/mypageFrm"> <img src="/src/imgs/icons/user.png" alt="" /></a>
			<span><a href="/mypageFrm">${sessionScope.user.userName }</a></span>
		</div>
	</c:if>
	<nav>
		<div class="ld-main_wrapper">
			<a href="/"> <img src="/src/imgs/logo/main_logo.png" alt="메인" />
			</a>
		</div>
		<div class="ld-menu_wrapper">
			<span><a href="/noticeFrm">공지사항</a></span> <span><a
				href="/rankingFrm">차트</a></span>
			<form action="/searchFrm" method="GET" class="ld-search">
				<input type="text" id="ld-keyword" />
				<button type="submit" id="ld-search">
					<img src="/src/imgs/icons/search.png" alt="" />
				</button>
			</form>
			<span><a href="/voucherFrm">이용권</a></span> <span><a
				href="/myMusicFrm">My Music</a></span>
		</div>
	</nav>
</header>
