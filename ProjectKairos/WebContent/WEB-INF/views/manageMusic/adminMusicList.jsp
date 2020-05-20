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
    <link rel="stylesheet" href="/src/css/search/search.css" />
  </head>
  <body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
      <div class="search_menu_wrapper">
        <span>결과 선택</span>
        <div>
          <span>국가</span>
          <select name="setCountry" id="setCountry">
            <option id="all" value="all">전체</option>
            <option id="kpop" value="kpop">국내</option>
            <option id="pop" value="pop">해외</option>
          </select>
        </div>
        <div class="search_category">
          <a
            href="javascript:void(0)"
            class="slt_category"
            category="song_title"
          >
            <span>곡명</span></a
          >
          <a
            href="javascript:void(0)"
            class="slt_category"
            category="song_artist"
            ><span>아티스트</span></a
          >
          <a
            href="javascript:void(0)"
            class="slt_category"
            category="album_name"
            ><span>앨범</span></a
          >
        </div>
        <div>
          <c:if test="${req.licensed == 0 }">
            <label>
              <input type="checkbox" id="chkLcn" name="setLicensed" value="1" />
              <span>유료</span>
            </label>
            <label>
              <input
                type="checkbox"
                id="chkFree"
                name="setLicensed"
                value="0"
              />
              <span>무료</span>
            </label>
          </c:if>
          <c:if test="${req.licensed == 1 }">
            <label>
              <input
                type="checkbox"
                id="chkLcn"
                name="setLicensed"
                value="1"
                checked
              />
              <span>유료</span>
            </label>
            <label>
              <input
                type="checkbox"
                id="chkFree"
                name="setLicensed"
                value="0"
              />
              <span>무료</span>
            </label>
          </c:if>
          <c:if test="${req.licensed == 2 }">
            <label>
              <input type="checkbox" id="chkLcn" name="setLicensed" value="1" />
              <span>유료</span>
            </label>
            <label>
              <input
                type="checkbox"
                id="chkFree"
                name="setLicensed"
                value="0"
                checked
              />
              <span>무료</span>
            </label>
          </c:if>
          <c:if test="${req.licensed == 3 }">
            <label>
              <input
                type="checkbox"
                id="chkLcn"
                name="setLicensed"
                value="1"
                checked
              />
              <span>유료</span>
            </label>
            <label>
              <input
                type="checkbox"
                id="chkFree"
                name="setLicensed"
                value="0"
                checked
              />
              <span>무료</span>
            </label>
          </c:if>
        </div>
      </div>
      <div class="search_board">
        <div class="search_board_top_wrapper">
          <div>
            <span>검색결과 : ${totalCount}건</span>
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
              <th class="play_count"><span>재생횟수</span></th>
            </tr>
            <c:forEach items="${list }" var="s">
              <tr class="result">
                <td class="chk_box">
                  <input
                    type="checkbox"
                    name="chkBox"
                    class="chkBox"
                    value="${s.songNo}"
                  />
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
                  <i class="far fa-heart likeBtn" songno="${s.songNo}"></i>
                  <span>${s.likeCount }</span>
                </td>
                <td class="play_count">${s.playCount }</td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div class="page_nav">${pageNavi}</div>
      </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <form action="/adminMusicListFrm" method="GET" id="search-form">
      <input type="hidden" name="keyword" id="keyword" value="${req.keyword}" />
      <input type="hidden" name="reqPage" id="reqPage" value="${req.reqPage}" />
      <input
        type="hidden"
        name="numPerPage"
        id="numPerPage"
        value="${req.numPerPage}"
      />
      <c:if test="${not empty req.category }">
        <input
          type="hidden"
          name="category"
          id="category"
          value="${req.category}"
        />
      </c:if>
      <c:if test="${not empty req.genre }">
        <input type="hidden" name="genre" id="genre" value="${req.genre}" />
      </c:if>
      <c:if test="${not empty req.licensed }">
        <input
          type="hidden"
          name="licensed"
          id="licensed"
          value="${req.licensed}"
        />
      </c:if>
      <c:if test="${not empty req.reSearch }">
        <input
          type="hidden"
          name="reSearch"
          id="reSearch"
          value="${req.reSearch}"
        />
      </c:if>
    </form>
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
                $("#search-form").submit();
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

      setReSearch();
      function setReSearch() {
        const reSearch = $("#reSearch").val();
        $("#re_search").val(reSearch);
      }

      $("#research-form").submit(function () {
        const reSearch = $("#re_search").val();

        $("#keyword").val(reSearch);
        $("#reqPage").val(1);
        sendSearch();

        return false;
      });

      $("input[name='setLicensed']").click(function () {
        const lcn = $("#chkLcn");
        const free = $("#chkFree");

        if (lcn.prop("checked") && free.prop("checked")) {
          $("#licensed").val(3);
        } else if (lcn.prop("checked")) {
          $("#licensed").val(1);
        } else if (free.prop("checked")) {
          $("#licensed").val(2);
        } else {
          $("#licensed").val(0);
        }

        sendSearch();
      });

      $(".page_btn").click(function () {
        $("#reqPage").val($(this).val());
        sendSearch();
      });

      $("#pageViewNum").change(function () {
        $("#numPerPage").val($(this).val());
        sendSearch();
      });

      setGenre();
      function setGenre() {
        if ($("#genre").length != 0) {
          const val = $("#genre").val();
          $("#" + val).attr("selected", true);
        }
      }

      $("#setCountry").change(function () {
        const form = $("#search-form");
        if ($("#genre").length == 0) {
          const input = document.createElement("input");
          input.type = "hidden";
          input.name = "genre";
          input.id = "genre";
          form.append(input);
        } else if ($(this).val() == "all") {
          $("#genre").remove();
          $("#reqPage").val(1);
          return sendSearch();
        }

        $("#genre").val($(this).val());
        $("#reqPage").val(1);
        sendSearch();
      });

      $(".slt_category").click(function () {
        const form = $("#search-form");
        if ($("#category").length == 0) {
          const input = document.createElement("input");
          input.type = "hidden";
          input.name = "category";
          input.id = "category";
          form.append(input);
        }

        $("#category").val($(this).attr("category"));
        $("#reqPage").val(1);
        sendSearch();
      });

      function sendSearch() {
        $("#search-form").submit();
      }
    </script>
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
