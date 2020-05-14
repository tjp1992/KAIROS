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
<link rel="stylesheet" href="/src/css/bootstrap.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=rqfxm5odrc&submodules=geocoder"></script>

<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link href="/src/css/join/join.css" rel="stylesheet" type="text/css">
</head>
<script>
		$(function(){
			/*$("#overlapId").click(function(){
				var id = $("#id").val();
				var idExp =/[A-Za-z0-9]{4,12}/;
				if(!idExp.test(id)){
					$(this).next().next().html("영어 대/소문자 숫자로 4~12글자");
					$(this).next().next().css("color","red");
				}else{
					//window.open("/overLapId?id="+id, "overLapId", "width=400, height=350");
					$(this).next().next().html("");
					$(this).prev().removeClass();
					$(this).prev().addClass("border border-success");
				}
			});*/
			$("#overlapId").click(function(){
				var userId = $("#id").val();
				var idExp =/[A-Za-z0-9]{4,12}/;
				$.ajax({
					url : "/overLapId",
					type : "get",
					data : {id:userId},
					success : function(data){
						if(data == 1){
							if(!idExp.test(userId)){
								$(this).next().next().html("영어 대/소문자 숫자로 4~12글자");
								$(this).next().next().css("color","red");
							}else{
								$(this).prev().removeClass();
								$(this).prev().addClass("border border-success");
								$("#idChk").html("사용할 수 있는 아이디 입니다.");
							}
						}else {
							$("#idChk").html("중복 아이디 입니다.");
						}
					}
				});
			});
			     
			$("#pw").focusout(function(){
				var pw = $("#pw").val();
				var pwExp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
				if(!pwExp.test(pw)){
					$(this).removeClass();
					$(this).addClass("border border-danger");
					$(this).next().next().html("양어 대/소문자 특수문자 8~16자리");
					$(this).next().next().css("color","red");
				}else{
					$(this).next().next().html("");
					$(this).removeClass();
					$(this).addClass("border border-success");
				}
			});
			$("#pwd").focusout(function(){
				var pwd = $("#pwd").val();
				var pw = $("#pw").val();
				if(pwd == pw){
					$(this).removeClass();
					$(this).addClass("border border-success");
					$(this).next().next().css("color","green");
					$(this).next().next().html("일치합니다");
				}else{
					$(this).removeClass();
					$(this).addClass("border border-danger");
					$(this).next().next().css("color","red");
					$(this).next().next().html("비밀번호와 일치하지 않습니다.");
				}
			});
		});
	</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<section class="cantainer">
	<div class="wrapper">
		<div id="title">
			<h1>회원가입</h1>
		</div>
		<form action="/join" name="join">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="id" name="id"
						style="width: 300px; height: 35px;" class="border border-warning"
						required>
						<button type="button" id="overlapId" class="btn btn-warning px-3"
							style="height: 35px;">중복확인</button> <br> <span id="idChk"></span>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="pw" name="pw"
						style="width: 300px; height: 35px;" class="border border-warning"><br>
						<span></span></td>
				</tr>
				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" id="pwd"
						style="width: 300px; height: 35px;" class="border border-warning"><br>
						<span></span></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" id="mail1" name="mail1"
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
						<button type="button" id="mailBtn" class="btn btn-warning px-3"
							style="height: 35px;">이메일 인증</button> <input type="text"
						id="mailCode" style="display: none;" class="border border-warning">
						<button type="button" id="mailResult" style="display: none;">메일확인</button>
						<span id="mailMsg"></span></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" id="phone" name="phone"
						style="width: 300px; height: 35px;" class="border border-warning">
						<button type="button" id="phoneResult"
							class="btn btn-warning px-3" style="height: 35px;">인증번호
							받기</button> <span></span></td>
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
						<button type="button" id="overlapNick"
							class="btn btn-warning px-3" style="height: 35px;">중복확인</button>
						<span></span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" id="postCode" name="postCode"
						class="border border-warning"
						style="width: 200px; height: 35px; display: inline-block"
						placeholder="우편번호" readonly> <input type="text"
						id="roadCode" name="roadCode" class="border border-warning"
						style="width: 200px; height: 35px;" placeholder="도로명주소" readonly>
						<button type="button" id="addrSearch" class="btn btn-warning px-3"
							style="height: 35px;">주소검색</button> <br> <input
						id="detailAddr" name="detailAddr" class="border border-warning"
						style="width: 400px; height: 35px;" type="text" placeholder="상세주소">
						<span></span></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><br>
						<button type="submit" class="btn btn-warning px-3"
							style="width: 150px; height: 50px;">가입하기</button>
						<button type="reset" class="btn btn-danger px-3"
							style="width: 150px; height: 50px;">초기화</button>
						<button type="button" id="cancel" class="btn btn-info px-3"
							style="width: 150px; height: 50px;">메인페이지로</button></td>
				</tr>
			</table>
		</form>
	</div>
	</section>
</body>
</html>