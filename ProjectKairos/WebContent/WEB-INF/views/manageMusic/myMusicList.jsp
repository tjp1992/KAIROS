<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <script src="/src/js/8bd2671777.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/src/css/manageMusic/myMusicList.css" />
  </head>
  <body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
      <div class="search_board">
        <div class="search_board_top_wrapper">
          <div>
            <form id="research-form">
              <input
                type="text"
                name="re_search_keyword"
                id="re_search"
                placeholder="결과 내 검색"
              />
              <button type="submit" id="ld-search">
                <img src="/src/imgs/icons/search.png" alt="" />
              </button>
            </form>
          </div>
          <div>
            <button>선택 삭제</button>
            <select name="pageViewNum" id="pageViewNum">
              <option value="25">한 페이지 출력 수</option>
              <option value="25">25</option>
              <option value="50">50</option>
              <option value="75">75</option>
              <option value="100">100</option>
            </select>
          </div>
        </div>
        <div class="search_board_content_wrapper">
          <table class="search_result tbl">
            <tr class="result_subject">
              <th class="chk_box">
                <input type="checkbox" name="chkAll" id="chkAll" />
              </th>
              <th class="result_no"><span>번호</span></th>
              <th class="title"><span>제목</span></th>
              <th class="artist"><span>아티스트</span></th>
              <th class="album_name"><span>앨범명</span></th>
              <th class="like_count"><span>Likes</span></th>
              <th><span>재생횟수</span></th>
            </tr>
            <c:forEach items="${list }" var="s">
              <tr class="result">
                <td class="chk_box">
                  <input type="checkbox" name="chkSong" id="${s.songNo }" />
                </td>
                <td class="result_no">${s.rowNum }</td>
                <td class="btn_container multi_cont">
                  <button
                    class="btn btn-sm btn-outline-primary mod_song"
                    songNo="${s.songNo}"
                  >
                    수정
                  </button>
                  <button
                    class="btn btn-sm btn-outline-danger del_song"
                    songNo="${s.songNo}"
                  >
                    삭제
                  </button>
                </td>
                <td class="title">${s.songTitle }</td>
                <td class="artist">${s.songArtist }</td>
                <td class="album_name">${s.albumName }</td>
                <td class="like_count multi_cont">
                  <span>${s.likeCount }</span>
                </td>
                <td class="play_count">${s.playCount }</td>
                <td class="report_container"></td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div class="page_nav">${pageNavi }</div>
      </div>
      <form id="req-form" action="/myMusicListFrm">
        <input
          type="hidden"
          name="reqPage"
          id="reqPage"
          value="${req.reqPage}"
        />
        <input
          type="hidden"
          name="numPerPage"
          id="numPerPage"
          value="${req.numPerPage}"
        />
        <input
          type="hidden"
          name="reSearch"
          id="reSearch"
          value="${req.reSearch}"
        />
      </form>
    </section>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <script>
      $(".mod_song").click(function () {
        const songNo = $(this).attr("songno");
        const songName = $(this).parent().next().html();
        if (confirm("[" + songName + "]곡을 수정하시겠습니까?")) {
          location.href = "/modifyMusicFrm?songNo=" + songNo;
        }
      });

      $(".del_song").click(function () {
        const songDesc = $(this).parent().parent();
        const songName = $(this).parent().next().html();
        const songNo = $(this).attr("songno");
        if (confirm("[" + songName + "] 곡을 정말 삭제하시겠습니까?")) {
          $.ajax({
            url: "/asyncDeleteSong",
            type: "post",
            data: { songNo: songNo },
            success: function (data) {
              if (data > 0) {
                $("#req-form").submit();
                // songDesc.remove();
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

      $(".page_btn").click(function () {
        const reqPage = $(this).val();
        $("#reqPage").val(reqPage);

        $("#req-form").submit();
      });

      $("#pageViewNum").change(function () {
        const numPerPage = $(this).val();
        $("#numPerPage").val(numPerPage);

        $("#req-form").submit();
      });

      $("#research-form").submit(function () {
        const reSearch = $("#re_search").val();
        $("#reSearch").val(reSearch);
        $("#reqPage").val(1);
        $("#req-form").submit();
        return false;
      });
    </script>
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
