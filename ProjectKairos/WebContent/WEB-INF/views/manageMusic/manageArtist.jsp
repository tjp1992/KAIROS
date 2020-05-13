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
      <div class="insert_wrapper">
        <div class="input_artist cont">
          <span>가수 이름</span>
          <input type="text" name="artist" id="artist" />
          <button type="submit" class="btn btn-light">
            <i class="fas fa-search"></i>
          </button>
        </div>
        <div class="btn_wrapper">
          <button type="submit" class="btn btn-primary">작성 완료</button>
          <button type="button" class="btn btn-secondary">취소</button>
        </div>
      </div>
    </section>

    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
