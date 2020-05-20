<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
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
      			location.href = "/adminQuestion?reqPage=1&check=2&reqPage2="+${reqPage2 };
      		});
      		$("#modify").click(function(){
      			location.href = "/adminQuestionAnswerModify?inqAnsNo="+${iqa.inqAnsNo }+"&reqPage="+${reqPage }+"&inqNo="+${iq.inqNo }+"&reqPage2="+${reqPage2 };
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
      <div class="bottom"></div>
      <c:if test="${not empty iqa }">
        <h2>ANSWER</h2>
        <div class="top">
          <span class="inner1">제목 : ${iqa.inqAnsTitle }</span>
          <span class="inner2">작성일 : ${iqa.inqAnsDate } </span>
        </div>
        <div class="middle">
          <p class="content">
            ${iqa.inqAnsContent }
          </p>
        </div>
        <div class="bottom">
          <button type="button" class="btn btn-default bbt" id="modify">
            답변 수정
          </button>
          <button type="button" class="btn btn-default bbt" id="back">
            목록으로
          </button>
        </div>
      </c:if>
      <br />
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>
