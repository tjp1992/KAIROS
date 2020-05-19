<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
<link rel="stylesheet" href="/src/css/playList/playList.css" />
<script src="https://kit.fontawesome.com/8bd2671777.js"
	crossorigin="anonymous"></script>
<!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
<title>KAIROS</title>

</head>

<body>
	<!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

	<!-- 우리 웹의 기본 틀이 될 구조입니다 -->
	<!-- section에 콘텐츠 작성하세요! -->
	<section>
	<div class="playlistoutline">
		<div class="plistname">
			플레이리스트<span class="likelistname"><a href="/likeList">좋아요리스트</a></span>
		</div>
		<div class="psearch">
			<form action="/psearchKeyword" >
				<button class="psearchBtn" type="submit">
					<img src="/src/imgs/icons/search.png">
				</button>
				<input type="text" id="keyword" name="keyword" placeholder="검색" value="${keyword }">
			</form>
		</div>
		<form id="plEditFrm">
			<input type="hidden" name="plEdit">
		</form>
		<form id="form_pl" method="post">
			<div class="tototo">
				<table>
					<tr class="pllll">
						<th width="5%"><input class="allchk" type="checkbox"></th>
						<td width="65%">
							<button type="button" id="listen_btn">듣기</button>
							<button type="button" id="delete_btn">삭제</button>
							</td>
						<th width="30%">|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
							<button type="button" onclick="plEdit();">순서변경</button>
						</th>
					</tr>
				</table>
				
			</div>
			
			<div class="toto">
				<table>
					<tr>
						<th width="5%"></th>
						<th width="5%">번호</th>
						<td width="60%">&nbsp;곡정보</td>
						<th width="10%">듣기</th>
						<th width="10%">삭제</th>
						<th width="10%">좋아요</th>
					</tr>
				</table>
			</div>
			<div class="table">
				<table>
					<c:forEach items="${list }" var="p">
						<tr class="pltr1" songNo="${p.songNo }" filepath="${p.filepath }" liked="${p.liked }">
							<th width="5%">
							<input class="plchk" type="checkbox" name="songNo" value=${p.songNo }>
							<input style="display:none;" type="checkbox" name="orderNo" value=${p.orderNo } class="ordChk" />							
							</th>
							<th width="5%" class="plsongNo">${p.orderNo }</th>
							<td width="60%">
								<div class="stitle_dhg">&nbsp;&nbsp;${p.songTitle }</div>
								<div class="subtitle">
									&nbsp;&nbsp;&nbsp;${p.songArtist }<span>&nbsp;&nbsp;|&nbsp;${p.albumName }</span>
								</div>
							</td>
							<th width="10%" class="playimg"><i
								class="iconplay far fa-play-circle"></i></th>
							<th width="10%" class="deleteimg"><i
								class="icondelete far fa-trash-alt"></i></th>
							<c:if test="${p.liked==0 }">
								<th width="10%" class="heartimg"><i style="color: black;"
									class="iconheart far fa-heart"></i></th>
							</c:if>
							<c:if test="${p.liked==1 }">
								<th width="10%" class="heartimg"><i style="color: red;"
									class="iconheart fas fa-heart"></i></th>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
	</section>
	<script>
		function plEdit(){
			var url="/plEdit";
			var title="plEdit";
			var status ="left=300px, top=50px, width=700px, height=500px, menubar=no,status=no,scroll=yes";
			var popup = window.open("",title,status);
			
			$("#plEditFrm").attr("action",url);
			$("#plEditFrm").attr("method","post");
			$("#plEditFrm").attr("target",title);
			$("#plEditFrm").submit();
		}
	
		$(function() {
			$("#listen_btn").click(function(){
				if($(".plchk:checked").length>0){
					$("#form_pl").attr("action","/frontAdd");
					$("#form_pl").submit();
				}else{
					alert("재생 할 곡을 선택해주세요!");
					return false;
				}
			});
			$("#pleditBtn").click(function(){
				$("#form_pl").attr("action","/plEdit");
				$("#form_pl").submit();
			});
			
			$(".plchk").change(function(){
			      if ($(this).prop("checked") == true) {
			        $(this).next().prop("checked", true);
			      } else {
			        $(this).next().prop("checked", false);
			      }
			});
			
			$("#delete_btn").click(function(){
				if($(".plchk:checked").length>0){
					$("#form_pl").attr("action","/dPlist");				
					$("#form_pl").submit();
				}else{
					alert("삭제할 곡을 선택해주세요!");
					return false;
				}
				
			});
			
			$(".allchk").click(function() {
				var arr = $(".plchk");
				var arr2 = $(".ordChk");
				if ($(this).prop("checked") == true) {
					for (var i = 0; i < arr.length; i++) {
						arr.eq(i).prop("checked", true);
						arr2.eq(i).prop("checked", true);
					}
				} else {
					for (var i = 0; i < arr.length; i++) {
						arr.eq(i).prop("checked", false);
						arr2.eq(i).prop("checked", false);
					}
				}
			});

			$(".plchk").click(function() {
				if ($(".plchk").length == $(".plchk:checked").length) {
					$(".allchk").prop("checked", true);
				} else {
					$(".allchk").prop("checked", false);
				}
			});
			$(".playimg").children().click(function(){
				var songNo = $(this).parent().parent().attr("songNo");
				var orderNo = $(this).parent().parent().children().eq(1).html();
				$.ajax({
					url :"/frontAddOne",
					type:"POST",
					data:{
						songNo:songNo,orderNo:orderNo
					},
					success:function(data){
						var result = Number(data);
						if(result>0){
							location.href="/playList";
							window.open("/player","","width=366px , height=650px , resizable=false");
						}
					}
				});
			});
			$(".deleteimg").children().click(function(){
				var songNo = $(this).parent().parent().attr("songNo");
				var orderNo= $(this).parent().parent().children().eq(1).html();
				var icon = $(this);
				$.ajax({
					url : "/deletePlist",
					type: "POST",
					data: {
						songNo:songNo,orderNo:orderNo
					},
					success:function(data){
						var result = Number(data);
						if(result>0){
							icon.parent().parent().remove();
							alert("삭제성공");
							location.href="/playList";
						}
						
							
						}
				});
			});

			$(".heartimg").children().click(function() {
				var songNo = $(this).parent().parent().attr("songNo");
				var icon = $(this);
				$.ajax({
					url : "/checkLike",
					type : "POST",
					data : {
						songNo : songNo
					},
					success : function(data) {
						var result = Number(data);
						switch (result) {
						case -1://db조작에 실패했을때
							break;
						case 0://0은  빈하트에서 빨간하트로      			
							icon.attr("class", "iconheart fas fa-heart");
							icon.css("color", "red");
							break;
						case 1: //빨간하트에서 빈하트로
							icon.attr("class", "iconheart far fa-heart");
							icon.css("color", "black");
							break;
						}
					},
					error : function() {
					}

				});

			});
			$(".pltr1").hover(function() {
				$(this).css("background-color", "#F2F6FC");
			}, function() {
				$(this).css("background-color", "white");
			});
			
		});
	</script>

	<!-- ↓↓ JS 파일 추가시 이곳에 ↓↓
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
</body>

</html>
