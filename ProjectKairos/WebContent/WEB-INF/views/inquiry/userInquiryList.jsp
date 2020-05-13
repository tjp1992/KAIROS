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
          <th>번호</th>
          <th class="th2">제목</th>
          <th class="th1" colspan="2">등록일</th>
          <th>답변상태</th>
        </tr>
      </thead>
      <tbody>
      <c:if test="${empty list }">
			<tr>
				<th colspan="5">문의 내역이 없습니다.</th>
			</tr>
		</c:if>
      <c:if test="${not empty list }">
        	<c:forEach items="${list }" var="n">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><button>수정</button><button>삭제</button></td>
				</tr>
			</c:forEach>
		</c:if>
      </tbody>
    </table>
          <div style="text-align: right">
            <button type="button" class="btn btn-default" id="back">돌아가기</button>
         </div>
    </section>
</body>
</html>