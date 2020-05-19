<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<script type="text/javascript" src="/src/js/bootstrap.js"></script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link href="/src/css/adminMypage/adminNotice.css" rel="stylesheet" type="text/css">
<script>
	$(function() {
		$("#back").click(function() {
			location.href = "/adminMypage";
		});
		$("#write").click(function() {
			location.href = "/adminNoticeWriteFrm?reqPage=1";
		});
	})
	function detail(no, req){ // req 는 해당 페이지이다.
		location.href = "/adminNoticeDetailFrm?noticeNo="+no+"&reqPage="+req;
	}
	function deleteNotice(no,event,req){
		if (confirm("삭제하시겠습니까?")) {
			event.stopPropagation();
			location.href = "/deleteNotice?noticeNo="+no+"&reqPage="+req;
			
		}
	}
</script>
</head>
<body>
	 
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
	<h2>공지사항</h2>
	<c:if test="${sessionScope.user.userId == 'admin' }">
	<div style="text-align: right">
		<button type="button" class="btn btn-info" id="write">글쓰기</button>
	</div>
	</c:if>
	<table class="table table-hover">
		<thead>
			<tr>
				<th class="num">번호</th>
				<th class="th2">제목</th>
				<th class="th1" colspan="2">등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="n" varStatus="i">
				<tr class="move" id="move" onclick="detail(${n.noticeNo},${reqPage })" >
					<th scope="row" class="num">${(reqPage-1)*10 + i.count }</th>
					<td>${n.noticeTitle }</td>
					<td class="td2" >${n.noticeDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="navheader">
	<nav id="footNav">
		<ul class="pagination">${pageNavi }</ul>
	</nav>
	<div style="text-align: right">
		<button type="button" class="btn btn-default" id="back">돌아가기</button>
	</div>
	</div>
	

	</section>
</body>
</html>