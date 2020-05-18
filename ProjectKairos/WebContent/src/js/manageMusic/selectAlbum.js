$(function () {
  readAlbums();
});

function readAlbums() {
  const albumOwner = $("input[name=artist]").val();

  $.ajax({
    url: "/asyncReadAlbums",
    type: "post",
    data: { albumOwner: albumOwner },
    success: function (data) {
      const albumList = $("#albums");
      albumList.empty();

      const defaultOption = document.createElement("option");
      defaultOption.value = "default";
      defaultOption.innerHTML = "앨범 선택";

      albumList.append(defaultOption);

      for (let i = 0; i < data.length; i++) {
        let albumNo = data[i].albumNo;
        let albumName = data[i].albumName;

        const option = document.createElement("option");

        option.value = albumNo;
        option.setAttribute("albumName", albumName);
        option.id = albumNo;
        option.innerHTML = albumName;

        albumList.append(option);
      }
    },
    error: function () {
      alert("앨범 목록 조회에 실패하였습니다.");
    },
  });
}

$(".insert_album").hide();

$("#add_input_album").click(function () {
  $(".insert_album").show();
});

$("#add_album_btn").click(function () {
  const albumOwner = $("input[name=artist]").val();
  const albumName = $("#input_album").val();
  if (albumName.length > 200) {
    alert("앨범 제목은 200자 이하만 가능합니다.");
  } else if (albumName.length == 0) {
    alert("앨범 제목을 입력해주세요");
  } else {
    $.ajax({
      url: "/asyncInsertAlbum",
      type: "POST",
      data: {
        albumOwner: albumOwner,
        albumName: albumName,
      },
      success: function (data) {
        const result = Number(data);

        switch (result) {
          case -1: // 같은 이름의 앨범이 있을때
            alert("이미 같은 이름의 앨범이 있습니다.");
            break;

          case 0: // 앨범 등록에 실패 했을때
            alert(
              "앨범 등록에 실패하였습니다. 오류가 반복되면 관리자에게 문의하세요."
            );
            break;

          case 1: // 앨범 등록에 성공 했을때
            $("#input_album").val("");
            $(".insert_album").hide();
            readAlbums();
            break;
        }
      },
      error: function () {
        alert("앨범 등록에 실패하였습니다.");
      },
    });
  }
});
