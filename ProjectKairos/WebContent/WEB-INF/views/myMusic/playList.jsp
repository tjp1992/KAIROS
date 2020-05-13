<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/playList/playList.css" />
    <sctipt src="/src/js/8bd2671777.js" crossorigin="anonymous"></sctipt>
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
    
</head>
<body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
        <div class="playlistoutline">
            <div class="plistname">플레이리스트<span class="likelistname"><a href="/likeList">좋아요리스트</a></span></div>
            <div class="psearch">
                <button class="psearchBtn" type="submit"><img src="/src/imgs/icons/search.png"></button>
                <input type="text" id="psearch" placeholder="검색">
            </div>
            <div class="tototo">
                <table>
                    <tr class="pllll">
                        <th width="5%"><input class="allchk" type="checkbox"></th>
                        <td width="65%"><button>듣기</button><button>삭제</button><button>좋아요 ♥</button></td>
                        <th width="30%">|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button>순서변경</button></th>
                    </tr>
                </table>
            </div>
            <div class="toto">
                <table>
                    <tr>
                        <th width="5%"></th>
                        <th width="5%">번호</th>
                        <td width="60%">&nbsp;곡정보</td>
                        <th width="10%">듣기</th>
                        <th width="10%">삭제</th>
                        <th width="10%">좋아요</th>
                    </tr>
                </table>
            </div>
            <div class="table">
                <table>
                    <tr class="pltr1">
                        <th width="5%"><input class="plchk" type="checkbox"></th>
                        <th width="5%" class="plsongNo">1</th>
                        <td width="60%">
                            <div class="stitle_dhg">&nbsp;&nbsp;곡며여어어어어어어어어어어어엉</div><div class="subtitle">&nbsp;&nbsp;&nbsp;가수명<span>&nbsp;&nbsp;|&nbsp;앨범명</span></div>
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
           $(".allchk").click(function(){
              var arr = $(".plchk");
              if($(this).prop("checked")==true){
                  for(var i=0; i<arr.length; i++){
                      arr.eq(i).prop("checked",true);
                  }
              }else{
                  for(var i=0; i<arr.length; i++){
                      arr.eq(i).prop("checked",false);
                  }
              }
           }); 
            
           $(".plchk").click(function(){
              if($(".plchk").length == $(".plchk:checked").length){
                  $(".allchk").prop("checked",true);
              }else{
                  $(".allchk").prop("checked",false);
              }
           });
            
           $(".heartimg").children().click(function(){
              if($(this).attr("class")=="iconheart far fa-heart"){
                  $(this).attr("class","iconheart fas fa-heart");
                  $(this).css("color","red");
              }else{
                  $(this).attr("class","iconheart far fa-heart");
                  $(this).css("color","black");
              }
           });
           $(".pltr1").hover(function(){
               $(this).css("background-color","#F2F6FC");
           },function(){
               $(this).css("background-color","white");
           });  
        });
    </script>

    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
</body>

</html>
