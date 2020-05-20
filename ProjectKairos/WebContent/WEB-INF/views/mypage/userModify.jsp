<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<link href="/src/css/userMypage/userModify.css" rel="stylesheet" type="text/css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=rqfxm5odrc&submodules=geocoder"></script>

<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<script>

var result = new Array("success","false","false","success","success","success","success","success","success","success");
var beforeStr = '${sessionScope.user.email }';
var ArrayEmail = beforeStr.split('@');
var beforeAddr = '${sessionScope.user.addr }';
var addr = beforeAddr.split('/');
$(function(){
	$("#modifyFullMail").val('${sessionScope.user.email }');
	$("#mail1").val(ArrayEmail[0]);
	$("#mail2").val(ArrayEmail[1]);
	$("#postCode").val(addr[0]);
	$("#roadAddr").val(addr[1]);
	$("#detailAddr").val(addr[2]);
	$("#userPw").focusout(function() {
		var pw = $("#userPw").val();
		var pwExp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
		if (!pwExp.test(pw)) {
			$(this).css("border","1px solid red");
			$("#pwMsg").html("양어 대/소문자 특수문자 8~16자리");
			$("#pwMsg").css("color", "red");
			result[1]='false';
			console.log(result);
		} else {
			$("#pwMsg").html("");
			$(this).css("border","1px solid green");
			result[1]='success';
			console.log(result);
		}
	});
	$("#userPwd").focusout(function() {
		var pwd = $("#userPwd").val();
		var pw = $("#userPw").val();
		if (pwd == pw) {
			$(this).css("border","1px solid green");
			$("#pwdMsg").css("color", "green");
			$("#pwdMsg").html("일치합니다");
			result[2]='success';
			console.log(result);
		} else {
			$(this).css("border","1px solid red");
			$("#pwdMsg").css("color", "red");
			$("#pwdMsg").html("일치하지 않습니다");
			result[2]='false';
			console.log(result);
		}
	});
	
	$("#userName").change(function(){
		var nameExp = /^[a-zA-Z가-힣]{2,10}$/;
		if(!nameExp.test($("#userName").val())){
			$("#userName").css('border','1px solid red');
			$("#nameMsg").html("한글 혹은 영어 대/소문자 2~10자리");
			$("#nameMsg").css('color','red');
			result[5]='false';
			console.log(result);
		}else{
			$("#userName").css('border','1px solid green');
			$("#nameMsg").html("");
			result[5]='success';
			console.log(result);
		}
	});
	$("#userNick").keydown(function(){
		result[6]='false';
		$("#userNick").css("border","1px solid red");
		$("#nickMsg").css("color", "red");
		$("#nickMsg").html("중복체크 해주세요");
			console.log(result);
	});
	$("#overLapNick").click(function() {
		var userNick = $("#userNick").val();
		var nickExp = /^[A-Za-z0-9가-힣]{2,16}$/;
		$.ajax({
			url : "/overLapNick",
			type : "get",
			data : {
				nick : userNick
			},
			success : function(data) {
				if (data == 1) {
					if (!nickExp.test(userNick)) {
						$("#userNick").css("border","1px solid red");
						$("#nickMsg").html("영어 대/소문자 숫자로 2~16글자 한글1~8글자");
						$("#nickMsg").css("color", "red");
						result[6]='false';
						console.log(result);
					}else {
						$("#userNick").css("border","1px solid green");
						$("#nickMsg").css("color", "green");
						$("#nickMsg").html("사용할 수 있는 닉네임 입니다.");
						result[6]='success';
						console.log(result);
					}
				} else {
					if($("#userNick").val()=='${sessionScope.user.userNick }'){
						$("#userNick").css("border","1px solid green");
						$("#nickMsg").css("color", "green");
						$("#nickMsg").html("사용할 수 있는 닉네임 입니다.");
						result[6]='success';
						console.log(result);
					}else{
					$("#userNick").css("border","1px solid red");
					$("#nickMsg").html("중복 닉네임 입니다.");
					$("#nickMsg").css("color", "red");
					result[6]='false';
					console.log(result);
					}
				}
			}
		});
	});
	$("#phone").change(function(){
		var nameExp = /^[0-1]{3}[0-9]{4}[0-9]{4}$/;
		if(!nameExp.test($("#phone").val())){
			$("#phone").css("border","1px solid red");
			$("#phoneMsg").html("01000000000 형식으로 입력해주세요");
			$("#phoneMsg").css('color','red');
			result[4]='false';
			console.log(result);
		}else{
			$("#phoneMsg").html("");
			$("#phone").css("border","1px solid green");
			result[4]='success';
			console.log(result);
		}
	});
	$("#mail1").keydown(function(){
		result[3]='false';
		$("#mailMsg").css("color", "red");
		$("#mailMsg").html("이메일 인증을 해주세요");
			console.log(result);
	});
	$("#mail2").keydown(function(){
		result[3]='false';
		$("#mailMsg").css("color", "red");
		$("#mailMsg").html("이메일 인증을 해주세요");
			console.log(result);
	});
	$('#selectEmail').change(function() {
		result[3]='false';
		$("#mailMsg").css("color", "red");
		$("#mailMsg").html("이메일 인증을 해주세요");
		console.log(result);
		$("#selectEmail option:selected").each(function() {
			if ($(this).val() == '1') {
				$("#mail2").val('');
				console.log($("#mail2").val());
				$("#mail2").attr("disabled", false);
			} else {
				$("#mail2").val($(this).text());
				console.log($("#mail2").val());
				$("#mail2").attr("disabled", true);
			}
		});
	});

	$("#mailBtn").click(function() {
		if($("#mail1").val()==""){
			$("#mail1Msg").show();
			$("#mail1Msg").html("메일을 입력하세요");
			$("#mail1Msg").css("color","red");
		}else{
			$("#mail1Msg").hide();
			$("#mail1Msg").html("");
			var email = $("#mail1").val()+"@"+$("#mail2").val();
			$("#modifyFullMail").val(email);
			$.ajax({
				url : "/sendMail",
				type : "post",
				data : {
					email : email
				},
				success : function(data) {
					mailCode = data;
					$("#mailCode").show();
					$("#mailResult").show();
					$("#input").show();
					var num = 60*3; // 몇분을 설정할지의 대한 변수 선언
				    function time(){
				        myVar = setInterval(alertFunc, 1000); 
				    }
				    time();
				 
				    function alertFunc() {
				        var min = num / 60; 
				        min = Math.floor(min);
				         
				        var sec = num - (60 * min);
				 
				        var $input = $('#input').val(min + '분' + sec + '초');
			
				        if(num == 0){
				            clearInterval(myVar) // num 이 0초가 되었을대 clearInterval로 타이머 종료
				            $("#mailCode").hide();
							$("#mailResult").hide();
							$("#input").hide();
				           	$("#mail1").val('');
				           	$("#mail1Msg").show();
				           	$("#mail1Msg").html("인증 제한시간이 초과되었습니다. 다시 시도해주세요");
				           	$("#mail1Msg").css("color","red");
				           	
				        }
				        num--;
				    }
				}
			});
		}
	});
	$("#mailResult").click(function(){
		if($("#mailCode").val()==mailCode){
			$("#mailMsg").html('인증 성공');
			$("#mailMsg").css('color','green');
			clearInterval(myVar);
			$("#mailCode").hide();
			$("#mailResult").hide();
			$("#input").hide();
			$("#mail1").css("border","1px solid green");
			$("#mail2").css("border","1px solid green");
			result[3]='success';
			console.log(result);
		}else{
			$("#mailMsg").html('인증 실패');
			$("#mailMsg").css('color','red');
			result[3]='false';
			console.log(result);
		}
	});
	$("#addrSearch").click(function(){
		new daum.Postcode({
			oncomplete:function(data){
				$("#postCode").val(data.zonecode);
				$("#roadAddr").val(data.roadAddress);
				if($("#postCode").val()!=null){
					$("#postCode").css("border","1px solid green");
					$("#roadAddr").css("border","1px solid green");
					$("#addrMsg").html("");
					result[7]='success';
					result[8]='success';
					console.log(result);
				}else{
					$("#postCode").css("border","1px solid red");
					$("#roadAddr").css("border","1px solid red");
					$("#addrMsg").html("주소를 입력해주세요");
					$("#addrMsg").css("color","red");
					result[7]='false';
					result[8]='false';
					console.log(result);
				}
			}
		}).open();
	});
	$("#detailAddr").keydown(function(){
		result[9]='false';
		$(this).css("border","1px solid red");
		$("#addrMsg").css("color", "red");
		$("#addrMsg").html("상세주소를 입력해주세요");
			console.log(result);
	});
	$("#detailAddr").change(function(){
		if($(this).val()==''){
			$(this).css("border","1px solid red");
			$("#addrMsg").html("주소를 입력해주세요");
			$("#addrMsg").css("color","red");
			result[9]='false';
			console.log(result);
		}else{
			$(this).css("border","1px solid green");
			$("#addrMsg").html("");
			result[9]='success';
			console.log(result);
		}
	});

	$("#cancel").click(function(){
		location.href="/loginFrm";
	});

});
function check(){
	if(result[1]==false){
		return false;
	}else if(result[2]=='false'){
		return false;
	}else if(result[3]=='false'){
		return false;
	}else if(result[4]=='false'){
		return false;
	}else if(result[5]=='false'){
		return false;
	}else if(result[6]=='false'){
		return false;
	}else if(result[7]=='false'){
		return false;
	}else if(result[8]=='false'){
		return false;
	}else if(result[9]=='false'){
		return false;
	}else{
		return true;
	}
	
}
</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
		<h2>개인정보 수정</h2>
			<form action="/userModify" id="userModify" method="post" onsubmit='return check()'>
			<input type='hidden' id='modifyFullMail' name ='modifyFullMail'>
				<div class="wrapper">
					<table>
						<tr>
							<th><label for="userId">아이디</label></th>
							<td>
							<input type="text" name="userId" id="userId" class="form-control" value="${sessionScope.user.userId }" readonly style=" width:185px;height: 35px;">
							</td>
						</tr>
						<tr>
							<th><label for="userPw">새 비밀번호</label></th>
							<td>
							<input type="password" name="userPw" id="userPw" style="height: 35px;" required><br>
							<span id="pwMsg"></span>
							</td>
						</tr>
						<tr>
							<th><label for="userPwd">새 비밀번호 확인</label></th>
							<td>
							<input type="password" name="userPwd" id="userPwd" style="height: 35px;" required><br>
							<span id="pwdMsg"></span>
							</td>
						</tr>
						<tr>
							<th><label for="userName">이름</label></th>
							<td>
							<input type="text" name="userName" id="userName" style="height: 35px;" value="${sessionScope.user.userName }" required>
							<br><span id="nameMsg"></span>
							</td>
						</tr>
						<tr>
							<th><label for="userNick">닉네임</label></th>
							<td><input type="text" id="userNick" style="height: 35px;"name="userNick" value="${sessionScope.user.userNick }" required>
								<button type="button" id="overLapNick" style="height: 35px;">중복확인</button><br><span id="nickMsg"></span></td>
						</tr>
						<tr>
							<th><label for="phone">전화번호</label></th>
							<td>
								<input type="text" id="phone" name="phone" style="height: 35px;" value="${sessionScope.user.phone }"required>
								<br><span id="phoneMsg"></span></td>
							</td>
						</tr>
						<tr>
							<th><label for="mail1">이메일</label></th>
							<td>
								<input type="hidden" id="fullMail" name="fullMail">
								<input type="text" id="mail1" name="mail1"
								style="width: 150px; height: 35px;" required>
								@ <input type="text" id="mail2" name="mail2"
								style="width: 150px; height: 35px;" required>
								<select style="width: 100px; height: 35px;" name="selectEmail" id="selectEmail">
									<option value="1">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="daum.net">daum.net</option>
								</select>
								<button type="button" id="mailBtn"
							style="height: 35px;">이메일 인증</button><br>
							<input type="text" id="mailCode" style="display: none; width:150px;">
							<input type="text" id="input" style="width:80px; border: none;display: none;">
							<button type="button" id="mailResult" style="display: none;">메일확인</button>
							<span id="mail1Msg" style="display:none;" class="form-control"></span>
							<span id="mailMsg"></span>
							</td>
						</tr>
						<tr style="border-bottom:1px solid black;">
							<th><label for="addrSearch">주소</label></th>
							<td><input type="text" id="postCode" name="postCode" style="width: 200px; height: 35px; display: inline-block" placeholder="우편번호" readonly required> 
								<input type="text" id="roadAddr" name="roadAddr" style="width: 200px; height: 35px;" placeholder="도로명주소" readonly required>
								<button type="button" id="addrSearch" style="height: 35px;">주소검색</button> <br> 
								<input id="detailAddr" name="detailAddr" style="width: 400px; height: 35px;" type="text" placeholder="상세주소" required>
								<span id="addrMsg"></span>
							</td>
						</tr>
						<tr>
					<td colspan="2" style="text-align: center;">
					<br>
						<button type="submit" id="modify" style="width: 150px; height: 50px;">수정하기</button>
						<button type="button" id="cancel" style="width: 150px; height: 50px;">메인페이지로</button>
					</td>
					</tr>
					</table>	
				</div>
			</form>
	</section>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>