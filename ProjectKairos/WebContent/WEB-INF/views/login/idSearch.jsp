<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/src/css/bootstrap.css">
<link href="/src/css/login/idsearch.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<section class="cantainer">
		<form action="/idSearch">
			<div class="wrapper">
				<div class="pjytop" style="height:25%; text-align:center; padding-top:35px;">
					<h3 style="font-weight:bold;">아이디 찾기</h3>
				</div>
				<div class="pjymid" style="height:45%;">
					<input type="text" id="name" name="name" style="width:300px;height:45px;" placeholder="이름을 입력하세요"><br><br>
					<input type="text" id="mail" name="mail" style="width:300px;height:45px;" placeholder="이메일을 입력하세요"><br><br>
					
				</div>
				<div class="pjybottom" style="height:30%; text-align:center;">
					<button type="submit">아이디 조회</button>
					<button id="cancel">취소</button>
				</div>
			</div>
		</form>
	</section>
</body>
</html>