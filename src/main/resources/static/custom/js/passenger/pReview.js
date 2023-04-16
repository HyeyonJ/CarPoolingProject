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
  const pReview = {
    starPoint: $("#starRate").val(),
    nickname: $("#nickname").val(),
    content: $("#content").val(),
    toIdx: $("#dIdx").val(),
    rIdx: $("#rIdx").val()
  };

  $.ajax({
    url: "/passenger/passengerCarpool/review",
    type: "POST",
    data: pReview,
    success: function (data, status) {
      if (status === "success") {
        window.location.href =
          "http://localhost:8080/passenger/passengerCarpool/reservation";
      }
    },
  });
}
