$(function () {
  $("#starRate").barrating({
    theme: "fontawesome-stars",
  });
});

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
