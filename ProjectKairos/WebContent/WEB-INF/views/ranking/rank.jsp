<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/js/bootstrap.js"></script>
	<link rel="stylesheet" href="/src/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="/src/css/voucher/voucher.css">
	<script src="/src/js/jquery-3.3.1.js"></script>
<title>Insert title here</title>
</head>
<script>
	
</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<section class="container">
	<hr>
		<div class="row m-4">
			<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
					<h1 class="h2">Kairos 순위차트</h1>
			</div>
		</div>
			<hr>
			<div class="table-responsive">
				<table class="table table-striped table-sm text-center">
					<thead>
						<tr>
							<th width="10%">
								<input type="checkbox" id="checkAll">
							</th>
							<th width="10%">순위</th>
							<th width="30%">곡 제목</th>
							<th width="20%">앨범</th>
							<th width="10%">좋아요</th>
							<th width="10%">재생 횟수</th>
							<th width="10%">듣기</th>
						</tr>
						<c:forEach items="${list }" var="m">
							<tr><input type="checkbox" id="select"></tr>
							<tr>m.getRankNo</tr>
							<tr>m.getSongTitle</tr>
							<tr>m.getAlbumNo</tr>
							<tr>m.getLikeCount</tr>
							<tr>m.getPlayCount</tr>
							<tr><input type="checkbox"></tr>
						</c:forEach>
					</thead>
					<tbody></tbody>
				</table>
			</div>
	</section>
</body>
</html>