// 곡 삭제 버튼을 클릭했을때
function delSong() {
  $(".del_song").click(function () {
    const songDesc = $(this).parent().parent();
    const songName = $(this).parent().prev().html();
    const songNo = $(this).val();
    if (confirm("[" + songName + "] 곡을 정말 삭제하시겠습니까?")) {
      $.ajax({
        url: "/asyncDeleteSong",
        type: "post",
        data: { songNo: songNo },
        success: function (data) {
          if (data > 0) {
            songDesc.remove();
          } else {
            alert("음원 삭제에 실패하였습니다.");
          }
        },
        error: function () {
          alert("음원 삭제에 실패하였습니다.");
        },
      });
    }
  });
}
