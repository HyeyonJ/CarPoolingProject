// jQuery datepicker
$(function () {
  $("#datepicker").datepicker({
    dateFormat: "yy-mm-dd",
  });
});

// 출근에 따른 변화
$("#goWork").change(() => {
  $("#workCheck").css("display", "none");
  // prop('checked') = 만약 goWork가 checked 상태이면 true값 반환
  // -> 출근 체크하면 출근 option이 보이게, 퇴근 option은 안보이게
  if ($("#goWork").prop("checked") == true) {
    $(".go_work_start_time").css("display", "block");
    $(".leave_work_start_time").css("display", "none");
    $(".go_work_end_time").css("display", "block");
    $(".leave_work_end_time").css("display", "none");
    $("#selectStartTime").val("선택");
    $("#selectEndTime").val("선택");
  }
});

// 퇴근에 따른 변화
$("#leaveWork").change(() => {
  $("#workCheck").css("display", "none");
  // prop('checked') = 만약 leaveWork가 checked 상태이면 true값 반환
  // -> 출근 체크하면 퇴근 option이 보이게, 출근 option은 안보이게
  if ($("#leaveWork").prop("checked") == true) {
    $(".go_work_start_time").css("display", "none");
    $(".leave_work_start_time").css("display", "block");
    $(".go_work_end_time").css("display", "none");
    $(".leave_work_end_time").css("display", "block");
    $("#selectStartTime").val("선택");
    $("#selectEndTime").val("선택");
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

function timeCheck(today, selectStartTimeVal) {
  var hours = today.getHours().toString();
  var minutes = today.getMinutes().toString();
  if (hours < 10) {
    hours = "0" + hours;
  }
  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  const drHours = selectStartTimeVal.substring(0, 2);
  const drMinutes = selectStartTimeVal.substring(3, 5);
  // 운행시간이 현재시간 이전이거나 시간은 같은데 운행분이 현재분 이전일 때
  if (hours > drHours || (hours === drHours && minutes > drMinutes)) {
    // 시간을 다시 선택해주세요. - 보여주기
    $("#timeRecheck").css("display", "block");
    // val 초기화
    $("#selectStartTime").val("선택");
    $("#selectEndTime").val("선택");
    return false;
  } else {
    // 시간을 다시 선택해주세요. - 숨기기
    $("#timeRecheck").css("display", "none");
  }
}

function commuteCheck(selectTime) {
  $("#timeCheck").css("display", "none");
  if ($(':radio[name="work"]:checked').length < 1) {
    // 출근 또는 퇴근을 선택해주세요. - 보여주기
    $("#workCheck").css("display", "block");
    // 바로 focusOut 하기
    $(`#${selectTime}`).blur();
    return false;
  } else {
    // 출근 또는 퇴근을 선택해주세요. - 숨기기
    $("#workCheck").css("display", "none");
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
  const drYear = dateVal.substring(0, 4);
  const drMonth = dateVal.substring(5, 7);
  const drDate = dateVal.substring(8, 10);

  // 운행날짜가 현재날짜 이전일 때
  if (year + month + date > drYear + drMonth + drDate) {
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

  // 운행날짜가 현재날짜와 같을 때 시간비교
  const selectStartTimeVal = $("#selectStartTime").val();
  // 운행날짜가 현재날짜와 같고 시간이 선택되어져있을 때
  if (
    year + month + date === drYear + drMonth + drDate &&
    selectStartTimeVal !== "선택"
  ) {
    timeCheck(today, selectStartTimeVal);
  }
});

$("#selectStartTime").change(() => {
  const selectStartTime =
    $("#selectStartTime").val().substring(0, 2) +
    $("#selectStartTime").val().substring(3, 5);
  const selectEndTime =
    $("#selectEndTime").val().substring(0, 2) +
    $("#selectEndTime").val().substring(3, 5);

  // 출발시간이 도착시간 이후일 때
  if (selectStartTime > selectEndTime) {
    // 도착시간을 다시 선택해주세요. - 보여주기
    $("#endTimeRecheck").css("display", "block");
    // val 초기화
    $("#selectEndTime").val("선택");
    return false;
  } else {
    // 도착시간을 다시 선택해주세요. - 숨기기
    $("#endTimeRecheck").css("display", "none");
    // 출발시간을 먼저 선택해주세요. - 숨기기 -> block인 경우도 있으니까
    $("#startTimeCheck").css("display", "none");
  }

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

  // 운행날짜
  const dateVal = $("#datepicker").val();
  const drYear = dateVal.substring(0, 4);
  const drMonth = dateVal.substring(5, 7);
  const drDate = dateVal.substring(8, 10);

  // 운행날짜가 ""이 아닐 때
  if ($("#datepicker").val() !== "") {
    // 운행날짜가 현재날짜와 같을 때
    if (year + month + date === drYear + drMonth + drDate) {
      const selectStartTimeVal = $("#selectStartTime").val();
      timeCheck(today, selectStartTimeVal);
    }
  }
});

$("#selectEndTime").change(() => {
  const selectStartTime =
    $("#selectStartTime").val().substring(0, 2) +
    $("#selectStartTime").val().substring(3, 5);
  const selectEndTime =
    $("#selectEndTime").val().substring(0, 2) +
    $("#selectEndTime").val().substring(3, 5);

  // 출발시간이 "선택"일때
  if (selectStartTime === "선택") {
    // 출발시간을 먼저 선택해주세요. - 보여주기
    $("#startTimeCheck").css("display", "block");
    // val 초기화
    $("#selectEndTime").val("선택");
    return false;
  } else {
    // 출발시간을 먼저 선택해주세요. - 숨기기
    $("#startTimeCheck").css("display", "none");
  }

  // 출발시간이 도착시간 이후일 때
  if (selectStartTime > selectEndTime) {
    // 도착시간을 다시 선택해주세요. - 보여주기
    $("#endTimeRecheck").css("display", "block");
    // val 초기화
    $("#selectEndTime").val("선택");
    return false;
  } else {
    // 도착시간을 다시 선택해주세요. - 숨기기
    $("#endTimeRecheck").css("display", "none");
  }
});

$("#selectStartTime").click(() => {
  commuteCheck("selectStartTime");
});
$("#selectEndTime").click(() => {
  commuteCheck("selectEndTime");
});

$("#rgsBtn").click(function () {
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

  // 출발시간 or 도착시간 선택이 안되어있을 때
  if (
    $("#selectStartTime").val() == "선택" ||
    $("#selectEndTime").val() == "선택"
  ) {
    $("#timeCheck").css("display", "block");
    // 도착시간을 다시 선택해주세요. - 숨기기 -> block인 경우도 있으니까
    $("#endTimeRecheck").css("display", "none");
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

  const dRegistraion = {
    dDate: $("#datepicker").val().toString(),
    dHopeGender: $("input:radio[name='gender']:checked").val().toString(),
    dCommute: $("input:radio[name='work']:checked").val().toString(),
    dStartTime: $("#selectStartTime").val(),
    dEndTime: $("#selectEndTime").val(),
    dStartPoint: $("#start").val().toString(),
    dEndPoint: $("#end").val().toString(),
    dFee: $("#fare").val(),
    dDistance: $("#distance").val(),
    dTime: $("#time").val(),
    dStartLon: $("#startlon").val(),
    dStartLat: $("#startlat").val(),
    dEndLon: $("#endlon").val(),
    dEndLat: $("#endlat").val(),
  };

  $.ajax({
    url: "/driver/driverCarpool/registration",
    type: "POST",
    data: dRegistraion,
    success: function (data) {
      console.log(data);
      if (data === "insert") {
        swal("등록성공!", "안전운행해주시기 바랍니다.", "success").then(
          (OK) => {
            if (OK) {
              window.location.reload();
            }
          }
        );
      } else if (data === "출근") {
        swal(
          "등록실패!",
          "해당일자에 이미 출근카풀등록내역이 있습니다. \n 카풀등록은 하루 총 2회 출근 1회, 퇴근 1회만 가능합니다.",
          "warning"
        );
      } else if (data === "퇴근") {
        swal(
          "등록실패!",
          "해당일자에 이미 퇴근카풀등록내역이 있습니다. \n 카풀등록은 하루 총 2회 출근 1회, 퇴근 1회만 가능합니다.",
          "warning"
        );
      } else {
        swal("등록실패!", "카풀등록에 실패했습니다.", "error");
      }
    },
  });
});

// ------------------------TMAP API------------------------

var map, marker;
var markerArr = [];
var drawInfoArr = [];
var resultMarkerArr = [];
var resultdrawArr = [];

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
    $(`#${locationPoint}Check`).css("display", "block");
    $(`#${locationPoint}`).focus();
    return;
  } else {
    $(`#${locationPoint}Check`).css("display", "none");
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

      if (resultMarkerArr.length > 0) {
        for (var i = 0; i < resultMarkerArr.length; i++) {
          resultMarkerArr[i].setMap(null);
        }
      }

      if (resultdrawArr.length > 0) {
        for (var i = 0; i < resultdrawArr.length; i++) {
          resultdrawArr[i].setMap(null);
        }
      }

      drawInfoArr = [];
      resultMarkerArr = [];
      resultdrawArr = [];

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
          $(`#${location}`).val(markerArr[i]._marker_data.options.title);
          $(`#${locationlon}`).val(lng);
          $(`#${locationlat}`).val(lat);
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

  if (resultMarkerArr.length > 0) {
    for (var i = 0; i < resultMarkerArr.length; i++) {
      resultMarkerArr[i].setMap(null);
    }
  }

  if (resultdrawArr.length > 0) {
    for (var i = 0; i < resultdrawArr.length; i++) {
      resultdrawArr[i].setMap(null);
    }
  }

  drawInfoArr = [];
  resultMarkerArr = [];
  resultdrawArr = [];

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
        map.zoomOut();
      } else if (tDistance < 20) {
        zoom(map, 3);
      } else if (tDistance < 30) {
        zoom(map, 4);
      } else if (tDistance < 40) {
        zoom(map, 5);
      } else {
        zoom(map, 6);
      }

      var tTime = (resultData[0].properties.totalTime / 60).toFixed(0);
      var tFare = resultData[0].properties.taxiFare;

      $("#distance").val(parseFloat(tDistance));
      $("#time").val(parseInt(tTime));
      $("#fare").val(parseInt(tFare) * 0.6);

      for (var i in resultData) {
        //for문 [S]
        var geometry = resultData[i].geometry;
        var properties = resultData[i].properties;

        if (geometry.type == "LineString") {
          for (var j in geometry.coordinates) {
            var latlng = new Tmapv2.Point(
              geometry.coordinates[j][0],
              geometry.coordinates[j][1]
            );
            var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
              latlng
            );
            var convertChange = new Tmapv2.LatLng(
              convertPoint._lat,
              convertPoint._lng
            );
            drawInfoArr.push(convertChange);
          }
          drawLine(drawInfoArr);
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

    resultMarkerArr.push(marker_p);
  }

  //라인그리기
  function drawLine(arrPoint) {
    var polyline_ = new Tmapv2.Polyline({
      path: arrPoint,
      strokeColor: "#DD0000",
      strokeWeight: 6,
      map: map,
    });
    resultdrawArr.push(polyline_);
  }
}
