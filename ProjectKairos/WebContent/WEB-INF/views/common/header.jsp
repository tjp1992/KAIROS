<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<script type="text/javascript" src="/src/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/src/css/style.css" />
<link rel="stylesheet" href="/src/css/header/header.css" />
<!-- header 수정이 필요할 경우 꼭 이도형에게 알림바랍니다!!! -->
<!-- 여러사람이 동시에 다른내용으로 header를 수정할 경우 branch merge시 충돌이 발생합니다 -->
<header>
  <!-- 유저가 로그인 되지 않았을 경우 표시됨 -->
  <c:if test="${empty sessionScope.user }">
    <div class="ld-header_top_wrapper">
      <!-- 로그인 서블릿 /loginFrm 작성 필요 -->
      <a href="/adminMypage"> <img src="/src/imgs/icons/user.png" alt="" /></a>
      <span>
        <!-- 로그인 서블릿 /loginFrm 작성 필요 -->
        <a href="/loginFrm">로그인</a>
      </span>
      <span>
        <!-- 회원가입 서블릿 /joinFrm 작성 필요 -->
        <a href="/joinFrm">회원가입</a>
      </span>
    </div>
  </c:if>
  <!-- 유저가 로그인 되었을 경우 표시됨 -->
  <c:if test="${not empty sessionScope.user }">
    <c:if test="${sessionScope.user.userId == 'admin' }">
      <div class="ld-header_top_wrapper">
        <!-- 마이페이지 서블릿 /mypageFrm 작성 필요-->
        <a href="/adminMypage">
          <img src="/src/imgs/icons/user.png" alt=""
        /></a>
        <span>
          <a href="/adminMypage">${sessionScope.user.userName }</a>
        </span>
        <span>
          <a href="/logout">로그아웃</a>
        </span>
      </div>
    </c:if>
    <c:if test="${sessionScope.user.userId != 'admin' }">
      <div class="ld-header_top_wrapper">
        <!-- 마이페이지 서블릿 /mypageFrm 작성 필요-->
        <a href="/mypageFrm"> <img src="/src/imgs/icons/user.png" alt="" /></a>
        <span>
          <a href="/mypageFrm">${sessionScope.user.userName }</a>
        </span>
        <span>
          <a href="/logout">로그아웃</a>
        </span>
      </div>
    </c:if>
  </c:if>
  <nav>
    <div class="ld-main_wrapper">
      <a href="/"> <img src="/src/imgs/logo/main_logo.png" alt="메인" /> </a>
    </div>
    <div class="ld-menu_wrapper">
      <!-- 공지사항 서블릿 /noticeFrm 작성 필요 -->
      <span><a href="/adminNotice?reqPage=1">공지사항</a></span>
      <!-- 랭킹차트 서블릿 /rankingFrm 작성 필요 -->
      <span><a href="/rankingFrm?reqPage=1&reqType=song">차트</a></span>
      <form action="/searchSong" method="GET" class="ld-search">
        <c:if test="${empty keyword }">
          <input type="text" name="keyword" id="ld-keyword" />
        </c:if>
        <c:if test="${not empty keyword }">
          <input
            type="text"
            name="keyword"
            id="ld-keyword"
            value="${keyword }"
          />
        </c:if>
        <button type="submit" id="ld-search">
          <img src="/src/imgs/icons/search.png" alt="" />
        </button>
      </form>
      <!-- 이용권 서블릿 /voucherFrm 작성필요 -->
      <span><a href="/voucherFrm">이용권</a></span>
      <!-- 이용권 서블릿 /myMusicFrm 작성필요 -->
      <span><a href="/myMusic">My Music</a></span>
    </div>
  </nav>
</header>
