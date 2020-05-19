<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<link href="/src/css/userMypage/userInquiry.css" rel="stylesheet" type="text/css">
</head>
	<script type="text/javascript">
	var oEditors = [];
	$(function(){
	      nhn.husky.EZCreator.createInIFrame({
	          oAppRef: oEditors,
	          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
	          //SmartEditor2Skin.html 파일이 존재하는 경로
	          sSkinURI: "/editor/SmartEditor2Skin.html",  
	          htParams : {
	              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	              bUseToolbar : true,             
	              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	              bUseVerticalResizer : true,     
	              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	              bUseModeChanger : true,         
	              fOnBeforeUnload : function(){
	                   
	              }
	          }, 
	          fOnAppLoad : function(){
	              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
	          },
	          fCreator: "createSEditor2"
	      });
	      
	      //저장버튼 클릭시 form 전송
	      $("#save").click(function(){
	          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
	          $("#frm").submit();
	      });
	      $("#back").click(function(){
	    	 location.href="/myInquiryView?inqNo=${inqNo}"; 
	      });
	});
	</script>
	<style>
	</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
		<h2>수정하기</h2>
		<form action="/modifyInquiryView" method="post" id="frm">
		<input type="hidden" name="inqNo" value=${inqNo }>
         <table>
	          <tr>
	             <th style="width:100px;">제목</th>
	             <td style="width:700px;"><input type="text" class="form-control" name="modifyInquiryTitle" style="width:580px;" value="${title }"></td>
	          </tr>
	          <tr>
		          <th>작성자</th>
		          <td>${sessionScope.user.userId }
		             <input type="hidden" name="modifyInquiryWriter" value="${sessionScope.user.userId }"> 
		          </td>
	          </tr>
	          
	          <tr style="border-bottom:1px solid black;">
	          	<th>내용</th>
	          	<td>
	          		<textarea name="modifyInquiryContent" id="ir1" style="width:610px; height:600px;">${content }</textarea>
	          	</td>
	          </tr>
	          <tr style="text-align : center;">
	          	<td colspan="2">
	          		<button type="submit" id="save">수정</button>
	          		<button type="button" id="back">취소</button>
	          	</td>
	          </tr>
         </table>
      </form>
	</section>
</body>
</html>