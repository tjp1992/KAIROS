<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link href="/src/css/userMypage/userModify.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
		<h2>개인정보 수정</h2>
			<form action="/userModify">
				<div class="wrapper">
					<table>
						<tr>
							<th><label for="userId">아이디</label></th>
							<td>
							<input type="text" name="userId" id="userId" class="form-control" readonly style=" width:185px;height: 35px;">
							</td>
						</tr>
						<tr>
							<th><label for="userPw">새 비밀번호</label></th>
							<td>
							<input type="password" name="userPw" id="userPw" style="height: 35px;">
							</td>
						</tr>
						<tr>
							<th><label for="userPwd">새 비밀번호 확인</label></th>
							<td>
							<input type="password" name="userPwd" id="userPwd" style="height: 35px;">
							</td>
						</tr>
						<tr>
							<th><label for="userName">이름</label></th>
							<td>
							<input type="text" name="userName" id="userName" style="height: 35px;">
							</td>
						</tr>
						<tr>
							<th><label for="userNick">닉네임</label></th>
							<td><input type="text" id="userNick" style="height: 35px;"name="userNick">
								<button type="button" id="overlapNick" style="height: 35px;">중복확인</button> <span></span></td>
						</tr>
						<tr>
							<th><label for="age">나이</label></th>
							<td>
							<input type="text" name="age" id="age" style="height: 35px;">
							</td>
						</tr>
						<tr>
							<th><label for="phone">전화번호</label></th>
							<td>
								<input type="text" id="phone" name="phone" style="height: 35px;">
								<button type="button" id="phoneResult" style="height: 35px;">인증번호 받기</button> <span></span></td>
							</td>
						</tr>
						<tr>
							<th><label for="mail1">이메일</label></th>
							<td>
								<input type="text" id="mail1" name="mail1"
								style="width: 150px; height: 35px;">
								@ <input type="text" id="mail2" name="mail2"
								style="width: 150px; height: 35px;">
								<select style="width: 100px; height: 35px;">
									<option>직접입력</option>
									<option>naver.com</option>
									<option>gmail.com</option>
									<option>daum.net</option>
								</select>
							</td>
						</tr>
						<tr style="border-bottom:1px solid black;">
							<th><label for="addrSearch">주소</label></th>
							<td><input type="text" id="postCode" name="postCode" style="width: 200px; height: 35px; display: inline-block" placeholder="우편번호" readonly> 
								<input type="text" id="roadCode" name="roadCode" style="width: 200px; height: 35px;" placeholder="도로명주소" readonly>
								<button type="button" id="addrSearch" style="height: 35px;">주소검색</button> <br> 
								<input id="detailAddr" name="detailAddr" style="width: 400px; height: 35px;" type="text" placeholder="상세주소">
								<span></span>
							</td>
						</tr>
						<tr>
					<td colspan="2" style="text-align: center;">
					<br>
						<button type="submit" style="width: 150px; height: 50px;">수정하기</button>
						<button type="button" id="cancel" style="width: 150px; height: 50px;">메인페이지로</button>
					</td>
					</tr>
					</table>	
				</div>
			</form>
	</section>
</body>
</html>