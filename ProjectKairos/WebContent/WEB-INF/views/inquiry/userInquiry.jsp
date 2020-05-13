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
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
		<h2>1:1문의하기</h2>
		<form action="/insertInquiry" method="post" enctype="multipart/form-data" id="insertFrm">
         <table>
	          <tr>
	             <th>제목</th>
	             <td><input type="text" class="form-control" name="inquiryTitle" style="width:500px;"></td>
	          </tr>
	          <tr>
		          <th>작성자</th>
		          <td>${sessionScope.user.userId }
		             <input type="hidden" name="inquiryWriter" value="${sessionScope.user.userId }"> 
		          </td>
	          </tr>
	          <tr>
	          	<th>첨부파일</th>
	          	<td><input type="file" name ="filename"></td>
	          </tr>
	          <tr style="border-bottom:1px solid black;">
	          	<th>내용</th>
	          	<td>
	          		<textarea name="inquiryContent" rows="10" cols="80"></textarea>
	          	</td>
	          </tr>
	          <tr style="text-align : center;">
	          	<td colspan="2">
	          		<button type="submit">등록</button>
	          		<button type="reset">취소</button>
	          	</td>
	          </tr>
         </table>
      </form>
	</section>
</body>
</html>