<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/src/css/voucher/voucher.css">
<script src="/src/js/jquery-3.3.1.js"></script>
<title>Top Chart</title>
</head>
<style>
#pageNavi {
	text-align: center;
	margin: 0 auto;
}

#pageNavi>* {
	margin: 10px;
}
</style>
<script>
	$('#all').change(function(e) {
		if (e.currentTarget.checked) {
			$('.rows').find('input[type="checkbox"]').prop('checked', true);
		} else {
			$('.rows').find('input[type="checkbox"]').prop('checked', false);
		}
	});
</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<section class="container text-center">
		<hr>
		<div class="row m-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
				<h1 class="h2">Kairos 순위차트</h1>
			</div>
		</div>
		<hr>
		<div class="row">
			<ul class="nav">
				<li class="nav-item"><a class="nav-link"
					href="/rankingFrm?reqPage=1&reqType=song" id="song">음악 차트</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/rankingFrm?reqPage=1&reqType=album" id="album">앨범 차트</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/rankingFrm?reqPage=1&reqType=artist" id="artist">아티스트 차트</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/rankingFrm?reqPage=1&reqType=play">플래이 차트</a></li>
			</ul>
		</div>
		<div class="table-responsive">
			<table class="table table-striped table-sm text-center">
				<c:if test="${reqType == 'song' or reqType == 'play'}">
					<thead>
						<tr>
							<th width="10%"><input type="checkbox" id="checkAll"><small>전체선택</small>
							</th>
							<th width="10%">순위</th>
							<th width="30%">곡 제목</th>
							<th width="20%">앨범</th>
							<th width="10%">좋아요</th>
							<th width="10%">재생 횟수</th>
							<th width="10%">듣기</th>
						</tr>
					</thead>
					<tbody class="rows">
						<c:forEach items="${list}" var="m">
							<tr>
								<td><input type="checkbox" id="select"></td>
								<td>${m.rankNo }</td>
								<td>${m.songTitle }<br>${m.songArtist }</td>
								<td>${m.albumName }</td>
								<td>${m.likeCount }</td>
								<td>${m.playCount }</td>
								<td><button class="btn btn-primary btn-sm">듣기</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
				<c:if test="${reqType eq 'artist'}">
					<thead>
						<tr>
							<th width="15%">순위</th>
							<th width="25">아티스트</th>
							<th width="20%">좋아요</th>
							<th width="15%">재생 횟수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="m">
							<tr>
								<td>${m.rankNo}</td>
								<td>${m.songArtist}</td>
								<td>${m.likeCount}</td>
								<td>${m.playCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
				<c:if test="${reqType eq 'album' }">
					<thead>
						<tr>
							<th width="15%">순위</th>
							<th width="30%">앨범</th>
							<th width="20%">아티스트</th>
							<th width="20%">좋아요</th>
							<th width="15%">재생 횟수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="m">
							<tr>
								<td>${m.rankNo }</td>
								<td>${m.albumName }</td>
								<td>${m.songArtist }</td>
								<td>${m.likeCount }</td>
								<td>${m.playCount }</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
		</div>
		<div class="row">
			<nav aria-label="navigation" id="pageNavi">
				<ul class="pagination">${pageNavi}
				</ul>
			</nav>
		</div>
	</section>
</body>
</html>