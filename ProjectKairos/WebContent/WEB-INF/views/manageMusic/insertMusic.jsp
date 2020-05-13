<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
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
        <form action="#" method="POST">
          <div class="input_title container">
            <span>곡명</span>
            <input type="text" name="title" id="title" />
          </div>
          <div class="select_album container">
            <span>앨범명</span>
            <select name="albums" id="albums">
              <option value="default">앨범 선택</option>
            </select>
            <button>+</button>
            <div class="insert_album">
              <fieldset>
                <input type="text" name="input_album" id="input_album" />
                <button type="button">앨범 추가</button>
              </fieldset>
            </div>
          </div>
          <div class="upload_albumImg container">
            <span>앨범 이미지</span>
            <input type="file" name="album_img_file" id="album_img_file" />
          </div>
          <div class="upload_music container">
            <span>음원</span>
            <input type="file" name="input_music" id="input_music" />
          </div>
        </form>
      </div>
    </section>

    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
