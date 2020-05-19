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
	var result = new Array("false","false","false","false","false","false","false","false","false","false");
	console.log(result);
	$(function() {
		var myVar;
		$("#id").keydown(function(){
			result[0]='false';
			$("#idMsg").css("color", "red");
			$("#idMsg").html("아이디 중복확인 해주세요");
			$("#id").removeClass();
			$("#id").addClass("border border-danger");
				console.log(result);
		});
		$("#overLapId").click(function() {
			var userId = $("#id").val();
			var idExp = /[A-Za-z0-9]{4,12}/;
			$.ajax({
				url : "/overLapId",
				type : "get",
				data : {
					id : userId
				},
				success : function(data) {
					if (data == 1) {
						if (!idExp.test(userId)) {
							$("#id").removeClass();
							$("#id").addClass("border border-danger");
							$("#idMsg").html("영어 대/소문자 숫자로 4~12글자");
							$("#idMsg").css("color", "red");
							result[0]='false';
						} else {
							$("#id").removeClass();
							$("#id").addClass("border border-success");
							$("#idMsg").css("color", "green");
							$("#idMsg").html("사용할 수 있는 아이디 입니다.");
							result[0]='success';
							console.log(result);
						}
					} else {
						$("#id").removeClass();
						$("#id").addClass("border border-danger");
						$("#idMsg").html("중복 아이디 입니다.");
						$("#idMsg").css("color", "red");
						result[0]='false';
						console.log(result);
					}
				}
			});
		});
		
		$("#pw").focusout(function() {
			var pw = $("#pw").val();
			var pwExp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
			if (!pwExp.test(pw)) {
				$(this).removeClass();
				$(this).addClass("border border-danger");
				$(this).next().next().html("양어 대/소문자 특수문자 8~16자리");
				$(this).next().next().css("color", "red");
				result[1]='false';
				console.log(result);
			} else {
				$(this).next().next().html("");
				$(this).removeClass();
				$(this).addClass("border border-success");
				result[1]='success';
				console.log(result);
			}
		});
		$("#pwd").focusout(function() {
			var pwd = $("#pwd").val();
			var pw = $("#pw").val();
			if (pwd == pw) {
				$(this).removeClass();
				$(this).addClass("border border-success");
				$(this).next().next().css("color", "green");
				$(this).next().next().html("일치합니다");
				result[2]='success';
				console.log(result);
			} else {
				$(this).removeClass();
				$(this).addClass("border border-danger");
				$(this).next().next().css("color", "red");
				$(this).next().next().html("비밀번호와 일치하지 않습니다.");
				result[2]='false';
				console.log(result);
			}
		});
		$("#mail1").keydown(function(){
			result[3]='false';
			$("#mailMsg").css("color", "red");
			$("#mailMsg").html("메일인증 해주세요");
			$("#mail1").removeClass();
			$("#mail1").addClass("border border-danger");
				console.log(result);
		});
		$("#mail2").keydown(function(){
			result[3]='false';
			$("#mailMsg").css("color", "red");
			$("#mailMsg").html("아이디 중복확인 해주세요");
			$("#mail2").removeClass();
			$("#mail2").addClass("border border-danger");
				console.log(result);
		});
		$('#selectEmail').change(function() {
			result[3]='false';
			$("#mailMsg").css("color", "red");
			$("#mail2").removeClass();
			$("#mail2").addClass("border border-danger");
			$("#mailMsg").html("이메일 인증을 해주세요");
			console.log(result);
			$("#selectEmail option:selected").each(function() {
				if ($(this).val() == '1') {
					$("#mail2").val('');
					$("#mail2").attr("disabled", false);
				} else {
					$("#mail2").val($(this).text());
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
				var email = ($("#mail1").val() + "@" + $("#mail2").val());
				$("#join").append("<input type='hidden' name = 'fullMail' value='"+email+"'>");
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
				$("#mail1").removeClass();
				$("#mail2").removeClass();
				$("#mail1").addClass("border border-success");
				$("#mail2").addClass("border border-success");
				result[3]='success';
				console.log(result);
			}else{
				$("#mailMsg").html('인증 실패');
				$("#mailMsg").css('color','red');
				result[3]='false';
				console.log(result);
			}
		});
		$("#phone").change(function(){
			var nameExp = /^[0-1]{3}[0-9]{4}[0-9]{4}$/;
			if(!nameExp.test($("#phone").val())){
				$("#phone").removeClass();
				$("#phone").addClass("border border-danger");
				$("#phoneMsg").html("01000000000 형식으로 입력해주세요");
				$("#phoneMsg").css('color','red');
				result[4]='false';
				console.log(result);
			}else{
				$("#phoneMsg").html("");
				$("#phone").removeClass();
				$("#phone").addClass("border border-success");
				result[4]='success';
				console.log(result);
			}
		});
		
		$("#name").change(function(){
			var nameExp = /^[a-zA-Z가-힣]{2,10}$/;
			if(!nameExp.test($("#name").val())){
				$("#name").removeClass();
				$("#name").addClass("border border-danger");
				$("#nameMsg").html("한글 혹은 영어 대/소문자 2~10자리");
				$("#nameMsg").css('color','red');
				result[5]='false';
				console.log(result);
			}else{
				$("#nameMsg").html("");
				$("#name").removeClass();
				$("#name").addClass("border border-success");
				result[5]='success';
				console.log(result);
			}
		});
		$("#nick").keydown(function(){
			result[6]='false';
			$("#nick").removeClass();
			$("#nick").addClass("border border-danger");
			$("#nickMsg").css("color", "red");
			$("#nickMsg").html("중복체크 해주세요");
				console.log(result);
		});
		$("#overLapNick").click(function() {
			var userNick = $("#nick").val();
			var nickExp = /[A-Za-z0-9가-힣]{2,16}/;
			$.ajax({
				url : "/overLapNick",
				type : "get",
				data : {
					nick : userNick
				},
				success : function(data) {
					if (data == 1) {
						if (!nickExp.test(userNick)) {
							$("#nick").removeClass();
							$("#nick").addClass("border border-danger");
							$("#nickMsg").html("영어 대/소문자 숫자로 2~16글자 한글1~8글자");
							$("#nickMsg").css("color", "red");
							result[6]='false';
							console.log(result);
						} else {
							$("#nick").removeClass();
							$("#nick").addClass("border border-success");
							$("#nickMsg").css("color", "green");
							$("#nickMsg").html("사용할 수 있는 닉네임 입니다.");
							result[6]='success';
							console.log(result);
						}
					} else {
						$("#nick").removeClass();
						$("#nick").addClass("border border-danger");
						$("#nickMsg").html("중복 닉네임 입니다.");
						$("#nickMsg").css("color", "red");
						result[6]='false';
						console.log(result);
					}
				}
			});
		});
		$("#addrSearch").click(function(){
			new daum.Postcode({
				oncomplete:function(data){
					$("#postCode").val(data.zonecode);
					$("#roadAddr").val(data.roadAddress);
					if($("#postCode").val()!=null){
						$("#postCode").removeClass();
						$("#roadAddr").removeClass();
						$("#postCode").addClass("border border-success");
						$("#roadAddr").addClass("border border-success");
						$("#addrMsg").html("");
						result[7]='success';
						result[8]='success';
						console.log(result);
					}else{
						$("#postCode").removeClass();
						$("#roadAddr").removeClass();
						$("#postCode").addClass("border border-danger");
						$("#roadAddr").addClass("border border-danger");
						$("#addrMsg").html("주소를 입력해주세요");
						$("#addrMsg").css("color","red");
						result[7]='false';
						result[8]='false';
						console.log(result);
					}
				}
			}).open();
		});

		$("#detailAddr").change(function(){
			if($(this).val()==''){
				$(this).removeClass();
				$(this).addClass("border border-danger");
				$("#addrMsg").html("주소를 입력해주세요");
				$("#addrMsg").css("color","red");
				result[9]='false';
				console.log(result);
			}else{
				$(this).removeClass();
				$(this).addClass("border border-success");
				$("#addrMsg").html("");
				result[9]='success';
				console.log(result);
			}
		});
		$("#reset").click(function(){
			$("input").removeClass();
			$("input").addClass("border border-warning");
			$("span").html("");
			$("#input").removeClass();
			for(var i=0;i<result.length;i++){
				result[i]='false';
			}
			console.log(result);
		});
		$("#cancel").click(function(){
			location.href="/loginFrm"
		});
	});
	function check(){
		if(result[0]=='false'){
			return false;
		}else if(result[1]=='false'){
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
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<section class="cantainer">
	<div class="wrapper">
		<div id="title">
			<h1>회원가입</h1>
		</div>
		<form action="/join" name="join" id="join" method="post" onsubmit='return check()'>
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="id" name="id"
						style="width: 300px; height: 35px;" class="border border-warning"
						required>
						<button type="button" id="overLapId" class="btn btn-warning px-3"
							style="height: 35px;">중복확인</button> <br> <span id="idMsg"></span>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="pw" name="pw"
						style="width: 300px; height: 35px;" class="border border-warning" required><br>
						<span></span></td>
				</tr>
				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" id="pwd"
						style="width: 300px; height: 35px;" class="border border-warning" required><br>
						<span></span></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" id="mail1" name="mail1"
						style="width: 150px; height: 35px;" class="border border-warning" required>
						@ <input type="text" id="mail2" name="mail2"
						style="width: 150px; height: 35px;" class="border border-warning"
						disabled value="naver.com" required> <select
						style="width: 100px; height: 35px;" class="border border-warning"
						name="selectEmail" id="selectEmail" required>
							<option value="1">직접입력</option>
							<option value="naver.com" selected>naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="daum.net">daum.net</option>
					</select>
						<button type="button" id="mailBtn" class="btn btn-warning px-3"
							style="height: 35px;">이메일 인증</button><br>
							<span id="mail1Msg" style="display:hidden;"></span>
							<input type="text"
						id="mailCode" style="display: none;" class="border border-warning">
						<input type="text" id="input" style="width:80px; border: none;display: none;">
						<button type="button" id="mailResult" style="display: none;">메일확인</button>
						<span id="mailMsg"></span></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" id="phone" name="phone"
						style="width: 300px; height: 35px;" class="border border-warning" required>
						<button type="button" id="phoneResult"
							class="btn btn-warning px-3" style="height: 35px;">인증번호
							받기</button><br><span id="phoneMsg"></span></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" id="name" name="name"
						style="width: 200px; height: 35px;" class="border border-warning" required><span id="nameMsg"></span></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" id="nick" name="nick"
						style="width: 300px; height: 35px;" class="border border-warning" required>
						<button type="button" id="overLapNick"
							class="btn btn-warning px-3" style="height: 35px;">중복확인</button><br>
						<span id="nickMsg"></span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td id="addr">
						<input type="text" id="postCode" name="postCode"class="border border-warning" style="width: 200px; height: 35px; font-size:10pt; display: inline-block" placeholder="우편번호" readonly required> 
						<input type="text" id="roadAddr" name="roadAddr" class="border border-warning"style="width: 200px; height: 35px; font-size:10pt;" placeholder="도로명주소" readonly required>
						<button type="button" id="addrSearch" class="btn btn-warning px-3" style="height: 35px;">주소검색</button> <br> 
							<input id="detailAddr" name="detailAddr" class="border border-warning" style="width: 400px; height: 35px; font-size:10pt;" type="text" placeholder="상세주소" required>
						<span id="addrMsg"></span></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><br>
						<button type="submit" class="btn btn-warning px-3" id="submit"
							style="width: 150px; height: 50px;">가입하기</button>
						<button type="reset" class="btn btn-danger px-3" id="reset"
							style="width: 150px; height: 50px;">초기화</button>
						<button type="button" id="cancel" class="btn btn-info px-3"
							style="width: 150px; height: 50px;">메인페이지로</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</section>
</body>
</html>