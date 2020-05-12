<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/src/css/bootstrap.css">
</head>
	<style>
		.wrapper{
			width : 500px;
			height : 400px;
			border : 1px solid black;
			margin : 0 auto;
			margin-top:100px;
		}
		.pjymid{
			padding-top :30px;
			text-align: center;
		}
		.pjybottom>button{
			width:150px;
			height:50px;
		}
	</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<section class="cantainer">
		<form action="/pwSearch">
			<div class="wrapper">
				<div class="pjytop" style="height:15%; text-align:center; padding-top:20px;" >
					<h3 style="font-weight:bold;">비밀번호 찾기</h3>
				</div>
				<div class="pjymid" style="height:65%;">
					<input type="text" id="id" style="width:300px;height:45px;" placeholder="아이디를 입력하세요"><br><br>
					<input type="password" id="pw" style="width:300px;height:45px;" placeholder="이름을 입력하세요"><br><br>
					<input type="text" id="phone"style="width:300px;height:45px;" placeholder="휴대폰번호를 입력하세요">
					
				</div>
				<div class="pjybottom" style="height:20%; text-align:center;">
					<button type="submit">비밀번호 조회</button>
					<button id="cancel">취소</button>
				</div>
			</div>
		</form>
	</section>
</body>
</html>