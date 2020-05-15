<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/src/css/bootstrap.css">
<link href="/src/css/login/login.css" rel="stylesheet" type="text/css">
</head>
<script>
window.onload = function() {
	 
    if (getCookie("id")) { // getCookie함수로 id라는 이름의 쿠키를 불러와서 있을경우
        document.loginForm.userid.value = getCookie("id"); //input 이름이 id인곳에 getCookie("id")값을 넣어줌
        document.loginForm.idsave.checked = true; // 체크는 체크됨으로
    }

}

function setCookie(name, value, expiredays) //쿠키 저장함수
{
    var todayDate = new Date();
    todayDate.setDate(todayDate.getDate() + expiredays);
    document.cookie = name + "=" + escape(value) + "; path=/; expires="
            + todayDate.toGMTString() + ";"
}

function getCookie(Name) { // 쿠키 불러오는 함수
    var search = Name + "=";
    if (document.cookie.length > 0) { // if there are any cookies
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // if cookie exists
            offset += search.length; // set index of beginning of value
            end = document.cookie.indexOf(";", offset); // set index of end of cookie value
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
}

function sendit() {
    var frm = document.loginForm;
    if (!frm.userid.value) { //아이디를 입력하지 않으면.
        alert("아이디를 입력 해주세요!");
        frm.userid.focus();
        return;
    }
    if (!frm.pwd.value) { //패스워드를 입력하지 않으면.
        alert("패스워드를 입력 해주세요!");
        frm.pwd.focus();
        return;
    }

    if (document.loginForm.idsave.checked == true) { // 아이디 저장을 체크 하였을때
        setCookie("id", document.loginForm.userid.value, 7); //쿠키이름을 id로 아이디입력필드값을 7일동안 저장
    } else { // 아이디 저장을 체크 하지 않았을때
        setCookie("id", document.loginForm.userid.value, 0); //날짜를 0으로 저장하여 쿠키삭제
    }

    document.loginForm.submit(); //유효성 검사가 통과되면 서버로 전송.

}
</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<section class="cantainer">
		<form action="/login">
			<div class="wrapper">
				<div class="pjytop" style="height:20%; text-align:center; margin-top:10px;">
					<a href="#"><img src="/src/imgs/logo/main_logo.png" style="width:50%; height:100%;"></a>
				</div>
				<div class="pjymid" style="height:45%;">
					<input type="text" name="id" id="id" style="width:300px;height:45px;" placeholder="아이디를 입력하세요">
					<input type="checkbox" id="idSave" style="width:15px;height:15px;margin-left:5px;">아이디 저장<br><br>
					<input type="password" name="pw" id="pw" style="width:300px;height:45px;" placeholder="비밀번호를 입력하세요"><br><br>
					
				</div>
				<div style="height:15%; text-align:center;">
					<a href="/idSearchFrm">아이디찾기</a>/<a href="/pwSearchFrm">비밀번호 찾기</a>
				</div>
				<div class="pjybottom" style="height:20%; text-align:center;">
					<button type="submit">로그인</button>
					<button id="cancel">취소</button>
				</div>
			</div>
		</form>
	</section>
</body>
</html>