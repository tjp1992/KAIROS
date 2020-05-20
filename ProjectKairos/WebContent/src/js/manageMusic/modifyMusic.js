$("form").submit(function () {
  const imgRegExp = /(.jpg)$/;
  const mp3RegExp = /(.mp3)$/;

  if ($("#title").val().length == 0) {
    alert("음원 타이틀을 입력해주세요");
    return false;
  } else if ($("#title").val().length > 200) {
    alert("음원 타이틀은 200자 미만만 가능합니다");
    return false;
  }

  if ($("#albums").val() == "default") {
    alert("앨범을 목록에서 선택해주세요");
    return false;
  }

  if (
    $("#input_music").val() != "" &&
    !mp3RegExp.test($("#input_music").val())
  ) {
    alert("음원은 mp3만 업로드 가능합니다.");
    return false;
  }

  return true;
});

$("#albums").change(function () {
  const albumNo = $(this).val();
  const albumImg = $(".img_container").children();
  albumImg.remove();

  $.ajax({
    url: "/asyncReadAlbumByAlbumNo",
    type: "post",
    data: { albumNo: albumNo },
    success: function (data) {
      if (data.albumPath != null) {
        const newAlbumImg = document.createElement("img");
        newAlbumImg.setAttribute(
          "src",
          "/src/imgs/albumImg/" + data.albumPath + ".jpg"
        );
        $(".img_container").append(newAlbumImg);
      }
    },
    error: function () {
      alert("앨범 이미지 조회 실패");
    },
  });
});
