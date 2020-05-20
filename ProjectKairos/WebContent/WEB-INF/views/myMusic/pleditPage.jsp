<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/playList/pleditPage.css" />
    <script src="https://kit.fontawesome.com/8bd2671777.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
</head>

<body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
        <div class="pleditoutline">
        <form id="plEditFrm" method="post">
            <div class="pleditname">플레이리스트 순서변경</div>
            <div class="tototo">
                <button type="button" class="upimg"><i class="iconup fas fa-angle-up"></i></button>
                <button type="button" class="downimg"><i class="icondown fas fa-angle-down"></i></button>
                <select onchange="orderChange(this)">
                	<option value="">정렬</option>
                    <option value="myedit">내 편집순</option>
                    <option value="artist_asc">아티스트명↓(가-하)순</option>
                    <option value="artist_desc">아티스트명↑(하-가)순</option>
                    <option value="title_asc">곡명↓(가-하)순</option>
                    <option value="title_desc">곡명↑(하-가)순</option>
                </select>
            </div>
            <div class="table">
                <table>
                	<c:forEach items="${list }" var="p">
                    <tr class="pltr1" songNo="${p.songNo }">
                        <th width="5%"><input class="plchk" name="songNo" type="checkbox" value="${p.songNo }">
                        <input style="display:none;" type="checkbox" name="orderNo" value=${p.orderNo } class="ordChk" />
                        </th>
                        <td width="90%">
                            <div class="stitle_dhg">&nbsp;&nbsp;${p.songTitle }</div>
                            <div class="subtitle">&nbsp;&nbsp;&nbsp;${p.songArtist }<span>&nbsp;&nbsp;|&nbsp;${p.albumName }</span></div>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="tototo">
                <button type="button" class="upimg"><i class="iconup fas fa-angle-up"></i></button>
                <button type="button" class="downimg"><i class="icondown fas fa-angle-down"></i></button>
            </div>
            <div class="sub">
                <input class="plsubmit" type="button" name="lineupsong" value="수정 완료">
                <input class="cancel" type="button" name="cancel" value="취소">
            </div>
            </form>
        </div>
    </section>
    <script>
    	function orderChange(t){
    		if(t.value=="artist_asc"){
    			location.href="/plEditArtistAsc";
    		}else if(t.value=="artist_desc"){
    			location.href="/plEditArtistDesc";
    		}else if(t.value=="title_asc"){
    			location.href="/plEditTitleAsc";
    		}else if(t.value=="title_desc"){
    			location.href="/plEditTitleDesc";
    		}else if(t.value=="myedit"){
    			location.href="/plEdit";
    		}
    	}
        $(function() {
        	$(".plsubmit").click(function(){
        		$("#plEditFrm").attr("action","/pleditPage");

            	$(".plchk").prop("checked",true);
            	$(".ordChk").prop("checked",true);
            	
        		$("#plEditFrm").submit();
        	});
        	

            $(".upimg").click(function() {
                var arr = $(".plchk:checked").parent().parent();
                var prev = arr.eq(0).prev();
                for (var i = 0; i < arr.length; i++) {
                    prev.before(arr[i]);
                }
            });
            
            $(".downimg").click(function() {
                var arr = $(".plchk:checked").parent().parent();
                var next = arr.eq(arr.length - 1).next();
                for (var i = arr.length - 1; i >= 0; i--) {
                    next.after(arr[i]);
                }
            });
			$(".cancel").click(function(){
				self.close();
			});
            
        });
        
    </script>
    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
</body>

</html>
