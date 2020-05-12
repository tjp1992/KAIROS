<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link href="/src/css/adminMypage/adminNotice.css" rel="stylesheet" type="text/css">
<style>
	.h2{
		font-size : 30px;
		margin-top: 30px;
		margin-bottom: 30px;
	}
	#insert{	
		background-color: black;
		color : white;
	}
	#insert:hover{
		background-color: #E2E2E2;
		color : black;
	}
	td{
		text-align:left;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<section>
		<h2>공지사항 등록</h2>
		<form action="/insertNotice" method="post" enctype="multipart/form-data" id="insertFrm">
			<table class="table table-bordered">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" class="form-control" name="noticeTitle">
					</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td>
						${sessionScope.member.memberId }
						<input type="hidden" name="noticeWriter" value="${sessionScope.member.memberId }">
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="filename"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="noticeContent" rows="5" style="width:100%;"></textarea>
					</td>
				</tr>
				<tr style="text-align: center;">
					<th colspan="2">
						<button type="submit" class="btn btn-default" id="insert">등록</button>
					</th>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>