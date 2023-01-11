$(function () {
  $("#starRate").barrating({
    theme: "fontawesome-stars",
  });
});

function reviewSubmit() {
  const dReview = {
    starPoint: $("#starRate").val(),
    nickname: $("#nickname").val(),
    content: $("#content").val(),
    // toIdx: $("#pIdx").val(),
    toIdx: "1",
    // rIdx: $("#rIdx").val(),
    rIdx: "1",
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
