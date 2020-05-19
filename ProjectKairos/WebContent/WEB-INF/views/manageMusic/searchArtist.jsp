<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <script type="text/javascript" src="/src/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <link rel="stylesheet" href="/src/css/manageMusic/searchArtist.css" />
    <title>아티스트 검색</title>
  </head>
  <body>
    <form action="/searchArtist" method="POST">
      <div>
        <input
          type="text"
          name="artistName"
          id="artistName"
          class="form-control"
          placeholder="검색할 가수명을 입력하세요"
        />
        <button class="btn btn-primary btn-sm" type="submit">검색</button>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th>No.</th>
            <th>가수명</th>
            <th>소속사</th>
          </tr>
        </thead>
        <c:forEach items="${list }" var="l" varStatus="status">
          <tr phone="${l.lcnAgentPhone}" agentName="${l.lcnAgentName}">
            <td>${status.count}</td>
            <td>${l.lcnArtistName}</td>
            <td>${l.lcnCompany}</td>
          </tr>
        </c:forEach>
      </table>
    </form>
  </body>
  <script>
    $(function () {
      $("#artistName").focus();
    });

    $("tr")
      .not($("thead>tr"))
      .click(function () {
        const artistName = $(this).children().eq(1).html();
        const company = $(this).children().eq(2).html();
        const agentName = $(this).attr("agentName");
        const phone = $(this).attr("phone");
        const input = opener.document.getElementById("artist");
        const inputCompany = opener.document.getElementById("lcn_company");

        if (inputCompany == null) {
          input.value = artistName;
          opener.readAlbums();
        } else {
          const inputAgentName = opener.document.getElementById(
            "lcn_agent_name"
          );
          const inputAgentPhone = opener.document.getElementById(
            "lcn_agent_phone"
          );

          input.value = artistName;
          inputCompany.value = company;
          inputAgentName.value = agentName;
          inputAgentPhone.value = phone;
          opener.document.getElementById("update").value = 1;
        }

        self.close();
      });
  </script>
</html>
