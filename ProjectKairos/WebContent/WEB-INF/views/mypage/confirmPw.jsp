<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-3.3.1.js"
    ></script>
    <title>Insert title here</title>
  </head>
  <style>
    #wrapper {
      margin-top: 80px;
      margin-left: 40px;
    }
  </style>
  <script>
    $(function () {
      $("#submit").click(function () {
        if ($("#pw").val() == "") {
          $("#pwMsg").html("비밀번호를 입력해주세요.");
          $("#pwMsg").css("color", "red");
        } else {
          if ($("#pw").val() != "${sessionScope.user.userPw }") {
            $("#pwMsg").html("");
            $("#pwMsg").html("비밀번호가 틀렸습니다.");
            $("#pwMsg").css("color", "red");
            return false;
          } else {
            opener.location.href = "/userModifyFrm";
            window.close();
          }
        }
      });
    });
  </script>
  <body>
    <div id="wrapper">
      <h2>비밀번호 확인</h2>
      <br />
      <form action="/confirmPw" method="post">
        <input type="password" name="pw" id="pw" />
        <button type="submit" id="submit">확인</button><br />
        <span id="pwMsg"></span>
      </form>
    </div>
  </body>
</html>
