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
		var check = ${check };
		
		if(check == 2){
			$('#tt2').addClass("active");
			$('#tt1').removeClass("active");
			$('#profile').addClass("active in");
			$('#home').removeClass("active in");
		}else{
			$('#tt1').addClass("active");
			$('#tt2').removeClass("active");
			$('#home').addClass("active in");
			$('#profile').removeClass("active in");
		}
		
		$('#myTab').children('a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		});
		$("#back").click(function() {
			location.href = "/adminMypage";
		});
		$("#sear").click(function() {
			var search = $("#search").val();
			location.href = "/adminQuestion?reqPage="+${reqPage }+"&check="+${check }+"&reqPage2="+${reqPage2 }+"&search="+search;
		});
		$("#search").keydown(function(key){
			if(key.keyCode == 13){
				$("#sear").click();	
			}
		});
	});
	function detail(no, reqPage){ // req 는 해당 페이지이다.
		location.href = "/adminQuestionDetailFrm?inqNo="+no+"&reqPage="+reqPage+"&check="+${check }+"&reqPage2="+${reqPage2 };
	}
	function detail2(no, reqPage2){
		location.href = "/adminQuestionEndDetailFrm?inqNo="+no+"&reqPage2="+reqPage2+"&check="+${check }+"&reqPage="+${reqPage };
	}
	
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
	<h2>고객센터</h2>

	<div class="bs-example bs-example-tabs" role="tabpanel"
		data-example-id="togglable-tabs">
		
			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li id="tt1" role="presentation" class="active"><a href="#home"
					id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="true"><b>답변 대기</b></a></li>
				<li id="tt2" role="presentation" class=""><a href="#profile" role="tab"
					id="profile-tab" data-toggle="tab" aria-controls="profile"
					aria-expanded="false"><b>답변 완료</b></a></li>

			</ul>
		
		<div id="myTabContent" class="tab-content">
			<div role="tabpanel" class="tab-pane fade active in" id="home"
				aria-labelledby="home-tab">
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
							<tr class="move" id="move" onclick="detail(${p.inqNo },${reqPage })">
								<th scope="row" class="num">${(reqPage-1)*10 + i.count }</th>
								<td>${p.userId }</td>
								<td class="title">${p.inqTitle }</td>
								<td class="insertdate">${p.inqDate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav id="footNav2">
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
							<th class="th2">등록일</th>
							<th class="th2">답변일</th> 
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list2 }" var="p" varStatus="i">
							<tr class="move" id="move2" onclick="detail2(${p.inqNo },${reqPage2 })">
								<th scope="row" class="num">${(reqPage2-1)*10 + i.count }</th>
								<td>${p.userId }</td>
								<td class="title">${p.inqTitle }</td>
								<td class="insertdate">${p.inqDate }</td>
								<td>${p.inqAnsDate }</td>
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
				<c:if test="${not empty search }">
						<input type="text" class="form-control " aria-label="..." id="search" value="${search }"> <span class="glyphicon glyphicon-search" id="sear"></span>
				</c:if>
				<c:if test="${empty search }">
						<input type="text" class="form-control " aria-label="..." id="search"> <span class="glyphicon glyphicon-search" id="sear"></span>
				</c:if>
			</div>
			<!-- /input-group -->

		</div>
	</div>
	<!-- /.col-lg-6 -->
	<div id="sel">
		<button type="button" class="btn btn-default" id="back">돌아가기</button>
	</div>

	</section>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>