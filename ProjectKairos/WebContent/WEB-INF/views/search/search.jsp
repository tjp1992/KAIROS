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
            <button type="button" id="chkPlayNow">선택 재생</button>
            <button type="button" id="addChkPlaylist">플레이리스트 추가</button>
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
                  <i
                    class="far fa-play-circle playBtn"
                    songno="${s.songNo}"
                  ></i>
                  <i class="far fa-plus-square addBtn" songno="${s.songNo}"></i>
                  <span class="free_song">
                    <c:if test="${s.licensed eq 0 }">Free</c:if>
                  </span>
                </td>
                <td class="title">${s.songTitle }</td>
                <td class="artist">${s.songArtist }</td>
                <td class="album_name">${s.albumName }</td>
                <td class="like_count multi_cont">
                  <c:if test="${not empty sessionScope.user }">
                    <c:if test="${s.liked == 0}">
                      <i class="far fa-heart likeBtn" songno="${s.songNo}"></i>
                    </c:if>
                    <c:if test="${s.liked > 0 }">
                      <i
                        style="color: red;"
                        class="fas fa-heart likeBtn"
                        songno="${s.songNo}"
                      ></i>
                    </c:if>
                  </c:if>
                  <c:if test="${empty sessionScope.user }">
                    <i class="far fa-heart likeBtn" songno="${s.songNo}"></i>
                  </c:if>
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
    <form action="/searchFrm" method="GET" id="search-form">
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
      setReSearch();
      function setReSearch() {
        const reSearch = $("#reSearch").val();
        $("#re_search").val(reSearch);
      }

      $("#research-form").submit(function () {
        const reSearch = $("#re_search").val();

        const form = $("#search-form");
        if ($("#reSearch").length == 0) {
          const input = document.createElement("input");
          input.type = "hidden";
          input.name = "reSearch";
          input.id = "reSearch";
          form.append(input);
        }

        $("#reSearch").val(reSearch);
        $("#reqPage").val(1);
        sendSearch();

        return false;
      });

      chkKeyword();
      function chkKeyword() {
        const keyword = $("#keyword").val();
        $("#ld-keyword").val(keyword);
      }

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
    <script>
      $("#chkAll").click(function () {
        if ($(this).prop("checked") == true) {
          $(".chkBox").prop("checked", true);
        } else {
          $(".chkBox").prop("checked", false);
        }
      });
    </script>
    <c:if test="${not empty sessionScope.user }">
      <script>
        $("#addChkPlaylist").click(function () {
          $("#chkForm").submit();
        });
      </script>
    </c:if>
    <c:if test="${empty sessionScope.user }">
      <script>
        $("#addChkPlaylist,.likeBtn,.addBtn,.playBtn").click(function () {
          alert("로그인 해주세요!");
        });
      </script>
    </c:if>
    <c:if test="${not empty sessionScope.user }">
      <script>
        // 플레이 나우
        $("#chkPlayNow").click(function () {
          let songNo = "";
          const chks = $(".chkBox:checked");

          for (let i = 0; i < chks.length; i++) {
            songNo += chks[i].value;
            if (i != chks.length - 1) {
              songNo += ",";
            }
          }

          $.ajax({
            url: "/asyncAddPlayNow",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result > 0) {
                window.open(
                  "/player",
                  "",
                  "width=366px , height=650px , resizable=false"
                );
              } else {
                alert("추가를 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 연결에 실패하엿습니다.");
            },
          });
        });

        // 선택곡 추가

        $("#addChkPlaylist").click(function () {
          let songNo = "";
          const chks = $(".chkBox:checked");

          for (let i = 0; i < chks.length; i++) {
            songNo += chks[i].value;
            if (i != chks.length - 1) {
              songNo += ",";
            }
          }

          $.ajax({
            url: "/asyncAddPlayList",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result > 0) {
                alert(chks.length + "곡을 추가 완료");
              } else {
                alert("추가를 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 연결에 실패하엿습니다.");
            },
          });
        });

        // 한곡 플레이
        $(".playBtn").click(function () {
          const songNo = $(this).attr("songno");

          $.ajax({
            url: "/asyncAddPlayFirst",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result > 0) {
                window.open(
                  "/player",
                  "",
                  "width=366px , height=650px , resizable=false"
                );
              } else {
                alert("서버 접속에 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 접속에 실패하였습니다.");
            },
          });
        });

        $(".addBtn").click(function () {
          const songNo = $(this).attr("songno");

          // 노래제목 가져오는 시블링
          const songName = $(this).parent().next().html();

          $.ajax({
            url: "/addOnePlist",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result > 0) {
                alert("[" + songName + "] 곡을 플레이리스트에 추가하였습니다.");
              } else {
                alert("서버 접속에 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 접속에 실패하였습니다.");
            },
          });
        });

        $(".likeBtn").click(function () {
          // click된 element가 i 태그가 아니면 수정필요
          const btn = $(this);

          const songNo = $(this).attr("songno");

          // countSpan은 좋아요 카운트를 출력해주는 element
          const countSpan = $(this).next();
          const count = Number(countSpan.html());

          $.ajax({
            url: "/asyncSearchLike",
            type: "POST",
            data: { songNo: songNo },
            success: function (data) {
              const result = Number(data);
              if (result == 0) {
                btn.removeClass();
                btn.addClass("fas fa-heart likeBtn");
                btn.css("color", "red");
                countSpan.html(count + 1);
              } else if (result == 1) {
                btn.removeClass();
                btn.addClass("far fa-heart likeBtn");
                btn.css("color", "black");
                countSpan.html(count - 1);
              } else {
                alert("서버 접속에 실패하였습니다.");
              }
            },
            error: function () {
              alert("서버 접속에 실패하였습니다.");
            },
          });
        });
      </script>
    </c:if>
  </body>
</html>
