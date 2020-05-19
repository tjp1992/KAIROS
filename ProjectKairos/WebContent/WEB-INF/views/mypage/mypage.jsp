<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<link href="/src/css/userMypage/usermypagemain.css" rel="stylesheet"
	type="text/css">

<script>
	$(function() {
		$("#userInquiryList").click(function() {
			location.href = "/userInquiryList?reqPage=1";
		});
		$("#userInquiry").click(function() {
			location.href = "/userInquiryFrm";
		});
		$("#userModify").click(
				function() {
					window.open("/confirmPwFrm", "confirmPw",
							"width=300,height=300,top=100,left=750");
				});
		$("#userDelete").click(function() {
			var result = confirm("탈퇴하시겠습니까?");
			if (result) {
				location.href = "/userDelete";
			} else {
				location.href = "/mypageFrm";
			}
		});
		$("#manageMusicFrm").click(function() {
			location.href = "/manageMusicFrm";
		});
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
	<h2 class="h2">
		마이페이지 <span class="span1">${sessionScope.user.userName } 님
			환영합니다 </span>
	</h2>
	<c:if test="${empty date }">
		<span>이용권이 없습니다.<a href="/voucherFrm">이용권 구매</a></span>
	</c:if> <c:if test="${not empty date }">
		<span>${date }일 남았습니다.</span>
	</c:if>
	<div class="PHJup">
		<h3>메뉴</h3>
		<div class="div1">
			<table class="table t1 table-hover">
				<tr class="move" id="userModify">
					<td scope="row"><label>개인정보 수정</label></td>
					<td><span class="glyphicon glyphicon-chevron-right"></span></td>
				</tr>
				<tr class="move" id="userInquiry">
					<td scope="row"><label>1:1문의하기</label></td>
					<td><span class="glyphicon glyphicon-chevron-right"></span></td>
				</tr>
			</table>
		</div>
		<div class="verticalLine"></div>
		<div class="div1">
			<table class="table t1 table-hover">
				<tr class="move" id="userInquiryList">
					<td scope="row"><label>내 문의내역</label></td>
					<td><span class="glyphicon glyphicon-chevron-right"></span></td>
				</tr>
				<tr class="move" id="userDelete">
					<td scope="row"><label>회원탈퇴</label></td>
					<td><span class="glyphicon glyphicon-chevron-right"></span></td>
				</tr>
			</table>
		</div>
		<div class="div1">
			<table class="table t1 table-hover">
				<tr class="move" id="manageMusicFrm">
					<td scope="row" style="width:394px;"><label>음악 관리</label></td>
					<td><span class="glyphicon glyphicon-chevron-right"></span></td>
				</tr>
			</table>
		</div>
	</section>
</body>
</html>