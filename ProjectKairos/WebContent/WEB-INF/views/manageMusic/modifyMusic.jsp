<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <link rel="stylesheet" href="/src/css/manageMusic/insertMusic.css" />
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
        <form
          action="/modifyMusic"
          method="POST"
          id="form-album"
          enctype="multipart/form-data"
        >
          <c:if test="${sessionScope.user.userId != 'admin'}">
            <input
              type="hidden"
              name="artist"
              value="${sessionScope.user.userNick}"
            />
            <input type="hidden" name="licensed" value="0" />
          </c:if>
          <c:if test="${sessionScope.user.userId == 'admin'}">
            <input type="hidden" name="licensed" value="1" />
            <div class="input_artist cont">
              <span>가수명</span>
              <input
                type="text"
                name="artist"
                id="artist"
                value="${song.songArtist}"
                readonly
              />
            </div>
          </c:if>
          <input type="hidden" name="songNo" value="${song.songNo}" />
          <div class="input_title cont">
            <span>곡명</span>
            <input
              type="text"
              name="title"
              id="title"
              value="${song.songTitle}"
            />
          </div>
          <div class="select_album cont">
            <span>앨범명</span>
            <select name="albumNo" id="albums">
              <option value="default">앨범 선택</option>
            </select>
            <button type="button" id="add_input_album">+</button>
            <div class="insert_album">
              <input type="text" name="albumPath" id="input_album" />
              <button type="button" id="add_album_btn">앨범 추가</button>
            </div>
          </div>
          <div class="upload_albumImg cont">
            <span>앨범 이미지</span>
            <div class="img_container">
              <img src="" alt="" />
            </div>
          </div>
          <div class="upload_music cont">
            <span>음원</span>
            <input type="file" name="input_music" id="input_music" />
          </div>
          <div class="btn_wrapper">
            <button type="submit" class="btn btn-primary">작성 완료</button>
            <button type="button" id="cancelBtn" class="btn btn-secondary">
              취소
            </button>
          </div>
        </form>
      </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- 관리자 전용 script -->
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <script>
      $("#cancelBtn").click(function () {
        location.href = "/manageMusicFrm";
      });
    </script>
    <script src="/src/js/manageMusic/selectAlbum.js"></script>
    <script src="/src/js/manageMusic/modifyMusic.js"></script>
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
