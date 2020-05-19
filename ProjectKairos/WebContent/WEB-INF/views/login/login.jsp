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

</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<section class="cantainer">
		<form action="/login" method="post">
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