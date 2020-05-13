<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/playList/psearchResult.css" />
    <script src="https://kit.fontawesome.com/8bd2671777.js" crossorigin="anonymous"></script>
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
</head>

<body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
        <div class="psearchoutline">
            <div class="psearch_wrapper">
                <div class="psearchname">플레이리스트 내 검색결과
                </div>
                <div class="plsearch">
                    <form action="/psearchKeyword">
                        <input type="text" name="psearchkeyword">
                        <button class="psearchBtn"><i class="iconsearch fas fa-search"></i></button>

                    </form>
                </div>
            </div>
            <div class="toto">
                <table>
                    <tr>
                        <td width="70%">&nbsp;곡정보</td>
                        <th width="10%">듣기</th>
                        <th width="10%">삭제</th>
                        <th width="10%">좋아요</th>
                    </tr>
                </table>
            </div>
            <div class="table">
                <table>
                    <tr class="pltr1">
                        <td width="70%">
                            <div class="stitle_dhg">&nbsp;&nbsp;곡며여어어어어어어어어어어어엉</div>
                            <div class="subtitle">&nbsp;&nbsp;&nbsp;가수명<span>&nbsp;&nbsp;|&nbsp;앨범명</span></div>
                        </td>
                        <th width="10%" class="playimg"><i class="iconplay far fa-play-circle"></i></th>
                        <th width="10%" class="deleteimg"><i class="icondelete far fa-trash-alt"></i></th>
                        <th width="10%" class="heartimg"><i class="iconheart far fa-heart"></i></th>
                    </tr>
                </table>
            </div>
        </div>
    </section>
    <script>
        $(function(){
           $(".heartimg").children().click(function(){
              if($(this).attr("class")=="iconheart far fa-heart"){
                  $(this).attr("class","iconheart fas fa-hearticonheart fas fa-heart");
                  $(this).css("color","red");
              }else{
                  $(this).attr("class","iconheart far fa-heart");
                  $(this).css("color","black");
              }
           }); 
        });
    </script>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
</body>

</html>
