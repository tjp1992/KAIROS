<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
          action="/insertMusic"
          method="POST"
          id="form-album"
          enctype="multipart/form-data"
        >
          <input
            type="hidden"
            name="artist"
            value="${sessionScope.user.userNick}"
          />
          <input type="hidden" name="licensed" value="0" />
          <div class="input_title cont">
            <span>곡명</span>
            <input type="text" name="title" id="title" />
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
            <input type="file" name="album_img_file" id="album_img_file" />
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
            <button type="button" class="btn btn-secondary">취소</button>
          </div>
        </form>
      </div>
    </section>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <script>
      $(function () {
        readAlbums();
      });

      $("#albums").change(function () {
        const albumNo = $(this).val();
        $.ajax({
          url: "/asyncReadAlbumByAlbumNo",
          type: "post",
          data: { albumNo: albumNo },
          success: function (data) {
            if (data.albumPath != null) {
            }
          },
          error: function () {
            alert("앨범 이미지 조회 실패");
          },
        });
      });

      function readAlbums() {
        const albumOwner = $("input[name=artist]").val();

        $.ajax({
          url: "/asyncReadAlbums",
          type: "post",
          data: { albumOwner: albumOwner },
          success: function (data) {
            const albumList = $("#albums");
            albumList.empty();

            const defaultOption = document.createElement("option");
            defaultOption.value = "default";
            defaultOption.innerHTML = "앨범 선택";

            albumList.append(defaultOption);

            for (let i = 0; i < data.length; i++) {
              let albumNo = data[i].albumNo;
              let albumName = data[i].albumName;

              const option = document.createElement("option");

              option.value = albumNo;
              option.innerHTML = albumName;

              albumList.append(option);
            }
          },
          error: function () {
            alert("앨범 목록 조회에 실패하였습니다.");
          },
        });
      }

      $(".insert_album").hide();

      $("#add_input_album").click(function () {
        $(".insert_album").show();
      });

      $("#add_album_btn").click(function () {
        const albumOwner = $("input[name=artist]").val();
        const albumName = $("input[name=input_album]").val();

        $.ajax({
          url: "/asyncInsertAlbum",
          type: "POST",
          data: {
            albumOwner: albumOwner,
            albumName: albumName,
          },
          success: function (data) {
            const result = Number(data);

            switch (result) {
              case -1: // 같은 이름의 앨범이 있을때
                alert("이미 같은 이름의 앨범이 있습니다.");
                break;

              case 0: // 앨범 등록에 실패 했을때
                alert(
                  "앨범 등록에 실패하였습니다. 오류가 반복되면 관리자에게 문의하세요."
                );
                break;

              case 1: // 앨범 등록에 성공 했을때
                $("#input_album").val("");
                $(".insert_album").hide();
                readAlbums();
                break;
            }
          },
          error: function () {
            alert("앨범 등록에 실패하였습니다.");
          },
        });
      });
    </script>
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
