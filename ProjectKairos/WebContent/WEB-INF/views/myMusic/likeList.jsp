<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/playList/likeList.css" />
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
        <div class="likelistoutline">
            <div class="llistname">좋아요리스트<span class="plistname"><a href="/playList">플레이리스트</a></span></div>
           
            <div class="tototo">
                <table>
                    <tr class="llll">
                        <th width="5%"><input class="allchk" type="checkbox"></th>
                        <td width="95%"><button>듣기</button><button>담기</button><button>좋아요 취소</button></td>
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
                        <th width="10%">담기</th>
                        <th width="10%">좋아요</th>
                    </tr>
                </table>
            </div>
            <div class="table">
                <table>
                	<c:forEach items="${list }" var="p">
                    <tr class="lltr1" songNo="${p.songNo }">
                        <th width="5%"><input class="llchk" type="checkbox"></th>
                        <th width="5%" class="plsongNo">${p.orderNo }</th>
                        <td width="60%">
                            <div class="stitle_dhg">&nbsp;&nbsp;${p.songTitle }</div>
                            <div class="subtitle">&nbsp;&nbsp;&nbsp;${p.songArtist }<span>&nbsp;&nbsp;|&nbsp;${p.albumName }</span></div>
                        </td>
                        <th width="10%" class="playimg"><i class="iconplay far fa-play-circle"></i></th>
                        <th width="10%" class="plusimg"><i class="iconplus fas fa-plus"></i></th>
                        
                        <th width="10%" class="heartimg"><i  class="iconheart fas fa-heart"></i></th>
                       
                        
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </section>
     <script>
        $(function(){
           $(".allchk").click(function(){
              var arr = $(".llchk");
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
            
           $(".llchk").click(function(){
              if($(".llchk").length == $(".llchk:checked").length){
                  $(".allchk").prop("checked",true);
              }else{
                  $(".allchk").prop("checked",false);
              }
           });
            
           $(".heartimg").children().click(function(){
        	   var songNo = $(this).parent().parent().attr("songNo");
        	   var icon =$(this);
        	   $.ajax({
        		 url:"/checkLike",
        		 type:"POST",
        		 data:{songNo:songNo},
        		 success:function(data){
        			 var result =Number(data);
        			 switch(result){
        			 case -1:
        				 break;
        			 case 0:
        				 icon.attr("class","iconheart fas fa-heart");
        				 icon.css("color","red");
        				 break;
        			 case 1:
        				 icon.parent().parent().remove();
        				 break;
        			 }
        		 }
        		   
        	   })
              if($(this).attr("class")=="iconheart fas fa-heart"){
                  $(this).attr("class","iconheart far fa-heart");
                  $(this).css("color","black");
              }else{
                  $(this).attr("class","iconheart fas fa-heart");
                  $(this).css("color","red");
              }
           });
        });
    </script>

    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
</body>

</html>
