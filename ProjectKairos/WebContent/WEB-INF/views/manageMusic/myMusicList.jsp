<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
            <form action="" method="get">
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
              <th class="result_no">
                <span>번호</span>
              </th>
              <th class="title">
                <span>제목</span>
              </th>
              <th class="artist">
                <span>아티스트</span>
              </th>
              <th class="album_name">
                <span>앨범명</span>
              </th>
              <th class="like_count">
                <span>Likes</span>
              </th>
              <th>
                <span>재생횟수</span>
              </th>
            </tr>
            <tr class="result">
              <td class="chk_box">
                <input type="checkbox" name="chkAll" id="chkAll" />
              </td>
              <td class="result_no">1</td>
              <td class="btn_container multi_cont">
                <button class="btn btn-sm btn-primary">삭제</button>
                <button class="btn btn-sm btn-danger">수정</button>
              </td>
              <td class="title">삐삐</td>
              <td class="artist">아이유</td>
              <td class="album_name">삐삐</td>
              <td class="like_count multi_cont">
                <span>248,468</span>
              </td>
              <td class="play_count">0</td>
              <td class="report_container"></td>
            </tr>
          </table>
        </div>
        <div class="page_nav">
          <button><i class="fas fa-angle-double-left"></i></button>
          <button><i class="fas fa-angle-left"></i></button>
          <button>1</button>
          <button>2</button>
          <button>3</button>
          <button>4</button>
          <button>5</button>
          <button><i class="fas fa-angle-right"></i></button>
          <button><i class="fas fa-angle-double-right"></i></button>
        </div>
      </div>
    </section>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
