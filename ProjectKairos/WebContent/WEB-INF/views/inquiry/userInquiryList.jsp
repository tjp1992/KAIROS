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
<link href="/src/css/userMypage/userInquiryList.css" rel="stylesheet" type="text/css">
<script>
   $(function(){
      $("#write").click(function(){
         location.href="/userInquiryFrm";
      });
      $("#back").click(function() {
		location.href="/mypageFrm";
	});
   });
</script>
<style>
	#pageNavi {
	text-align: center;
	width: 1000px;
	margin: 0 auto;
}

#pageNavi>* {
	margin: 0 auto;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<section>
         <h2>내 문의내역</h2>
         <div style="text-align: right">
            <button type="button" class="btn btn-info" id="write">글쓰기</button>
         </div>
   <table class="table table-hover">
      <thead>
        <tr>
          <th class="th2">번호</th>
          <th class="th2">제목</th>
          <th class="th2">등록일</th>
          <th class="th2">답변상태</th>
          <th class="th2">수정/삭제</th>
        </tr>
      </thead>
      <tbody>
      <c:if test="${empty list }">
			<tr>
				<th colspan="5" class="th1">문의 내역이 없습니다.</th>
			</tr>
		</c:if>
      <c:if test="${not empty list }">
        	<c:forEach items="${list }" var="i" varStatus="status">
        	<form action="/inquiryDelete" method="post">
				<tr>
					<td>${status.count }</td>
					<td><input type="hidden" name="inqNo" value=${i.inqNo }>
						<a href="/myInquiryView?inqNo=${i.inqNo }">${i.inqTitle }</a></td>
					<td>${i.inqDate }</td>
					<td>
						<c:if test="${i.inqAnsNo == 0 }">
						<span style="color:red;">답변대기</span>
						</c:if>
						<c:if test="${i.inqAnsNo != 0 }">
						<span style="color:green;">답변완료</span>
						</c:if>
					</td>
					<td><button type="submit" id="delete">삭제</button></td>
				</tr>
			</form>
			</c:forEach>
		</c:if>
      </tbody>
    </table>
    	<div id="pageNavi">${pageNavi }</div>
          <div style="text-align: right">
            <button type="button" class="btn btn-default" id="back">돌아가기</button>
         </div>
    </section>
</body>
</html>