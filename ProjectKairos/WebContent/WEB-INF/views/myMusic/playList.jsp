<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--↓↓ 스타일 시트를 추가할 시 이곳에 ↓↓-->
    <link rel="stylesheet" href="/src/css/playList/playList.css" />
    <!--↑↑ 스타일 시트를 추가할 시 이곳에 ↑↑-->
    <title>KAIROS</title>
</head>

<body>
    <!-- header.jsp에 jquery와 jstl이 이미 추가되어 있습니다! -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- 우리 웹의 기본 틀이 될 구조입니다 -->
    <!-- section에 콘텐츠 작성하세요! -->
    <section>
        <div class="toto">
            <div class="plistname">플레이리스트<span class="likelist">좋아요리스트</span></div>
            <div class="psearch">
                <button class="psearchBtn" type="submit"><img src="/src/imgs/icons/search.png"></button>
                <input type="text" id="psearch" placeholder="검색">
            </div>
            <div class="tototo">
                <table>
                    <tr class="pllll">
                        <th width="10%"><input class="allchk" type="checkbox"></th>
                        <td width="60%"><button>듣기</button><button>삭제</button><button>좋아요</button></td>
                        <th width="30%">|&nbsp;&nbsp;&nbsp;&nbsp;<button>편집하기</button></th>
                    </tr>
                </table>
            </div>
            <div class="table">
                <table>
                    <tr class="pltr1">
                        <th width="10%"><input class="plchk" type="checkbox"></th>
                        <th width="10%">NO</th>
                        <td width="50%">
                            <div class="stitle_dhg">&nbsp;&nbsp;곡며여어어어어어어어어어어어엉</div>&nbsp;&nbsp;가수명<span>&nbsp;&nbsp;|&nbsp;앨범명</span>
                        </td>
                        <th width="10%">듣</th>
                        <th width="10%">담</th>
                        <th width="10%">좋</th>
                    </tr>
                </table>
            </div>
        </div>
    </section>

    <!-- ↓↓ JS 파일 추가시 이곳에 ↓↓-->
    <!-- ↑↑ JS 파일 추가시 이곳에 ↑↑-->
</body>

</html>
