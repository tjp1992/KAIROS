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
    <script type="text/javascript" src="/src/js/bootstrap.js"></script>
    <link
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
      crossorigin="anonymous"
    />
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
      integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <link
      href="/src/css/adminMypage/adminmypagemain.css"
      rel="stylesheet"
      type="text/css"
    />

    <script>
      $(function () {
        $("#goNotice").click(function () {
          location.href = "/adminNotice?reqPage=1";
        });
        $("#goQuesion").click(function () {
          location.href = "/adminQuestion?reqPage=1&check=1&reqPage2=1";
        });
        $("#goTicket").click(function () {
          location.href = "/adminTicket?reqPage=1&check=1&reqPage2=1";
        });
        $("#goMusic").click(function () {
          location.href = "/manageMusicFrm";
        });
      });
    </script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <section>
      <c:if test="${sessionScope.user.userId == 'admin' }">
        <h2 class="h2">
          내 정보 <span class="span1">로그인계정 : </span>
          <span class="span1">${sessionScope.user.userId }</span>
        </h2>
        <div class="PHJup">
          <h3>관리자 정보수정</h3>
          <div class="div1">
            <table class="table t1 table-hover">
              <tr class="move" id="goTicket">
                <td scope="row"><label>이용권 관리</label></td>
                <td><span class="glyphicon glyphicon-chevron-right"></span></td>
              </tr>
              <tr class="move" id="goNotice">
                <td scope="row"><label>공지사항 관리</label></td>
                <td><span class="glyphicon glyphicon-chevron-right"></span></td>
              </tr>
            </table>
          </div>
          <div class="verticalLine"></div>
          <div class="div1">
            <table class="table t1 table-hover">
              <tr class="move" id="goMusic">
                <td scope="row"><label>음악 관리</label></td>
                <td><span class="glyphicon glyphicon-chevron-right"></span></td>
              </tr>
              <tr class="move" id="goQuesion">
                <td scope="row"><label>고객문의 관리</label></td>
                <td><span class="glyphicon glyphicon-chevron-right"></span></td>
              </tr>
            </table>
          </div>
        </div>
      </c:if>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>
