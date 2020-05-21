<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <title>Insert title here</title>
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-3.3.1.js"
    ></script>
    <link rel="stylesheet" href="/src/css/bootstrap.css" />
    <script type="text/javascript" src="/src/js/bootstrap.js"></script>
    <link
      href="/src/css/adminMypage/adminNoticeWrite.css"
      rel="stylesheet"
      type="text/css"
    />
    <script
      type="text/javascript"
      src="/editor/js/HuskyEZCreator.js"
      charset="utf-8"
    ></script>
    <script type="text/javascript">
      $(function () {
        var oEditors = [];
        nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "weditor",
          sSkinURI: "/editor/SmartEditor2Skin.html",

          htParams: {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar: true,
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer: true,
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger: true,
          },

          fCreator: "createSEditor2",
        });

        $("#insert").click(function () {
          oEditors.getById["weditor"].exec("UPDATE_CONTENTS_FIELD", []);
          if($("#title").val() !=""){
        	  $("#insertFrm").submit();  
          }else{
        	  alert("제목을 입력해주세요");
        	  return false;
          }
          
        });

        // textArea에 이미지 첨부
        function pasteHTML(filepath) {
          var sHTML =
            '<img src="<%=request.getContextPath()%>/editor/storage/' +
            filepath +
            '">';
          oEditors.getById["weditor"].exec("PASTE_HTML", [sHTML]);
        }
      });
    </script>

    <script>
      $(function(){
      	$("#back").click(function(){
      		location.href = "/adminNotice?reqPage="+${reqPage };
      	});
      });
    </script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <section>
      <h2 class="h2">공지사항 등록</h2>

      <form action="/insertNotice" method="post" id="insertFrm">
        <table class="table table-bordered">
          <tr>
            <th>제목</th>
            <td>
              <input
                type="text"
                class="form-control"
                name="noticeTitle"
                id="title"
              />
            </td>
          </tr>
          <tr>
            <th>등록일</th>
            <td>${today }</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea
                name="noticeContent"
                rows="5"
                cols="90"
                style="width: 100%;"
                id="weditor"
              ></textarea>
            </td>
          </tr>
        </table>
        <div class="bottom">
          <button type="button" class="btn btn-default bbt" id="insert">
            등록
          </button>
          <button type="button" class="btn btn-default bbt" id="back">
            돌아가기
          </button>
        </div>
      </form>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>
