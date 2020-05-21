<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script type="text/javascript" src="/src/js/bootstrap.js"></script>
    <link
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
      crossorigin="anonymous"
    />
    <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
      integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
      crossorigin="anonymous"
    ></script>
    <link href="/src/css/main/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <title>KAIROS</title>
  </head>
  <body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
      <div class="wrapper">
        <div class="content">
          <div class="firstcontent">
            <a href="/voucherFrm"
              ><img id="fir" src="/src/imgs/mainimg/111.jpg"
            /></a>
            <h1>당신의 모든 움직임을 응원합니다.</h1>
            <p>
              카이로스 이용권과 함께 언제 어디서나 음악의 힘을
              믿어보세요
            </p>
            <div>
              <a href="/voucherFrm">이용권 구매</a>
            </div>
          </div>
          <div class="firstcontent">
            <a class="b1" href="/rankingFrm?reqPage=1&reqType=song"
              ><img src="/src/imgs/mainimg/twice2.jpg" />
            </a>
            <h1>당신의 모든 행동을 응원합니다</h1>
            <p>
              카이로스 음악차트와 함께 음악의 행복을 누려 보세요 
            </p>
            <div>
              <a href="/rankingFrm?reqPage=1&reqType=song">음악차트</a>
            </div>
          </div>
          <div class="firstcontent">
            <a class="b1" href="/rankingFrm?reqPage=1&reqType=album"
              ><img src="/src/imgs/mainimg/bts.jfif" />
            </a>
            <h1>당신의 감성을 느껴보세요</h1>
            <p>카이로스 앨범차트와 함께 좋아하는 가수의 목소리를 누려 보세요</p>
            <div>
              <a class="b1" href="/rankingFrm?reqPage=1&reqType=album">앨범차트</a>
            </div>
          </div>
          <div class="firstcontent">
            <a class="b1" href="/rankingFrm?reqPage=1&reqType=artist"
              ><img src="/src/imgs/mainimg/iu.jpg" />
            </a>
            <h1>여러분의 꿈과 희망을 응원합니다.</h1>
            <p>카이로스 아티스트 차트와 함께 꿈과 희망을 키워보세요</p>
            <div>
              <a class="b1" href="/rankingFrm?reqPage=1&reqType=artist" style="width: 150px" >아티스트차트</a>
            </div>
          </div>
          <div class="firstcontent">
            <a class="b3" href="/rankingFrm?reqPage=1&reqType=play">
            	<img src="/src/imgs/mainimg/redbelvet.jpeg" />
            </a>
            <a class="b3" href="/rankingFrm?reqPage=1&reqType=play">
            	<img src="/src/imgs/mainimg/blackpink2.jfif" />
            </a>
            <h1>당신의 성공을 응원합니다.</h1>
            <p>카이로스 플레이차트와 함께 자신만의 세계로 빠져보세요</p>
            <div>
              <a class="b1" href="/rankingFrm?reqPage=1&reqType=play">플레이차트</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
  </body>
</html>
