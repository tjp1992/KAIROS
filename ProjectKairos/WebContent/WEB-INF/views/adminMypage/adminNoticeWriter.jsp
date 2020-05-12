<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/src/css/bootstrap.css" />
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link href="/src/css/adminMypage/adminNotice.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef:oEditors,
		elPlaceHolder:"weditor",
		sSkinURI: "/editor/SmartEditor2Skin.html",
		
			htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer : true, 
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseModeChanger : true, 
			}, 
			fCreator: "createSEditor2"
		});
	
	function submitContents(elClickedObj) {
	    // 에디터의 내용이 textarea에 적용된다.
	    oEditors.getById["weditor"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	 
	    // 에디터의 내용에 대한 값 검증은 이곳에서
	    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
	  
	    try {
	        elClickedObj.form.submit();
	    } catch(e) {
	     
	    }
	}
	 
	// textArea에 이미지 첨부
	function pasteHTML(filepath){
	    var sHTML = '<img src="<%=request.getContextPath()%>/editor/storage/'+filepath+'">';
	    oEditors.getById["weditor"].exec("PASTE_HTML", [sHTML]);
	}
});	
</script>

<script>
$(function(){
	$("#back").click(function(){
		location.href = "/adminNotice";
	});	
});

</script>
<style>
	.h2{
		font-size : 30px;
		margin-top: 30px;
		margin-bottom: 30px;
	}
	.bbt{	
		background-color: black;
		color : white;
	}
	.bbt:hover{
		background-color: #E2E2E2;
		color : black;
	}
	
	td{
		text-align:left;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<section>
		<h2>공지사항 등록</h2>
		<form action="/insertNotice" method="post" enctype="multipart/form-data" id="insertFrm">
			<table class="table table-bordered">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" class="form-control" name="noticeTitle">
					</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td>
						${sessionScope.member.memberId }
						<input type="hidden" name="noticeWriter" value="${sessionScope.member.memberId }">
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="noticeContent" rows="5" cols="90" style="width:100%;" name="weditor" id="weditor"></textarea>
					</td>
				</tr>
				<tr style="text-align: center;">
					<th colspan="2">
						<button type="submit" class="btn btn-default bbt" id="insert">등록</button>
						<button type="submit" class="btn btn-default bbt" id="modify">수정</button>
						<button type="submit" class="btn btn-default bbt" id="delete">삭제</button>
						<button class="btn btn-default bbt" id="back">돌아가기</button>
					</th>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>