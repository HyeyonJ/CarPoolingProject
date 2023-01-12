$(function () {
  $("#starRate").barrating({
    theme: "fontawesome-stars",
  });
});

var nameList = new Array('어피치', '초롱초롱', '튜브', '프로도', '라이언', '프로도');

function randomName(nameList) {
  return nameList[Math.floor(Math.random() * nameList.length)];
}

$("#nickname").val(randomName(nameList));

function reviewSubmit() {
  const dReview = {
    starPoint: $("#starRate").val(),
    nickname: $("#nickname").val(),
    content: $("#content").val(),
    toIdx: $("#pIdx").val(),
    rIdx: $("#rIdx").val(),
  };

  $.ajax({
    url: "/driver/driverCarpool/review",
    type: "POST",
    data: dReview,
    success: function (data, status) {
      if (status === "success") {
        window.location.href =
          "http://localhost:8080/driver/driverCarpool/registration";
      }
    },
  });
}
