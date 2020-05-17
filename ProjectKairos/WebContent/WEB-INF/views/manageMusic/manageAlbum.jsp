<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <link rel="stylesheet" href="/src/css/manageMusic/manageAlbum.css" />
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
          action="#"
          method="POST"
          enctype="multipart/form-data"
          id="form-album"
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
              <input type="text" name="artist" id="artist" readonly />
              <button type="button" id="search_artist">검색</button>
            </div>
          </c:if>
          <div class="select_album cont">
            <span>앨범명</span>
            <select name="albums" id="albums">
              <option value="default">앨범 선택</option>
            </select>
            <button type="button" id="add_input_album">+</button>
            <div class="album_btn" style="display: none;">
              <button
                type="button"
                id="mod_album_name"
                class="btn btn-outline-primary btn-sm"
              >
                수정
              </button>
              <button
                type="button"
                id="del_album"
                class="btn btn-outline-danger btn-sm"
              >
                삭제
              </button>
            </div>
            <div class="insert_album" style="display: none;">
              <input type="text" name="input_album" id="input_album" />
              <button type="button">앨범 추가</button>
            </div>
          </div>
          <div class="mid_wrapper">
            <div class="cont mid_cont">
              <span>앨범 상세</span>
            </div>
            <div class="upload_albumImg cont">
              <span>앨범 이미지</span>
              <input type="file" name="album_img_file" id="album_img_file" />
            </div>
          </div>
          <div class="mid_wrapper">
            <div class="album_desc form-control" id="album_desc">
              <div class="desc">
                <span class="desc_no"></span>
                <span class="desc_name">앨범을 선택해주세요.</span>
              </div>
            </div>
            <div class="img_container">
              <img src="/src/imgs/albumImg/아이유-Love poem.jpg" alt="" />
            </div>
          </div>
          <div class="btn_wrapper" style="display: none;">
            <button type="submit" class="btn btn-primary">수정 완료</button>
            <button type="button" class="btn btn-secondary">취소</button>
          </div>
        </form>
      </div>
    </section>

    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <script>
      // 곡 삭제 버튼을 클릭했을때
      function delSong() {
        $(".del_song").click(function () {
          const songDesc = $(this).parent().parent();
          const songName = $(this).parent().prev().html();
          const songNo = $(this).val();
          if (confirm("[" + songName + "] 곡을 정말 삭제하시겠습니까?")) {
            $.ajax({
              url: "/asyncDeleteSong",
              type: "post",
              data: { songNo: songNo },
              success: function (data) {
                if (data > 0) {
                  songDesc.remove();
                } else {
                  alert("음원 삭제에 실패하였습니다.");
                }
              },
              error: function () {
                alert("음원 삭제에 실패하였습니다.");
              },
            });
          }
        });
      }

      // 앨범명 수정버튼 클릭시 동작
      $("#mod_album_name").click(function () {
        $("#albums").hide();
        $("#add_input_album").hide();
        $(".btn_wrapper").show();
        $("#form-album").attr("action", "/modifyAlbum");
        const albumNo = $("#albums").val();
        const albumName = $("option[value=" + albumNo + "]").attr("songName");

        const modAlbumName = document.createElement("input");
        modAlbumName.value = albumName;
        modAlbumName.name = "albumName";
        modAlbumName.classList.add("input_mod_album");

        $(this).parent().before(modAlbumName);
        modAlbumName.focus();
      });

      // 앨범 목록에서 선택했을시 동작
      $("#albums").change(function () {
        $(".insert_album").hide();
        controlBtn();

        $("#album_desc").empty();
        const albumImg = $(".img_container").children();
        albumImg.remove();

        const albumNo = $("#albums").val();
        if (albumNo != "default") {
          $.ajax({
            url: "asyncReadSongsByAlbumNo",
            type: "POST",
            data: { albumNo: albumNo },
            success: function (data) {
              if (data.length != 0) {
                $("#album_desc").empty();

                for (let i = 0; i < data.length; i++) {
                  let desc = document.createElement("div");
                  desc.classList.add("desc");

                  let descNo = document.createElement("span");
                  descNo.classList.add("desc_no");
                  descNo.innerHTML = i + 1;

                  let descName = document.createElement("span");
                  descName.classList.add("desc_name");
                  descName.innerHTML = data[i].songTitle;

                  let descBtn = document.createElement("div");
                  descBtn.classList.add("desc_btn");

                  let descBtn1 = document.createElement("button");
                  descBtn1.type = "button";
                  descBtn1.id = "mod_song";
                  descBtn1.value = data[i].songNo;
                  descBtn1.classList.add("btn");
                  descBtn1.classList.add("btn-sm");
                  descBtn1.classList.add("btn-primary");
                  descBtn1.innerHTML = "수정";

                  let descBtn2 = document.createElement("button");
                  descBtn2.type = "button";
                  descBtn2.value = data[i].songNo;
                  descBtn2.classList.add("btn");
                  descBtn2.classList.add("btn-sm");
                  descBtn2.classList.add("btn-danger");
                  descBtn2.classList.add("del_song");
                  descBtn2.innerHTML = "삭제";

                  descBtn.append(descBtn1);
                  descBtn.append(descBtn2);

                  desc.append(descNo);
                  desc.append(descName);
                  desc.append(descBtn);

                  $("#album_desc").append(desc);
                }

                delSong();
              } else {
                let desc = document.createElement("div");
                desc.classList.add("desc");

                let descNo = document.createElement("span");
                descNo.classList.add("desc_no");

                let descName = document.createElement("span");
                descName.classList.add("desc_name");
                descName.innerHTML = "등록된 곡이 없습니다.";

                desc.append(descNo);
                desc.append(descName);

                $("#album_desc").append(desc);
              }

              if (data[0].albumPath != null) {
                const newAlbumImg = document.createElement("img");
                newAlbumImg.setAttribute(
                  "src",
                  "/src/imgs/albumImg/" + data[0].albumPath + ".jpg"
                );
                $(".img_container").append(newAlbumImg);
              }
            },
            error: function () {
              alert("앨범 목록 조회 실패");
            },
          });
        } else {
          let desc = document.createElement("div");
          desc.classList.add("desc");

          let descNo = document.createElement("span");
          descNo.classList.add("desc_no");

          let descName = document.createElement("span");
          descName.classList.add("desc_name");
          descName.innerHTML = "앨범을 선택해주세요.";

          desc.append(descNo);
          desc.append(descName);

          $("#album_desc").append(desc);
        }
      });

      // 앨범 수정, 삭제 버튼의 표시를 제어
      function controlBtn() {
        const selectedAlbum = $("#albums").val();

        if (selectedAlbum == "default") {
          $(".album_btn").hide();
        } else {
          $(".album_btn").show();
        }
      }
    </script>
    <script src="/src/js/manageMusic/selectAlbum.js"></script>
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
