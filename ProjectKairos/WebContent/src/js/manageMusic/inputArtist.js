$("#artist").click(function () {
  $("#search_artist").click();
});

$("#search_artist").click(function () {
  const url = "/searchArtist";
  const title = "등록된 아티스트 검색";
  const status =
    "left=500px, top=300px, width=500px, height=500px, menubar=no, status=no,scrollbars=yes";

  const popup = window.open(url, title, status);
});
