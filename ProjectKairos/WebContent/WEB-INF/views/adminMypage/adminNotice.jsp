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
<script>
	$(function(){
		$("#back").click(function(){
			location.href="/adminMypage";
		});
		$("#write").click(function(){
			location.href="/adminNoticeWriteFrm";
		});
	})
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<section>
			<h2>공지사항</h2>
			<div style="text-align: right">
				<button type="button" class="btn btn-info" id="write">글쓰기</button>
			</div>
	<table class="table table-hover">
      <thead>
        <tr>
          <th>번호</th>
          <th class="th2">제목</th>
          <th class="th1" colspan="2"">등록일</th>
        </tr>
      </thead>
      <tbody>
        <tr class="move">
          <th scope="row">1</th>
          <td>Mark</td>
          <td class="td3">2020-04-05</td>
          <td class="td3"><button>삭제</button></td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>Jacob</td>
          <td class="td3">2020-04-05</td>
          <td class="td3"><button>삭제</button></td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td>Larry</td>
          <td class="td3">2020-04-05</td>
          <td class="td3"><button>삭제</button></td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td>Larry</td>
          <td class="td3">2020-04-05</td>
          <td class="td3"><button>삭제</button></td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td>Larry</td>
          <td class="td3">2020-04-05</td>
          <td class="td3"><button>삭제</button></td>
        </tr>
      </tbody>
    </table>
    		<div style="text-align: right">
				<button type="button" class="btn btn-default" id="back">돌아가기</button>
			</div>
    </section>
</body>
</html>