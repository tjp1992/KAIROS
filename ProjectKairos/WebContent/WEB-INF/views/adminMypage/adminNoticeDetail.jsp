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
<script type="text/javascript" src="/src/js/bootstrap.js"></script>
<link href="/src/css/adminMypage/adminNoticeDetail.css" rel="stylesheet" type="text/css">

<script>
	$(function(){
		$("#back").click(function(){
			location.href = "/adminNotice?reqPage="+${req };
		});
		$("#modify").click(function(){
			location.href = "/modifyNoticeFrm?noticeNo="+${n.noticeNo }+"&reqPage="+${req };
		});
	});
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
	
	<div class="top">
		<span class="inner1">제목  : ${n.noticeTitle }</span>

		<span class="inner2">작성일  : ${n.noticeDate } </span>
	</div>
	<div class="middle">
		<p class="content">
			${n.noticeContent }
		</p>
	</div>
	
	<div class="bottom">
		<c:if test="${sessionScope.user.userId == 'admin' }">
		<button type="button" class="btn btn-default bbt" id="modify">수정</button>
		<button type="button" class="btn btn-default bbt" id="delete" onclick="deleteNotice('${n.noticeNo}',event,${req })">삭제</button>
		</c:if>
		<button type="button" class="btn btn-default bbt" id="back">목록으로</button>
	</div>
	<br>
	</section>
</body>
</html>