<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <script src="/src/js/8bd2671777.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <link rel="stylesheet" href="/src/css/manageMusic/manageArtist.css" />
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
  </head>
  <body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
      <form action="/manageArtist" method="POST">
        <div class="insert_wrapper">
          <input type="hidden" id="update" name="update" value="0" />
          <div class="input_artist cont">
            <span>가수명</span>
            <input type="text" name="artist" id="artist" />
            <button type="button" id="search_artist">검색</button>
          </div>
          <div class="input_company cont">
            <span>소속사 이름</span>
            <input type="text" name="lcn_company" id="lcn_company" />
          </div>
          <div class="input_agent cont">
            <span>담당자 이름</span>
            <input type="text" name="lcn_agent_name" id="lcn_agent_name" />
          </div>
          <div class="input_agent cont">
            <span>담당자 연락처</span>
            <input type="text" name="lcn_agent_phone" id="lcn_agent_phone" />
          </div>
          <div class="btn_wrapper">
            <button type="submit" class="btn btn-primary">작성 완료</button>
            <button type="button" id="cancelBtn" class="btn btn-secondary">
              취소
            </button>
          </div>
        </div>
      </form>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->

    <script>
      $("#cancelBtn").click(function () {
        location.href = "/manageMusicFrm";
      });
    </script>
    <script>
      $("#search_artist").click(function () {
        const url = "/searchArtist";
        const title = "등록된 아티스트 검색";
        const status =
          "left=500px, top=300px, width=500px, height=500px, menubar=no, status=no,scrollbars=yes";

        const popup = window.open(url, title, status);
      });
    </script>
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
