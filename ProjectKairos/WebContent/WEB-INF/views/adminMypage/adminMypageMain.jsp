<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<link href="/src/css/adminMypage/adminmypagemain.css" rel="stylesheet" type="text/css">
<style>

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
	<h2 class="h2">
		내 정보 <span class="span1">로그인계정 : </span>
	</h2>
	<div class="PHJup">
		<h5>관리자 정보수정</h5>
		<div class="div1">
			<table class="table t1">
					<tr>
						<td scope="row"><label>이용권 관리</label></td>
						<td><a href="#"><label class="la1"><img src="src/imgs/go.png" class="pic1"></label></a></td>
					</tr>
					<tr>
						<td scope="row"><label>공지사항 관리</label></td>
						<td><a href="#"><label class="la1"><img src="src/imgs/go.png" class="pic1"></label></a></td>
					</tr>
			</table>
		</div>
		<div class="verticalLine"></div>
		<div class="div1">
			<table class="table t1">
					<tr>
						<td scope="row"><label>음악 관리</label></td>
						<td><a href="#"><label class="la1"><img src="src/imgs/go.png" class="pic1"></label></a></td>
					</tr>
					<tr>
						<td scope="row"><label>고객문의 관리</label></td>
						<td><a href="#"><label class="la1"><img src="src/imgs/go.png" class="pic1"></label></a></td>
					</tr>
					<tr>
					</tr>
			</table>
		</div>
	</div>
	</section>



</body>
</html>