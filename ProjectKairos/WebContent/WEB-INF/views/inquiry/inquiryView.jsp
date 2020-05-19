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
<link href="/src/css/userMypage/userInquiryView.css" rel="stylesheet" type="text/css">
</head>
	<script>
		$(function(){
			$("#back").click(function(){
				location.href="/userInquiryList?reqPage=1";
			});
		});
	</script>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
		<h2>게시글 상세보기</h2>
		<form action="/modifyInquiryViewFrm" method="post" id="insertFrm">
		<input type="hidden" name="inqNo" value=${i.inqNo }>
         <table>
	          <tr>
	             <th style="width:150px;">제목</th>
	             <td><span id="span">${i.inqTitle }</span><input type="hidden" class="form-control" name="inqTitle" style="width:500px;" value=${i.inqTitle }></td>
	          </tr>
	          <tr>
		          <th>작성자</th>
		          <td><span id="span">${i.userId }</span>
		             <input type="hidden" name="inquiryWriter" value=${i.userId }> 
		          </td>
	          </tr>
	         
	          <tr style="border-bottom:1px solid black;">
	          	<th>내용</th>
	          	<td>${i.inqContent }
	          		<textarea style="display:none;" name="inqCon" rows="10" cols="80">${i.inqContent }</textarea>
	          	</td>
	          </tr>
	          <tr style="text-align : center;">
	          	<td colspan="2">
	          		<button type="submit" id="modify">수정하기</button>
	          		<button type="button" id="back">뒤로가기</button>
	          	</td>
	          </tr>
         </table>
      </form>
      <div>
	      <table style="width:500px;">
		      	<tr style="border-bottom:1px solid black;">
		      		<td colspan="3" style="font-size:25pt;">답변</td>
		      	</tr>
		      	<tr>
		      		<td>제목</td>
		      		<td style="font-size:15pt;">${ia.inqAnsTitle }</td>
		      	</tr>
		      	<tr>
		      		<td>답변 내용</td>
		      		<td style="font-size:15pt;">${ia.inqAnsContent }</td>
		      	</tr>
		      	<tr>
		      		<td>작성일자</td>
		      		<td style="font-size:15pt;">${ia.inqAnsDate }</td>
		      	</tr>
	      </table>
		</div>
	</section>
</body>
</html>