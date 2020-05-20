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
<script src="https://kit.fontawesome.com/8bd2671777.js"
	crossorigin="anonymous"></script>
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
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<section class="container text-center">
		<hr>
		<div class="row m-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
				<h1 class="h2">
					<img src="/src/imgs/logo/main_logo(no).png" width="250px">
					순위차트
				</h1>
			</div>
		</div>
		<hr>
		<div class="row m-2">
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
							<th width="10%"><input type="checkbox" id="checkAll"><small><label
									for="checkAll">전체선택</label></small></th>
							<th width="10%"><a class="btn btn-primary" id="chkPlayNow">선택 듣기</a></th>
							<th width="30%"></th>
							<th width="20%"></th>
							<th width="10%"></th>
							<th width="10%"></th>
							<th width="10%"></th>
						</tr>
						<tr>
							<th width="10%"></th>
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
								<td><input type="hidden" value=${m.songNo } id="songNo"
									name="songNo"> <input type="checkbox" id="select" value="${m.songNo}"
									class="check chkBox">
								</td>
								<td>${m.rankNo }</td>
								<td>${m.songTitle }<br>${m.songArtist }</td>
								<td>${m.albumName }</td>

								<c:if test="${m.liked==0 }">
									<th width="10%" class="heartimg"><i style="color: black;"
										class="iconheart far fa-heart likeBtn" songNo="${m.songNo}"></i>${m.likeCount }</th>
								</c:if>
								<c:if test="${m.liked>0 }">
									<th width="10%" class="heartimg"><i style="color: red;"
										class="iconheart fas fa-heart likeBtn" songNo="${m.songNo}"></i>${m.likeCount}</th>
								</c:if>

								<td>${m.playCount }</td>
								<td><button class="btn btn-primary btn-sm playBtn" songNo="${m.songNo}">듣기</button></td>
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
		<div class="row page_nav_wrapper">
			<nav aria-label="navigation" id="pageNavi">
				<ul class="pagination">${pageNavi}
				</ul>
			</nav>
		</div>
	</section>
	<!-- 버튼 기능 -->
	<script>
		
		$(".play").click(function() {
			console.log($(this).parent().parent().find('#songNo').val());
		})
		$('#checkAll').click(function() {
			if ($('#checkAll').prop('checked')) {
				$('.check').each(function() {
					$(this).attr('checked', true);
				});
			} else {
				$('.check').each(function() {
					$(this).attr('checked', false);
				});
			}
		});
	</script>
	<script>
		
        // 플레이 나우
        $("#chkPlayNow").click(function () {
          let songNo = "";
          const chks = $(".chkBox:checked");

          for (let i = 0; i < chks.length; i++) {
            songNo += chks[i].value;
            if (i != chks.length - 1) {
              songNo += ",";
            }
          }

          $.ajax({
            url: "/asyncAddPlayList",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result > 0) {
				window.open("/player","","width=366px , height=650px , resizable=false");
              } else {
                alert("추가를 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 연결에 실패하엿습니다.");
            },
          });
        });


        // 한곡 플레이
        $(".playBtn").click(function () {
          const songNo = $(this).attr("songNo");

          $.ajax({
            url: "/asyncAddPlayFirst",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result > 0) {
                window.open("/player","","width=366px , height=650px , resizable=false");
              } else {
                alert("서버 접속에 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 접속에 실패하였습니다.");
            },
          });
        });


        $(".likeBtn").click(function () {
          // click된 element가 i 태그가 아니면 수정필요
          const btn = $(this);

          const songNo = $(this).attr("songNo");

          // countSpan은 좋아요 카운트를 출력해주는 element
          const countSpan = $(this).next();
          const count = Number(countSpan.html());

          $.ajax({
            url: "/asyncSearchLike",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result == 0) {
                btn.removeClass();
                btn.addClass("fas fa-heart likeBtn");
                btn.css("color", "red");
                countSpan.html(count + 1);
              } else if (result == 1) {
                btn.removeClass();
                btn.addClass("far fa-heart likeBtn");
                btn.css("color", "black");
                countSpan.html(count - 1);
              } else {
                alert("서버 접속에 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 접속에 실패하였습니다.");
            },
          });
        });
	</script>
</body>
</html>