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

<link rel="stylesheet" href="/src/css/bootstrap.css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link href="/src/css/adminMypage/adminQuestion.css" rel="stylesheet"
	type="text/css">
<script>
	$(function() {
		$('#myTab').children('a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		});
		$("#back").click(function() {
			location.href = "/adminMypage";
		});
		$("#move").click(function() {
			location.href = "/adminQuestionDetailFrm";
		});
		$("#move2").click(function() {
			location.href = "/adminQuestionEndDetailFrm";
		})
	});
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
	<h2>고객센터</h2>

	<div class="bs-example bs-example-tabs" role="tabpanel"
		data-example-id="togglable-tabs">
		<ul id="myTab" class="nav nav-tabs" role="tablist">
		<c:if test="${check == 1}">
			<li role="presentation" class="active">
			<a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true"><b>답변 대기</b></a></li>
			<li role="presentation" class=""><a href="#profile" role="tab"
				id="profile-tab" data-toggle="tab" aria-controls="profile"
				aria-expanded="false"><b>답변 완료</b></a></li>
		</c:if>
		<c:if test="${check == 2}">
			<li role="presentation" class="">
			<a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="false"><b>답변 대기</b></a></li>
			<li role="presentation" class="active">
			<a href="#profile"  role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="true"><b>답변 완료</b></a></li>
		</c:if>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="num">번호</th>
							<th class="id">id</th>
							<th class="th2">제목</th>
							<th class="th1" colspan="2">등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="p" varStatus="i">
							<tr class="move" id="move"
								onclick="detail(${n.noticeNo},${req })">
								<th scope="row" class="num">${(req-1)*10 + i.count }</th>
								<td>${p.userId }</td>
								<td class="title">${p.inqTitle }</td>
								<td class="insertdate">${p.inqDate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav id="footNav">
					<ul class="pagination">${pageNavi }</ul>
				</nav>
			</div>

			<div role="tabpanel" class="tab-pane fade" id="profile"
				aria-labelledby="profile-tab">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="num">번호</th>
							<th class="id">id</th>
							<th class="th2">제목</th>
							<th class="th1" colspan="2">등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list2 }" var="p" varStatus="i">
							<tr class="move" id="move"
								onclick="detail(${n.noticeNo},${req })">
								<th scope="row" class="num">${(req-1)*10 + i.count }</th>
								<td>${p.userId }</td>
								<td class="title">${p.inqTitle }</td>
								<td class="insertdate">${p.inqDate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav id="footNav2">
					<ul class="pagination">${pageNavi2 }</ul>
				</nav>
			</div>

		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">id</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">id</a></li>
					</ul>

				</div>
				<!-- /btn-group -->
				<input type="text" class="form-control " aria-label="..."
					id="search"> <span class="glyphicon glyphicon-search"
					id="sear"></span>
			</div>
			<!-- /input-group -->

		</div>
	</div>
	<!-- /.col-lg-6 -->
	<div style="text-align: right">
		<button type="button" class="btn btn-default" id="back">돌아가기</button>
	</div>

	</section>
</body>
</html>