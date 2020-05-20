<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-3.3.1.js"
    ></script>
    <link href="/src/css/login/login.css" rel="stylesheet" type="text/css" />
  </head>
  <script>
    $(document).ready(function () {
      // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
      var key = getCookie("key");
      $("#id").val(key);

      if ($("#id").val() != "") {
        // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
        $("#idSave").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
      }

      $("#idSave").change(function () {
        // 체크박스에 변화가 있다면,
        if ($("#idSave").is(":checked")) {
          // ID 저장하기 체크했을 때,
          setCookie("key", $("#id").val(), 7); // 7일 동안 쿠키 보관
        } else {
          // ID 저장하기 체크 해제 시,
          deleteCookie("key");
        }
      });

      // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
      $("#id").keyup(function () {
        // ID 입력 칸에 ID를 입력할 때,
        if ($("#idSave").is(":checked")) {
          // ID 저장하기를 체크한 상태라면,
          setCookie("key", $("#id").val(), 7); // 7일 동안 쿠키 보관
        }
      });
    });

    function setCookie(cookieName, value, exdays) {
      var exdate = new Date();
      exdate.setDate(exdate.getDate() + exdays);
      var cookieValue =
        escape(value) +
        (exdays == null ? "" : "; expires=" + exdate.toGMTString());
      document.cookie = cookieName + "=" + cookieValue;
    }

    function deleteCookie(cookieName) {
      var expireDate = new Date();
      expireDate.setDate(expireDate.getDate() - 1);
      document.cookie =
        cookieName + "= " + "; expires=" + expireDate.toGMTString();
    }

    function getCookie(cookieName) {
      cookieName = cookieName + "=";
      var cookieData = document.cookie;
      var start = cookieData.indexOf(cookieName);
      var cookieValue = "";
      if (start != -1) {
        start += cookieName.length;
        var end = cookieData.indexOf(";", start);
        if (end == -1) end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
      }
      return unescape(cookieValue);
    }
  </script>
  <body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <section class="cantainer">
      <form action="/login" method="post">
        <div class="wrapper">
          <div
            class="pjytop"
            style="height: 20%; text-align: center; margin-top: 10px;"
          >
            <a href="#"
              ><img
                src="/src/imgs/logo/main_logo.png"
                style="width: 50%; height: 100%;"
            /></a>
          </div>
          <div class="pjymid" style="height: 45%;">
            <input
              type="text"
              name="id"
              id="id"
              style="width: 300px; height: 45px;"
              placeholder="아이디를 입력하세요"
            />
            <input
              type="checkbox"
              id="idSave"
              style="width: 15px; height: 15px; margin-left: 5px;"
            />아이디 저장<br /><br />
            <input
              type="password"
              name="pw"
              id="pw"
              style="width: 300px; height: 45px;"
              placeholder="비밀번호를 입력하세요"
            /><br /><br />
          </div>
          <div style="height: 15%; text-align: center;">
            <a href="/idSearchFrm">아이디찾기</a>/<a href="/pwSearchFrm"
              >비밀번호 찾기</a
            >
          </div>
          <div class="pjybottom" style="height: 20%; text-align: center;">
            <button type="submit">로그인</button>
            <button type="button" id="cancel">취소</button>
          </div>
        </div>
      </form>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
  <script>
    $("#cancel").click(function () {
      location.href = "/index.jsp";
    });
  </script>
</html>
