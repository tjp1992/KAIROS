<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
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
            <option value="all">전체</option>
            <option value="k-pop">국내</option>
            <option value="pop">해외</option>
          </select>
        </div>
        <div class="search_category">
          <a href="#"><span>곡명</span></a>
          <a href="#"><span>아티스트</span></a>
          <a href="#"><span>앨범</span></a>
        </div>
        <div>
          <label>
            <input type="checkbox" name="setLicensed" value="1" />
            <span>유료</span>
          </label>
          <label>
            <input type="checkbox" name="setLicensed" value="0" />
            <span>무료</span>
          </label>
        </div>
      </div>
      <div class="search_board">
        <div class="search_board_top_wrapper">
          <div>
            <span>검색결과 : &#38; </span>
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
          <div>test2</div>
        </div>
        <div class="search_board_content_wrapper"></div>
      </div>
    </section>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
