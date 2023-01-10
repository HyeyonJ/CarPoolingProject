// jQuery datepicker
$(function () {
  $("#datepicker").datepicker({
    dateFormat: "yy-mm-dd",
  });
});

// 부트스트랩 모달창 닫을 시 입력값 초기화
$("#selectModal").on("hide.bs.modal", function () {
  $(this).find(".modal-body form")[0].reset();
});

// 출근에 따른 변화
$("#goWork").change(() => {
  $("#workCheck").css("display", "none");
  // prop('checked') = 만약 goWork가 checked 상태이면 true값 반환
  // -> 출근 체크하면 출근 option이 보이게, 퇴근 option은 안보이게
  if ($("#goWork").prop("checked") == true) {
    $(".go_work_boarding_time").css("display", "block");
    $(".leave_work_boarding_time").css("display", "none");
    $(".go_work_boarding_times").css("display", "block");
    $(".leave_work_boarding_time").css("display", "none");
    $("#selectBoardingTime").val("선택");
  }
});

// 퇴근에 따른 변화
$("#leaveWork").change(() => {
  $("#workCheck").css("display", "none");
  // prop('checked') = 만약 leaveWork가 checked 상태이면 true값 반환
  // -> 출근 체크하면 퇴근 option이 보이게, 출근 option은 안보이게
  if ($("#leaveWork").prop("checked") == true) {
    $(".go_work_boarding_time").css("display", "none");
    $(".leave_work_boarding_time").css("display", "block");
    $(".go_work_boarding_times").css("display", "none");
    $(".leave_work_boarding_time").css("display", "block");
    $("#selectBoardingTime").val("선택");
  }
});

$("#male, #female, #any").change(() => {
  $("#genderCheck").css("display", "none");
});

$("#startPoint, #endPoint").change(() => {
  $("#routeCheck").css("display", "none");
});

$("#routesearch").click(() => {
  $("#searchCheck").css("display", "none");
});

function timeCheck(today, selectBoardingTimeVal) {
  var hours = today.getHours().toString();
  var minutes = today.getMinutes().toString();
  if (hours < 10) {
    hours = "0" + hours;
  }
  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  const prHours = selectBoardingTimeVal.substring(0, 2);
  const prMinutes = selectBoardingTimeVal.substring(3, 5);
  // 운행시간이 현재시간 이전이거나 시간은 같은데 운행분이 현재분 이전일 때
  if (hours > prHours || (hours === prHours && minutes > prMinutes)) {
    // 시간을 다시 선택해주세요. - 보여주기
    $("#timeRecheck").css("display", "block");
    // val 초기화
    $("#selectBoardingTime").val("선택");
    return false;
  } else {
    // 시간을 다시 선택해주세요. - 숨기기
    $("#timeRecheck").css("display", "none");
  }
}

$("#datepicker").change(() => {
  // 현재날짜
  var today = new Date();
  var year = today.getFullYear().toString();
  var month = (today.getMonth() + 1).toString();
  var date = today.getDate().toString();

  if (month < 10) {
    month = "0" + month;
  }
  if (date < 10) {
    date = "0" + date;
  }

  // 선택한날짜
  const dateVal = $("#datepicker").val();
  const prYear = dateVal.substring(0, 4);
  const prMonth = dateVal.substring(5, 7);
  const prDate = dateVal.substring(8, 10);

  // 탑승날짜가 현재날짜 이전일 때
  if (year + month + date > prYear + prMonth + prDate) {
    // 날짜를 다시 선택해주세요. - 보여주기
    $("#dateRecheck").css("display", "block");
    // 날짜를 선택해주세요. - 숨기기 -> block인 경우도 있으니까
    $("#dateCheck").css("display", "none");
    // val 초기화
    $("#datepicker").val("");
    return false;
  } else {
    // 날짜를 다시 선택해주세요. - 숨기기
    $("#dateRecheck").css("display", "none");
    // 날짜를 선택해주세요. - 숨기기 -> block인 경우도 있으니까
    $("#dateCheck").css("display", "none");
  }

  // 탑승날짜가 현재날짜와 같을 때 시간비교
  const selectBoardingTimeVal = $("#selectBoardingTime").val();
  // 탑승날짜가 현재날짜와 같고 시간이 선택되어져있을 때
  if (
    year + month + date === prYear + prMonth + prDate &&
    selectBoardingTimeVal !== "선택"
  ) {
    timeCheck(today, selectBoardingTimeVal);
  }
});

$("#selectBoardingTime").change(() => {
  $("#timeRecheck").css("display", "none");
  // 현재날짜
  var today = new Date();
  var year = today.getFullYear().toString();
  var month = (today.getMonth() + 1).toString();
  var date = today.getDate().toString();
  if (month < 10) {
    month = "0" + month;
  }
  if (date < 10) {
    date = "0" + date;
  }

  const dateVal = $("#datepicker").val();
  const prYear = dateVal.substring(0, 4);
  const prMonth = dateVal.substring(5, 7);
  const prDate = dateVal.substring(8, 10);

  // 탑승날짜가 ""이 아닐 때
  if ($("#datepicker").val() !== "") {
    // 탑승날짜가 현재날짜와 같을 때
    if (year + month + date === prYear + prMonth + prDate) {
      const selectBoardingTimeVal = $("#selectBoardingTime").val();
      timeCheck(today, selectBoardingTimeVal);
    }
  }
});

$("#selectBoardingTime").click((e) => {
  e.preventDefault();

  $("#timeCheck").css("display", "none");
  if ($(':radio[name="work"]:checked').length < 1) {
    // 출근 또는 퇴근을 선택해주세요. - 보여주기
    $("#workCheck").css("display", "block");
    // 바로 focusOut 하기
    $("#selectBoardingTime").blur();
    return false;
  } else {
    // 출근 또는 퇴근을 선택해주세요. - 숨기기
    $("#workCheck").css("display", "none");
  }
});

$("#searchBtn").click(() => {
  $("#searchCarpoolList").empty();

  // 출퇴근 선택이 안되어있을 때
  if ($(':radio[name="work"]:checked').length < 1) {
    $("#workCheck").css("display", "block");
    return false;
  } else {
    $("#workCheck").css("display", "none");
  }

  // 성별 선택이 안되어있을 때
  if ($(':radio[name="gender"]:checked').length < 1) {
    $("#genderCheck").css("display", "block");
    return false;
  } else {
    $("#genderCheck").css("display", "none");
  }

  // 날짜 선택이 안되어있을 때
  if ($("#datepicker").val() == "") {
    // 날짜를 다시 선택해주세요. - 숨기기 -> block인 경우도 있으니까
    $("#dateRecheck").css("display", "none");
    $("#dateCheck").css("display", "block");
    return false;
  } else {
    $("#dateCheck").css("display", "none");
  }

  if ($("#selectBoardingTime").val() == "선택") {
    $("#timeCheck").css("display", "block");
    return false;
  } else {
    $("#timeCheck").css("display", "none");
  }

  // 출발지 or 도착지 ""일 때
  if ($("#start").val() == "" || $("#end").val() == "") {
    $("#routeCheck").css("display", "block");
    $("#startPointCheck").css("display", "none");
    $("#endPointCheck").css("display", "none");
    return false;
  } else {
    $("#routeCheck").css("display", "none");
  }

  // 요금이 ""일 때
  if ($("#fare").val() == "") {
    $("#searchCheck").css("display", "block");
    $("#startPointSearchCheck").css("display", "none");
    $("#endPointSearchCheck").css("display", "none");
    return false;
  } else {
    $("#searchCheck").css("display", "none");
  }

  const searchCarpool = {
    pDate: $("#datepicker").val().toString(),
    pCommute: $("input:radio[name='work']:checked").val().toString(),
    pHopeGender: $("input:radio[name='gender']:checked").val().toString(),
    pBoardingTime: $("#selectBoardingTime").val(),
    pStartLon: $("#startlon").val() + 1,
    pStartLat: $("#startlat").val() + 1,
    pEndLon: $("#endlon").val() + 1,
    pEndLat: $("#endlat").val() + 1,
  };

  $.ajax({
    url: "/passenger/passengerCarpool/reservation/searchAll",
    type: "POST",
    data: searchCarpool,
    success: function (data) {
      if (data !== "") {
        swal(
          "찾기성공!",
          "원하시는 카풀을 예약해 주시기 바랍니다.",
          "success"
        ).then((OK) => {
          if (OK) {
            console.log(data);
            var html = "";
            if (data.length > 0) {
              for (var i = 0; i < data.length; i++) {
                html += '<div id="match">';
                // html += '<img src="img/car.png>" id="matchimg">';
                html +=
                  '<input type="hidden" id="' +
                  data[i].drIdx +
                  '"><input type="hidden" id="' +
                  data[i].didx +
                  '">';
                html +=
                  '<span id="fdate">' +
                  data[i].ddate.substring(0, 10) +
                  "</span>\t" +
                  data[i].dcommute +
                  "<br>\n";
                html +=
                  '<span class="fitem">픽업가능시간</span>\t' +
                  data[i].dstartTime +
                  "\t -\t " +
                  data[i].dendTime +
                  "<br>\n";
                html +=
                  '<span class="fitem">출발</span>\t' +
                  data[i].dstartPoint +
                  "<br>\n";
                html +=
                  '<span class="fitem">도착</span>\t' +
                  data[i].dendPoint +
                  "<br>\n";
                html +=
                  '<span class="fitem">요금</span>\t' +
                  data[i].dfee +
                  "원 <br>";
                html +=
                  '<button id="view" onclick="dRoute(' +
                  data[i].dstartlon +
                  ", " +
                  data[i].dstartlat +
                  ", " +
                  data[i].dendlon +
                  ", " +
                  data[i].dendlat +
                  ')" class="btn rsvsbtn">경로보기</button>\t';
                html +=
                  '<button id="select" onclick="searchCarpool(' +
                  data[i].drIdx +
                  ')" class="btn rsvsbtn" data-bs-toggle="modal" data-bs-target="#selectModal">예약하기</button>';
                html += "</div>";
              }
            }
            $("#searchCarpoolList").html(html);
          }
        });
      } else {
        swal("찾기실패!", "해당일자에 등록된 카풀이 없습니다.", "warning");
      }
    },
  });
});

function reservation() {

  IMP.init("imp58566348");

  $.ajax({	// 탑승자 결제 데이터 요청
    type: 'GET',
    url: '/passenger/carpoolingPay/rqPay'
  }).done(function (user) {
    const data = {
      pg: 'html5_inicis', // PG사 선택
      pay_method: 'card', // 지불 수단
      merchant_uid: 'carpooling_' + new Date().getTime(), //가맹점에서 구별할 수 있는 고유한id
      name: 'CarPooling 요금 선결제', // 상품명
      amount: $('#dFee').val(), // 가격
      buyer_email: user.puserEmail, // 구매자 이메일
      buyer_name: user.puserName, // 구매자 이름
      buyer_tel: user.puserTel, // 구매자 연락처
      m_redirect_url: 'http://localhost:8080/passenger/carpoolingPay/mobile/complete', // 모바일 결제시 사용할 url
      digital: true // 실제 물품인지 무형의 상품인지(핸드폰 결제에서 필수 파라미터)
    };

    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay(data, function (rsp) {
      if (rsp.success) {	// 결제 승인 성공
        //console.log("결제 승인 완료");
        const imp_uid = rsp.imp_uid;
        //결제 검증 진행
        $.ajax({	//가맹점 서버 결제 API > 토큰 발급 > imp_uid에 해당하는 결제 정보 조회
          type: 'POST',
          url: '/passenger/carpoolingPay/verifyIamport/' + imp_uid
        }).done(function (result) {
          //console.log(result.response);
          var paymentData = result.response;
          if (rsp.paid_amount === paymentData.amount) {	//결제 요청 금액과 결제 처리 완료된 금액 비교
            //console.log("검증 성공 : 결제 완료");
            //DB에 결제 데이터 전송하기
            console.log("drIdx : " + $("#drIdx").val());
            $.ajax({
              url: "/passenger/passengerCarpool/reservation",
              type: "POST",
              data: { drIdx: $("#drIdx").val() },
              success: function (data, status) {
                if (status === "success") {
                  const payData = {
                    payIdx: paymentData.merchantUid,
                    rIdx: data,
                    amount: paymentData.amount,
                    receiptUrl: paymentData.receiptUrl
                  };
                  $.ajax({
                    url: "/passenger/carpoolingPay/complete",
                    type: "POST",
                    data: payData,
                    success: function (result) {
                      swal("예약성공!", "카풀 예약이 완료되었습니다.", "success").then(
                        (OK) => {
                          if (OK) {
                            window.location.reload();
                          }
                        }
                      );
                    }
                  });
                }
              }
            });
          } else {
            //console.log("검증 실패 : 결제 금액 확인 요망")
            //alert("에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);
          }
        }).fail(function (error) {
          //console.log("검증 불가 : 검증 로직 확인 요망" + error);
        })
      } else {	//결제 승인 실패
        //console.log("결제 승인 실패");
        alert("결제 실패했습니다." + "에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);
      }
    })
  })
}

function searchCarpool(drIdx) {
  $.ajax({
    url: "/passenger/passengerCarpool/reservation/search/" + drIdx,
    type: "GET",
    success: function (data) {
      $("#drIdx").val(data.drIdx);
      $("#dDate").val(data.ddate.substring(0, 10));
      $("#dStartTime").val(data.dstartTime);
      $("#dEndTime").val(data.dendTime);
      $("#dStartPoint").val(data.dstartPoint);
      $("#dEndPoint").val(data.dendPoint);
      $("#dFee").val(data.dfee);
    },
  });
}

// ------------------------TMAP API------------------------

var map, marker;
var markerArr = [];
var drawInfoArr = [];
var resultMarkerArr = [];
var resultdrawArr = [];

var dDrawInfoArr = [];
var dResultMarkerArr = [];
var dResultdrawArr = [];
var pResultMarkerArr = [];
var pResultdrawArr = [];

$(document).ready(function () {
  initTmap();
});

function initTmap() {
  map = new Tmapv2.Map("map_div", {
    center: new Tmapv2.LatLng(37.5652045, 126.98702028),
    width: "600px",
    height: "550px",
    zoom: 17,
    zoomControl: true,
    scrollwheel: true,
  });
}

function zoom(map, count) {
  for (let i = 0; i < count; i++) {
    map.zoomOut();
  }
}

// 장소검색
function searchPOI(location) {
  $("#routeCheck").css("display", "none");
  var locationPoint = location + "Point";
  var locationlon = location + "lon";
  var locationlat = location + "lat";

  var locationPointVal = $("#" + locationPoint).val();

  if (locationPointVal == "") {
    $("#routeCheck").css("display", "none");
    $(`#${ locationPoint }Check`).css("display", "block");
    $(`#${ locationPoint }`).focus();
    return;
  } else {
    $(`#${ locationPoint }Check`).css("display", "none");
  }

  $.ajax({
    method: "GET",
    url: "https://apis.openapi.sk.com/tmap/pois?version=1&format=json&callback=result",
    async: false,
    data: {
      appKey: "l7xx7b54bdec824142b3b3887c3917595b73",
      searchKeyword: locationPointVal,
      resCoordType: "EPSG3857",
      reqCoordType: "WGS84GEO",
      count: 10,
    },
    success: function (response) {
      var resultpoisData = response.searchPoiInfo.pois.poi;

      // ------ 초기화
      if (markerArr.length > 0) {
        for (var i in markerArr) {
          markerArr[i].setMap(null);
        }
      }

      if (pResultMarkerArr.length > 0) {
        for (var i = 0; i < pResultMarkerArr.length; i++) {
          pResultMarkerArr[i].setMap(null);
        }
      }

      if (dResultMarkerArr.length > 0) {
        for (var i = 0; i < dResultMarkerArr.length; i++) {
          dResultMarkerArr[i].setMap(null);
        }
      }

      if (pResultdrawArr.length > 0) {
        for (var i = 0; i < pResultdrawArr.length; i++) {
          pResultdrawArr[i].setMap(null);
        }
      }

      if (dResultdrawArr.length > 0) {
        for (var i = 0; i < dResultdrawArr.length; i++) {
          dResultdrawArr[i].setMap(null);
        }
      }

      drawInfoArr = [];
      pResultMarkerArr = [];
      dResultMarkerArr = [];
      pResultdrawArr = [];
      dResultdrawArr = [];

      // 초기화 ------

      // 맵에 결과물 확인 하기 위한 LatLngBounds객체 생성
      var positionBounds = new Tmapv2.LatLngBounds();

      // 마커찍기
      for (var k in resultpoisData) {
        var noorLat = Number(resultpoisData[k].noorLat);
        var noorLon = Number(resultpoisData[k].noorLon);
        var name = resultpoisData[k].name;

        var pointCng = new Tmapv2.Point(noorLon, noorLat);
        var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
          pointCng
        );

        var lat = projectionCng._lat;
        var lon = projectionCng._lng;

        var markerPosition = new Tmapv2.LatLng(lat, lon);
        marker = new Tmapv2.Marker({
          position: markerPosition,
          icon:
            "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_" +
            k +
            ".png",
          iconSize: new Tmapv2.Size(24, 38),
          title: name,
          map: map,
        });

        markerArr.push(marker); // LatLngBounds의 객체 확장
        positionBounds.extend(markerPosition);
      }

      for (let i = 0; i < markerArr.length; i++) {
        $(markerArr[i]._htmlElement).click(() => {
          const lat = markerArr[i]._marker_data.options.position._lat;
          const lng = markerArr[i]._marker_data.options.position._lng;
          $(`#${ location }`).val(markerArr[i]._marker_data.options.title);
          $(`#${ locationlon }`).val(lng);
          $(`#${ locationlat }`).val(lat);
        });
      }

      // 확장된 bounds의 중심으로 이동시키기
      map.panToBounds(positionBounds);
      map.zoomOut();
    },
    error: function (request, status, error) {
      console.log(
        "code:" +
        request.status +
        "\n" +
        "message:" +
        request.responseText +
        "\n" +
        "error:" +
        error
      );
    },
  });
}

function route() {
  var start = $("#start").val();
  var end = $("#end").val();

  if (start === "" && end === "") {
    $("#startPointSearchCheck").css("display", "none");
    $("#endPointSearchCheck").css("display", "none");
  } else if (start === "") {
    $("#searchCheck").css("display", "none");
    $("#startPointSearchCheck").css("display", "block");
    $("#startPoint").focus();
    return;
  } else if (end === "") {
    $("#searchCheck").css("display", "none");
    $("#endPointSearchCheck").css("display", "block");
    $("#endPoint").focus();
    return;
  } else {
    $("#searchCheck").css("display", "none");
  }

  // ------ 초기화
  if (markerArr.length > 0) {
    for (var i in markerArr) {
      markerArr[i].setMap(null);
    }
  }

  if (pResultMarkerArr.length > 0) {
    for (var i = 0; i < pResultMarkerArr.length; i++) {
      pResultMarkerArr[i].setMap(null);
    }
  }

  if (dResultMarkerArr.length > 0) {
    for (var i = 0; i < dResultMarkerArr.length; i++) {
      dResultMarkerArr[i].setMap(null);
    }
  }

  if (pResultdrawArr.length > 0) {
    for (var i = 0; i < pResultdrawArr.length; i++) {
      pResultdrawArr[i].setMap(null);
    }
  }

  if (dResultdrawArr.length > 0) {
    for (var i = 0; i < dResultdrawArr.length; i++) {
      dResultdrawArr[i].setMap(null);
    }
  }

  drawInfoArr = [];
  pResultMarkerArr = [];
  dResultMarkerArr = [];
  pResultdrawArr = [];
  dResultdrawArr = [];

  // 초기화 ------

  var startX = $("#startlon").val();
  var startY = $("#startlat").val();
  var endX = $("#endlon").val();
  var endY = $("#endlat").val();

  $.ajax({
    type: "POST",
    url: "https://apis.openapi.sk.com/tmap/routes?version=1&format=json&callback=result",
    async: false,
    data: {
      appKey: "l7xx7b54bdec824142b3b3887c3917595b73",
      startX: startX,
      startY: startY,
      endX: endX,
      endY: endY,
      reqCoordType: "WGS84GEO",
      resCoordType: "EPSG3857",
    },
    success: function (response) {
      var x;
      var y;

      var numberSX = parseFloat(startX);
      var numberSY = parseFloat(startY);
      var numberEX = parseFloat(endX);
      var numberEY = parseFloat(endY);

      if (numberSX > numberEX) {
        var x = numberEX + (numberSX - numberEX) / 2;
      } else {
        var x = numberSX + (numberEX - numberSX) / 2;
      }

      if (numberSY > numberEY) {
        var y = numberEY + (numberSY - numberEY) / 2;
      } else {
        var y = numberSY + (numberEY - numberSY) / 2;
      }

      var lonlat = new Tmapv2.LatLng(y, x);
      map.setCenter(lonlat);

      var resultData = response.features;
      var tDistance = (resultData[0].properties.totalDistance / 1000).toFixed(
        1
      );
      if (tDistance < 10) {
        zoom(map, 2);
      } else if (tDistance < 20) {
        zoom(map, 3);
      } else if (tDistance < 30) {
        zoom(map, 4);
      } else if (tDistance < 40) {
        zoom(map, 5);
      } else {
        zoom(map, 6);
      }

      var tFare = resultData[0].properties.taxiFare;
      $("#fare").val(parseInt(tFare) * 0.6);

      for (var i in resultData) {
        //for문 [S]
        var geometry = resultData[i].geometry;
        var properties = resultData[i].properties;

        if (geometry.type == "LineString") {
          for (var j in geometry.coordinates) {
            // 경로들의 결과값들을 포인트 객체로 변환
            var latlng = new Tmapv2.Point(
              geometry.coordinates[j][0],
              geometry.coordinates[j][1]
            );
            // 포인트 객체를 받아 좌표값으로 변환
            var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
              latlng
            );
            // 포인트객체의 정보로 좌표값 변환 객체로 저장
            var convertChange = new Tmapv2.LatLng(
              convertPoint._lat,
              convertPoint._lng
            );
            // 배열에 담기
            drawInfoArr.push(convertChange);
          }
          drawLine(drawInfoArr, "0");
        } else {
          var markerImg = "";
          var pType = "";

          if (properties.pointType == "S") {
            //출발지 마커
            markerImg =
              "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png";
            pType = "S";
          } else if (properties.pointType == "E") {
            //도착지 마커
            markerImg =
              "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png";
            pType = "E";
          } else {
            //각 포인트 마커
            markerImg = "http://topopen.tmap.co.kr/imgs/point.png";
            pType = "P";
          }

          // 경로들의 결과값들을 포인트 객체로 변환
          var latlon = new Tmapv2.Point(
            geometry.coordinates[0],
            geometry.coordinates[1]
          );
          // 포인트 객체를 받아 좌표값으로 다시 변환
          var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
            latlon
          );

          var routeInfoObj = {
            markerImage: markerImg,
            lng: convertPoint._lng,
            lat: convertPoint._lat,
            pointType: pType,
          };

          // Marker 추가
          addMarkers(routeInfoObj);
        }
      } //for문 [E]
    },
    error: function (request, status, error) {
      console.log(
        "code:" +
        request.status +
        "\n" +
        "message:" +
        request.responseText +
        "\n" +
        "error:" +
        error
      );
    },
  });

  //마커 생성하기
  function addMarkers(infoObj) {
    var size = new Tmapv2.Size(24, 38); //아이콘 크기 설정합니다.

    if (infoObj.pointType == "P") {
      //포인트점일때는 아이콘 크기를 줄입니다.
      size = new Tmapv2.Size(8, 8);
    }

    marker_p = new Tmapv2.Marker({
      position: new Tmapv2.LatLng(infoObj.lat, infoObj.lng),
      icon: infoObj.markerImage,
      iconSize: size,
      map: map,
    });

    pResultMarkerArr.push(marker_p);
  }

  //라인그리기
  function drawLine(arrPoint, traffic) {
    var polyline_ = new Tmapv2.Polyline({
      path: arrPoint,
      strokeColor: "#DD0000",
      strokeWeight: 6,
      map: map,
    });
    pResultdrawArr.push(polyline_);
  }
}

function dRoute(d_startlon, d_startlat, d_endlon, d_endlat) {
  // ------ 초기화 ------
  if (dResultMarkerArr.length > 0) {
    for (var i = 0; i < dResultMarkerArr.length; i++) {
      dResultMarkerArr[i].setMap(null);
    }
  }

  if (dResultdrawArr.length > 0) {
    for (var i = 0; i < dResultdrawArr.length; i++) {
      dResultdrawArr[i].setMap(null);
    }
  }

  dResultMarkerArr = [];
  dDrawInfoArr = [];
  dResultdrawArr = [];

  $.ajax({
    type: "POST",
    url: "https://apis.openapi.sk.com/tmap/routes?version=1&format=json&callback=result",
    async: false,
    data: {
      appKey: "l7xx7b54bdec824142b3b3887c3917595b73",
      startX: d_startlon,
      startY: d_startlat,
      endX: d_endlon,
      endY: d_endlat,
      reqCoordType: "WGS84GEO",
      resCoordType: "EPSG3857",
    },
    success: function (response) {
      var x;
      var y;

      var numberSX = parseFloat(d_startlon);
      var numberSY = parseFloat(d_startlat);
      var numberEX = parseFloat(d_endlon);
      var numberEY = parseFloat(d_endlat);

      if (numberSX > numberEX) {
        var x = numberEX + (numberSX - numberEX) / 2;
      } else {
        var x = numberSX + (numberEX - numberSX) / 2;
      }

      if (numberSY > numberEY) {
        var y = numberEY + (numberSY - numberEY) / 2;
      } else {
        var y = numberSY + (numberEY - numberSY) / 2;
      }

      var lonlat = new Tmapv2.LatLng(y, x);
      map.setCenter(lonlat);

      var resultData = response.features;
      console.log(resultData);
      var tDistance = (resultData[0].properties.totalDistance / 1000).toFixed(
        1
      );
      if (tDistance < 10) {
        zoom(map, 2);
      } else if (tDistance < 20) {
        zoom(map, 3);
      } else if (tDistance < 30) {
        zoom(map, 4);
      } else if (tDistance < 40) {
        zoom(map, 5);
      } else {
        zoom(map, 6);
      }

      for (var i in resultData) {
        //for문 [S]
        var geometry = resultData[i].geometry;
        var properties = resultData[i].properties;

        if (geometry.type == "LineString") {
          for (var j in geometry.coordinates) {
            // 경로들의 결과값들을 포인트 객체로 변환
            var latlng = new Tmapv2.Point(
              geometry.coordinates[j][0],
              geometry.coordinates[j][1]
            );
            // 포인트 객체를 받아 좌표값으로 변환
            var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
              latlng
            );
            // 포인트객체의 정보로 좌표값 변환 객체로 저장
            var convertChange = new Tmapv2.LatLng(
              convertPoint._lat,
              convertPoint._lng
            );
            // 배열에 담기
            dDrawInfoArr.push(convertChange);
          }
          dDrawLine(dDrawInfoArr, "0");
        } else {
          var markerImg = "";
          var pType = "";

          if (properties.pointType == "S") {
            //출발지 마커
            markerImg =
              "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_s.png";
            pType = "S";
          } else if (properties.pointType == "E") {
            //도착지 마커
            markerImg =
              "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_e.png";
            pType = "E";
          } else {
            //각 포인트 마커
            markerImg = "http://topopen.tmap.co.kr/imgs/point.png";
            pType = "P";
          }

          // 경로들의 결과값들을 포인트 객체로 변환
          var latlon = new Tmapv2.Point(
            geometry.coordinates[0],
            geometry.coordinates[1]
          );
          // 포인트 객체를 받아 좌표값으로 다시 변환
          var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
            latlon
          );

          var routeInfoObj = {
            markerImage: markerImg,
            lng: convertPoint._lng,
            lat: convertPoint._lat,
            pointType: pType,
          };

          // Marker 추가
          addMarkers(routeInfoObj);
        }
      } //for문 [E]
    },
    error: function (request, status, error) {
      console.log(
        "code:" +
        request.status +
        "\n" +
        "message:" +
        request.responseText +
        "\n" +
        "error:" +
        error
      );
    },
  });

  //마커 생성하기
  function addMarkers(infoObj) {
    var size = new Tmapv2.Size(24, 38); //아이콘 크기 설정합니다.

    if (infoObj.pointType == "P") {
      //포인트점일때는 아이콘 크기를 줄입니다.
      size = new Tmapv2.Size(8, 8);
    }

    marker_p = new Tmapv2.Marker({
      position: new Tmapv2.LatLng(infoObj.lat, infoObj.lng),
      icon: infoObj.markerImage,
      iconSize: size,
      map: map,
    });

    dResultMarkerArr.push(marker_p);
  }

  //라인그리기
  function dDrawLine(arrPoint, traffic) {
    var polyline_ = new Tmapv2.Polyline({
      path: arrPoint,
      strokeColor: "#2979ff",
      strokeWeight: 6,
      map: map,
    });
    dResultdrawArr.push(polyline_);
  }
}
