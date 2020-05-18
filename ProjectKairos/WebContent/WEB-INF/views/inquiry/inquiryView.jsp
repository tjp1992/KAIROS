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
<link href="/src/css/userMypage/userInquiry.css" rel="stylesheet" type="text/css">
</head>
	<script>
		$(function(){
			$("#modify").click(function(){
				location.href="/modifyInquiryViewFrm";
			});
		});
	</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
		<h2>게시글 상세보기</h2>
		<form action="/insertInquiry" method="post" enctype="multipart/form-data" id="insertFrm">
         <table style="width:800px; height:600px;">
	          <tr>
	             <th style="width:300px;">제목</th>
	             <td><span>${i.inqTitle }</span><input type="hidden" class="form-control" name="inquiryTitle" style="width:500px;" value="${i.inqTitle }"></td>
	          </tr>
	          <tr>
		          <th>작성자</th>
		          <td><span>${i.userId }</span>
		             <input type="hidden" name="inquiryWriter" value="${i.userId }"> 
		          </td>
	          </tr>
	         
	          <tr style="border-bottom:1px solid black;">
	          	<th>내용</th>
	          	<td>${i.inqContent }
	          		<textarea style="display:none;" name="inquiryContent" rows="10" cols="80">${i.inqContent }</textarea>
	          	</td>
	          </tr>
	          <tr style="text-align : center;">
	          	<td colspan="2">
	          		<button type="button" id="modify">수정하기</button>
	          		<button type="button" id="back">뒤로가기</button>
	          	</td>
	          </tr>
         </table>
      </form>
      <c:if test="${not empty sessionScope.user }">
		<div class="comment-write">
			<form action="/inquiryCommentInsert" method="post">
			<!--작성자,게시글번호,댓글레벨,댓글번호  -->
			<input type="hidden" name="noticeCommentWriter" value="${sessionScope.member.memberId }">
			<input type="hidden" name="noticeRef" value="${n.noticeNo }">
			<input type="hidden" name="noticeCommentLevel" value="1">
			<input type="hidden" name="noticeCommentRef" value="0">
				<table class="table">
					<tr>
						<td width="85%">
							<input type="text" class="form-control" name="noticeCommentContent">
						</td>
						<td width="15%">
							<button type="submit" class="btn btn-primary">등록</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		</c:if>
      <div class="comment-wrapper">
			<c:forEach items="${list }" var="nc">
			<c:if test="${nc.noticeCommentLevel eq 1 }">
				<ul class="commentList">
					<li style="width:10% ;text-align:center;">
						<span>${nc.noticeCommentWriter }</span>
					</li>
					<li style="width:60%">
						<input type="text" class="form-control" name="noticeCommentContent" value="${nc.noticeCommentContent }" style="display:none;">
						<span>${nc.noticeCommentContent }</span>
					</li>
					<li style="width:10%; text-align:center;">
						<span>${nc.noticeCommentDate }</span>
					</li>
					<li style="width:20%; text-align:center;">
						<c:if test="${not empty sessionScope.member }">
							<a href="javascript:void(0)" onclick="insertComment(this, '${nc.noticeCommentNo}','${n.noticeNo }','${sessionScope.member.memberId }')">댓글달기</a>
							<c:if test="${sessionScope.member.memberId == nc.noticeCommentWriter }">
								<a href="javascript:void(0)" onclick="modifyComment(this,'${nc.noticeCommentNo }','${nc.noticeRef }')">수정</a>
								<a href="javascript:void(0)" onclick="deleteComment('${nc.noticeCommentNo}','${nc.noticeRef }')">삭제</a>
							</c:if>
						</c:if>
					</li>
				</ul>
				</c:if>
				<c:forEach items="${list }" var="ncc">
					<c:if test="${ncc.noticeCommentLevel eq 2 && nc.noticeCommentNo eq ncc.noticeCommentRef}">
						<ul class="commentList">
							<li style="width:5%; text-align:center;">
								<span>└─</span>
							</li>
							<li style="width:10%; text-align:center;">
								<span>${ncc.noticeCommentWriter }</span>
							</li>
							<li style="width:55%">
							<input type="text" class="form-control" name="noticeCommentContent" value="${ncc.noticeCommentContent }" style="display:none;">
								<span>${ncc.noticeCommentContent }</span>
							</li>
							<li style="width:10%; text-align:center;">
								<span>${ncc.noticeCommentDate }</span>
							</li>
							<li style="width:20%; text-align:center;">
								<c:if test="${not empty sessionScope.member && sessionScope.member.memberId eq ncc.noticeCommentWriter }">
									<a></a>
									<a href="javascript:void(0)" onclick="modifyComment(this,'${ncc.noticeCommentNo }','${ncc.noticeRef }')">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment('${ncc.noticeCommentNo}','${ncc.noticeRef }')">삭제</a>
								</c:if>
							</li>
						</ul>
					</c:if>
				</c:forEach>
			</c:forEach>
		</div>
	</section>
</body>
</html>