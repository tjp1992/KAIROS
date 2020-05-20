<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Insert title here</title>
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-3.3.1.js"
    ></script>
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <script type="text/javascript" src="/src/js/bootstrap.js"></script>
    <link
      href="/src/css/adminMypage/adminNoticeDetail.css"
      rel="stylesheet"
      type="text/css"
    />
    <script>
      $(function(){
      	$("#back").click(function(){
      		location.href = "/adminQuestion?reqPage="+${req }+"&check=1"+"&reqPage2="+${req2 };
      	});
      	$("#answer").click(function(){
      		location.href = "/adminQuestionAnswer?inqNo="+${iq.inqNo }+"&reqPage="+${req }+"&reqPage2="+${req2 };
      	});
      });
    </script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <section>
      <h2>Q&A</h2>
      <div class="top">
        <span class="inner1">제목 : ${iq.inqTitle }</span>
        <span class="inner3">작성자 : ${iq.userId }</span>
        <span class="inner2">작성일 : ${iq.inqDate }</span>
      </div>
      <div class="middle">
        <p class="content">
          ${iq.inqContent }
        </p>
      </div>
      <div class="bottom">
        <button type="button" class="btn btn-default bbt" id="answer">
          답변하기
        </button>
        <button type="button" class="btn btn-default bbt" id="back">
          돌아가기
        </button>
      </div>
      <br />
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>
