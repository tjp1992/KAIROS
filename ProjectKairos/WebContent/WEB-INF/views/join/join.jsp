<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/src/css/bootstrap.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=rqfxm5odrc&submodules=geocoder"></script>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<style>
input {
	border: 1px solid black;
	outline: none;
	font-size: 15pt;
}

table {
	border : 1px solid #FFC107;
	width: 780px;
	margin: 0 auto;
}
th {
	text-align: right;
	padding-right: 30px;
	width: 200px;
	height: 80px;
}
td>div{
	height: 35px;
	line-height: 35px;
}
td>button{
	margin-top: -8px;
}
#title{
	margin-top : 20px;
	text-align : center;
}
</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<section class="cantainer">
	<div class="wrapper">
		<div id="title"><h1>회원가입</h1></div>
		<form action="/join" name="join">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" id="id" name="id" style="width: 300px; height: 35px;"
							class="border border-warning">
						<button id="overlapId" class="btn btn-warning px-3"
							style="height: 35px;">중복확인</button>
						<span></span>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="pw" name="pw"
						style="width: 300px; height: 35px;" class="border border-warning"><span></span></td>
				</tr>
				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" id="pwd"
						style="width: 300px; height: 35px;" class="border border-warning"><span></span></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						</div><input type="text" id="mail1" name="mail1"
						style="width: 150px; height: 35px;" class="border border-warning">
						@ <input type="text" id="mail2" name="mail2"
						style="width: 150px; height: 35px;" class="border border-warning">
						<select style="width: 100px; height: 35px;"
						class="border border-warning">
							<option>직접입력</option>
							<option>naver.com</option>
							<option>gmail.com</option>
							<option>daum.net</option>
					</select>
						<button id="mailBtn" class="btn btn-warning px-3"
							style="height: 35px;">이메일 인증</button> <input type="text"
						id="mailCode" style="display: none;" class="border border-warning">
						<button id="mailResult" style="display: none;">메일확인</button>
						<span id="mailMsg"></span></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" id="phone" name="phone"
						style="width: 300px; height: 35px;" class="border border-warning">
						<button id="phoneResult" class="btn btn-warning px-3"
							style="height: 35px;">인증번호 받기</button> <span></span></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" id="name" name="name"
						style="width: 200px; height: 35px;" class="border border-warning"><span></span></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" id="nickName" name="nickName"
						style="width: 300px; height: 35px;" class="border border-warning">
						<button id="overlapNick" class="btn btn-warning px-3"
							style="height: 35px;">중복확인</button> <span></span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" id="postCode" name="postCode"
						class="border border-warning"
						style="width: 200px; height: 35px; display: inline-block"
						placeholder="우편번호" readonly> <input type="text"
						id="roadCode" name="roadCode" class="border border-warning"
						style="width: 200px; height: 35px;" placeholder="도로명주소" readonly>
						<button id="addrSearch" class="btn btn-warning px-3"
							style="height: 35px;">주소검색</button> <br> 
						<input id="detailAddr" name="detailAddr" class="border border-warning"
						style="width: 400px; height: 35px;" type="text" placeholder="상세주소">
						<span></span></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
					<br>
						<button type="submit" class="btn btn-warning px-3" style="width: 150px; height: 50px;">가입하기</button>
						<button type="reset" class="btn btn-danger px-3" style="width: 150px; height: 50px;">초기화</button>
						<button type="button" id="cancel" class="btn btn-info px-3" style="width: 150px; height: 50px;">메인페이지로</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</section>
</body>
</html>