<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <div class="psearchname"><a id="plist">플레이리스트</a> 내 검색결과
                </div>
                <div class="plsearch">
                    <form action="/psearchKeyword">
                        <input type="text" id="keyword" name="keyword" value="${keyword }">
                        <button class="psearchBtn" type="submit"><i class="iconsearch fas fa-search"></i></button>
                    </form>
                </div>
            </div>
            <div class="toto">
                <table>
                    <tr>
                        <td width="80%">&nbsp;곡정보</td>
                        <th width="10%">듣기</th>
                        <th width="10%">삭제</th>
                    </tr>
                </table>
            </div>
            <div class="table">
                <table>
                	<c:forEach items="${list }" var="p">
                    <tr class="pltr1" songNo="${p.songNo }" orderNo="${p.orderNo }" filepath="${p.filepath }" liked="${p.liked }">
                        <td width="80%">
                            <div class="stitle_dhg">&nbsp;&nbsp;${p.songTitle }</div>
                            <div class="subtitle">&nbsp;&nbsp;&nbsp;${p.songArtist }<span>&nbsp;&nbsp;|&nbsp;${p.albumName }</span></div>
                        </td>
                        <th width="10%" class="playimg"><i class="iconplay far fa-play-circle"></i></th>
                        <th width="10%" class="deleteimg"><i class="icondelete far fa-trash-alt"></i></th>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </section>
    <script>
        $(function(){
         
           $("#plist").click(function(){
        	   location.href="/playList";
           });

         
           
           $(".playimg").children().click(function(){
        	  var songNo = $(this).parent().parent().attr("songNo");
        	  var orderNo = $(this).parent().parent().attr("orderNo");
        	  $.ajax({
        		 url:"/frontAddOne",
        		 type:"POST",
        		 data:{
        			 songNo:songNo,orderNo:orderNo
        		 },
        		 success:function(data){
        			 var result = Number(data);
        			 if(result>0){
        				 alert("재~생~중~");
        				
        			 }
        			 
        		 }
        	  });
           });
           
           $(".deleteimg").children().click(function(){
        	  var songNo = $(this).parent().parent().attr("songNo");
        	  var orderNo = $(this).parent().parent().attr("orderNo");
        	  var icon = $(this);
        	  $.ajax({
        		 url:"/deletePlist",
        		 type:"POST",
        		 data:{
        			 songNo:songNo,orderNo:orderNo
        		 },
        		 success:function(data){
        			 var result = Number(data);
        			 if(result>0){
        				 icon.parent().parent().remove();
        				 alert("삭제성공");
        			 }
        		 }
        	  });
           });
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
