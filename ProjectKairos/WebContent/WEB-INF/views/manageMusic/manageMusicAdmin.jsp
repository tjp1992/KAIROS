<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/manageMusic/manageMusicAdmin.css" />
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
  </head>
  <body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
      <a href="/manageArtistFrm" class="link_container">
        <div>
          라이센스<br />
          아티스트
        </div>
      </a>
      <a href="/insertMusicFrm" class="link_container">
        <div>
          라이센스<br />
          앨범 관리
        </div>
      </a>
      <a href="/insertMusicFrm" class="link_container">
        <div>
          라이센스<br />
          음원 등록
        </div>
      </a>
      <a href="/myMusicListFrm" class="link_container">
        <div>
          전체 음원
        </div>
      </a>
    </section>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
