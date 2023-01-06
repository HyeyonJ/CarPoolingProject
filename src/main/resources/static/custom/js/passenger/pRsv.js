$(function () {
  $("#datepicker").datepicker({
    dateFormat: 'yy-mm-dd'
  });
});

$(document).ready(function () {
  initTmap();

  $('#selectModal').on('hide.bs.modal', function (e) {
    $(this).find('.modal-body form')[0].reset();
    //폼 초기화 
  });

});


// 출근에 따른 변화
$('#towork').change(() => {
  // prop('checked') = 만약 towork가 checked 상태이면 true값 반환
  if ($('#towork').prop('checked') == true) {
    $('.toworktime').css('display', 'block');
    $('.fromworktime').css('display', 'none');
    $('.toworktimes').css('display', 'block');
    $('.fromworktimes').css('display', 'none');
    $('#tocommute').val("선택");
    $('#tocommutes').val("선택");
  }
  if ($("#boardingTime").val().substring(0, 2) > 9) {
    $("#boardingTime").val("선택");
  }
});

// 퇴근에 따른 변화
$('#fromwork').change(() => {
  // prop('checked') = 만약 fromwork가 checked 상태이면 true값 반환
  if ($('#fromwork').prop('checked') == true) {
    $('.toworktime').css('display', 'none');
    $('.fromworktime').css('display', 'block');
    $('.toworktimes').css('display', 'none');
    $('.fromworktimes').css('display', 'block');
    $('#tocommute').val("선택");
    $('#tocommutes').val("선택");
  }
  if ($("#boardingTime").val().substring(0, 2) < 18) {
    $("#boardingTime").val("선택");
  }
});

$("#datepicker").change(() => {
  // 현재날짜
  var today = new Date();
  var year = today.getFullYear().toString(); // 년도
  var month = (today.getMonth() + 1).toString();  // 월
  var date = today.getDate().toString();  // 날짜
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

  if (year + month + date > drYear + drMonth + drDate) {
    $("#dateCheck").css('display', 'none');
    $('#dateRecheck').css('display', 'block');
    $("#datepicker").val("");
    return false;
  } else {
    $('#dateRecheck').css('display', 'none');
  }

  const boardingTimeVal = $("#boardingTime").val();

  if ((year + month + date === drYear + drMonth + drDate) && boardingTimeVal !== "선택") {
    var hours = today.getHours().toString();
    var minutes = today.getMinutes().toString();
    if (hours < 10) {
      hours = "0" + hours;
    }
    if (minutes < 10) {
      minutes = "0" + minutes;
    }

    const drHour = boardingTimeVal.substring(0, 2);
    const drMinute = boardingTimeVal.substring(3, 5);

    if (hours > drHour || (hours === drHour && minutes > drMinute)) {
      console.log("2번들어옴");
      $('#timeRecheck').css('display', 'block');
      $("#boardingTime").val("선택");
      return false;
    } else {
      console.log("3번들어옴");
      $('#timeRecheck').css('display', 'none');
    }
  }
})


$("#boardingTime").change(() => {
  var today = new Date();
  var year = today.getFullYear().toString();
  var month = (today.getMonth() + 1).toString();
  var date = today.getDate().toString();
  var hours = today.getHours().toString();
  var minutes = today.getMinutes().toString();
  if (month < 10) {
    month = "0" + month;
  }
  if (date < 10) {
    date = "0" + date;
  }
  if (hours < 10) {
    hours = "0" + hours;
  }
  if (minutes < 10) {
    minutes = "0" + minutes;
  }

  const dateVal = $("#datepicker").val();
  const drYear = dateVal.substring(0, 4);
  const drMonth = dateVal.substring(5, 7);
  const drDate = dateVal.substring(8, 10);

  if ($("#datepicker").val() !== null) {

    if (year + month + date === drYear + drMonth + drDate) {

      const boardingTimeVal = $("#boardingTime").val();
      const drHour = boardingTimeVal.substring(0, 2);
      const drMinute = boardingTimeVal.substring(3, 5);

      if (hours > drHour || (hours === drHour && minutes > drMinute)) {
        $('#timeRecheck').css('display', 'block');
        $("#boardingTime").val("선택");
        return false;
      } else {
        $('#timeRecheck').css('display', 'none');
      }
    }
  }
})

$("#boardingTime").click(() => {
  if ($(':radio[name="work"]:checked').length < 1) {
    $('#workCheck').css('display', 'block');
    $("#boardingTime").blur();
    return false;
  } else {
    $('#workCheck').css('display', 'none');
  }
})

// ------------TMAP API------------
var map, marker;
var markerArr = [];

var drawInfoArr = [];

var dDrawInfoArr = [];
var dResultMarkerArr = [];
var dResultdrawArr = [];
var pResultMarkerArr = [];
var pResultdrawArr = [];


function initTmap() {
  // 1. 지도 띄우기
  map = new Tmapv2.Map("map_div", {
    center: new Tmapv2.LatLng(37.56520450, 126.98702028),
    width: "70%",
    height: "400px",
    zoom: 17,
    zoomControl: true,
    scrollwheel: true

  });
}

function searchPOIS() {
  var startPoint = $('#startPoint').val();

  if (startPoint == "") {
    alert("출발할 장소를 입력해주세요.");
    $('#startPoint').focus();
    return;
  }

  $.ajax({
    method: "GET",
    url: "https://apis.openapi.sk.com/tmap/pois?version=1&format=json&callback=result",
    async: false,
    data: {
      "appKey": "l7xx7b54bdec824142b3b3887c3917595b73",
      "searchKeyword": startPoint,
      "resCoordType": "EPSG3857",
      "reqCoordType": "WGS84GEO",
      "count": 10
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
        var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(pointCng);

        var lat = projectionCng._lat;
        var lon = projectionCng._lng;

        var markerPosition = new Tmapv2.LatLng(lat, lon);
        marker = new Tmapv2.Marker({
          position: markerPosition,
          icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_" + k + ".png",
          iconSize: new Tmapv2.Size(24, 38),
          title: name,
          map: map
        });

        markerArr.push(marker);
        // LatLngBounds의 객체 확장
        positionBounds.extend(markerPosition);
      }

      for (let i = 0; i < markerArr.length; i++) {
        $(markerArr[i]._htmlElement).click(() => {
          const slat = markerArr[i]._marker_data.options.position._lat;
          const slng = markerArr[i]._marker_data.options.position._lng;
          $("#start").val(markerArr[i]._marker_data.options.title);
          $('#startlon').val(slng);
          $('#startlat').val(slat);
          // map.destroy();
          // initTmap();
        })
      }

      // 확장된 bounds의 중심으로 이동시키기
      map.panToBounds(positionBounds);
      map.zoomOut();
    },
    error: function (request, status, error) {
      console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
    }
  });
}

function searchPOIE() {
  var endPoint = $('#endPoint').val();

  if (endPoint == "") {
    alert("도착할 장소를 입력해주세요.");
    $('#endPoint').focus();
    return;
  }

  $.ajax({
    method: "GET",
    url: "https://apis.openapi.sk.com/tmap/pois?version=1&format=json&callback=result",
    async: false,
    data: {
      "appKey": "l7xx7b54bdec824142b3b3887c3917595b73",
      "searchKeyword": endPoint,
      "resCoordType": "EPSG3857",
      "reqCoordType": "WGS84GEO",
      "count": 10
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
        var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(pointCng);

        var lat = projectionCng._lat;
        var lon = projectionCng._lng;

        var markerPosition = new Tmapv2.LatLng(lat, lon);
        marker = new Tmapv2.Marker({
          position: markerPosition,
          icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_" + k + ".png",
          iconSize: new Tmapv2.Size(24, 38),
          title: name,
          map: map
        });

        markerArr.push(marker);              // LatLngBounds의 객체 확장
        positionBounds.extend(markerPosition);
      }

      for (let i = 0; i < markerArr.length; i++) {
        $(markerArr[i]._htmlElement).click(() => {
          const elat = markerArr[i]._marker_data.options.position._lat;
          const elng = markerArr[i]._marker_data.options.position._lng;
          $("#end").val(markerArr[i]._marker_data.options.title);
          $('#endlon').val(elng);
          $('#endlat').val(elat);
          // map.destroy();
          // initTmap();
        })
      }

      // 확장된 bounds의 중심으로 이동시키기
      map.panToBounds(positionBounds);
      map.zoomOut();
    },
    error: function (request, status, error) {
      console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
    }
  });
}

function route() {

  var start = $('#start').val();
  if (start == "") {
    alert("도착할 장소를 검색 후 선택해주세요.");
    $('#startPoint').focus();
    return;
  }

  var end = $('#end').val();
  if (end == "") {
    alert("도착할 장소를 검색 후 선택해주세요.");
    $('#endPoint').focus();
    return;
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

  var startX = $('#startlon').val();
  var startY = $('#startlat').val();
  var endX = $('#endlon').val();
  var endY = $('#endlat').val();

  $
    .ajax({
      type: "POST",
      url: "https://apis.openapi.sk.com/tmap/routes?version=1&format=json&callback=result",
      async: false,
      data: {
        "appKey": "l7xx7b54bdec824142b3b3887c3917595b73",
        "startX": startX,
        "startY": startY,
        "endX": endX,
        "endY": endY,
        "reqCoordType": "WGS84GEO",
        "resCoordType": "EPSG3857",
      },
      success: function (response) {

        var positionBounds = new Tmapv2.LatLngBounds();		//맵에 결과물 확인 하기 

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
          var y = numberSY + ((numberEY - numberSY) / 2);
        }

        var lonlat = new Tmapv2.LatLng(y, x);
        map.setCenter(lonlat);

        var resultData = response.features;
        console.log(resultData);
        var distance = (resultData[0].properties.totalDistance / 1000);
        if (distance < 10) {
          console.log("10km미만");
          map.zoomOut();
        } else if (distance < 20) {
          console.log("20km미만");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        } else if (distance < 30) {
          console.log("30km미만");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        } else if (distance < 40) {
          console.log("40km미만");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        } else {
          console.log("40km이상");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        }

        var tDistance = (resultData[0].properties.totalDistance / 1000).toFixed(1);
        var tTime = (resultData[0].properties.totalTime / 60).toFixed(0);
        var tFare = resultData[0].properties.taxiFare;

        //택시비의 60프로 저렴한 가격
        $('#fare').val(parseInt(tFare) * 0.6);

        for (var i in resultData) { //for문 [S]
          var geometry = resultData[i].geometry;
          var properties = resultData[i].properties;

          if (geometry.type == "LineString") {
            for (var j in geometry.coordinates) {
              // 경로들의 결과값들을 포인트 객체로 변환 
              var latlng = new Tmapv2.Point(
                geometry.coordinates[j][0],
                geometry.coordinates[j][1]);
              // 포인트 객체를 받아 좌표값으로 변환
              var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
                latlng);
              // 포인트객체의 정보로 좌표값 변환 객체로 저장
              var convertChange = new Tmapv2.LatLng(
                convertPoint._lat,
                convertPoint._lng);
              // 배열에 담기
              drawInfoArr
                .push(convertChange);
            }
            drawLine(drawInfoArr,
              "0");
          } else {

            var markerImg = "";
            var pType = "";

            if (properties.pointType == "S") { //출발지 마커
              markerImg = "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png";
              pType = "S";
            } else if (properties.pointType == "E") { //도착지 마커
              markerImg = "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png";
              pType = "E";
            } else { //각 포인트 마커
              markerImg = "http://topopen.tmap.co.kr/imgs/point.png";
              pType = "P"
            }

            // 경로들의 결과값들을 포인트 객체로 변환 
            var latlon = new Tmapv2.Point(
              geometry.coordinates[0],
              geometry.coordinates[1]);
            // 포인트 객체를 받아 좌표값으로 다시 변환
            var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
              latlon);

            var routeInfoObj = {
              markerImage: markerImg,
              lng: convertPoint._lng,
              lat: convertPoint._lat,
              pointType: pType
            };

            // Marker 추가
            addMarkers(routeInfoObj);
          }
        }//for문 [E]

      },
      error: function (request, status, error) {
        console.log("code:"
          + request.status + "\n"
          + "message:"
          + request.responseText
          + "\n" + "error:" + error);
      }
    });

  //마커 생성하기
  function addMarkers(infoObj) {
    var size = new Tmapv2.Size(24, 38);//아이콘 크기 설정합니다.

    if (infoObj.pointType == "P") { //포인트점일때는 아이콘 크기를 줄입니다.
      size = new Tmapv2.Size(8, 8);
    }

    marker_p = new Tmapv2.Marker({
      position: new Tmapv2.LatLng(infoObj.lat, infoObj.lng),
      icon: infoObj.markerImage,
      iconSize: size,
      map: map
    });

    pResultMarkerArr.push(marker_p);
  }

  //라인그리기
  function drawLine(arrPoint, traffic) {
    var polyline_ = new Tmapv2.Polyline({
      path: arrPoint,
      strokeColor: "#DD0000",
      strokeWeight: 6,
      map: map
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


  $
    .ajax({
      type: "POST",
      url: "https://apis.openapi.sk.com/tmap/routes?version=1&format=json&callback=result",
      async: false,
      data: {
        "appKey": "l7xx7b54bdec824142b3b3887c3917595b73",
        "startX": d_startlon,
        "startY": d_startlat,
        "endX": d_endlon,
        "endY": d_endlat,
        "reqCoordType": "WGS84GEO",
        "resCoordType": "EPSG3857",
      },
      success: function (response) {

        var positionBounds = new Tmapv2.LatLngBounds();		//맵에 결과물 확인 하기 

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
          var y = numberSY + ((numberEY - numberSY) / 2);
        }

        var lonlat = new Tmapv2.LatLng(y, x);
        map.setCenter(lonlat);

        var resultData = response.features;
        console.log(resultData);
        var distance = (resultData[0].properties.totalDistance / 1000);
        if (distance < 10) {
          console.log("10km미만");
          map.zoomOut();
        } else if (distance < 20) {
          console.log("20km미만");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        } else if (distance < 30) {
          console.log("30km미만");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        } else if (distance < 40) {
          console.log("40km미만");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        } else {
          console.log("40km이상");
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
          map.zoomOut();
        }

        var tDistance = (resultData[0].properties.totalDistance / 1000).toFixed(1);
        var tTime = (resultData[0].properties.totalTime / 60).toFixed(0);
        var tFare = resultData[0].properties.taxiFare;

        //택시비의 60프로 저렴한 가격
        $('#fare').val(parseInt(tFare) * 0.6);

        for (var i in resultData) { //for문 [S]
          var geometry = resultData[i].geometry;
          var properties = resultData[i].properties;

          if (geometry.type == "LineString") {
            for (var j in geometry.coordinates) {
              // 경로들의 결과값들을 포인트 객체로 변환 
              var latlng = new Tmapv2.Point(
                geometry.coordinates[j][0],
                geometry.coordinates[j][1]);
              // 포인트 객체를 받아 좌표값으로 변환
              var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
                latlng);
              // 포인트객체의 정보로 좌표값 변환 객체로 저장
              var convertChange = new Tmapv2.LatLng(
                convertPoint._lat,
                convertPoint._lng);
              // 배열에 담기
              dDrawInfoArr.push(convertChange);
            }
            dDrawLine(dDrawInfoArr, "0");
          } else {

            var markerImg = "";
            var pType = "";

            if (properties.pointType == "S") { //출발지 마커
              markerImg = "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_s.png";
              pType = "S";
            } else if (properties.pointType == "E") { //도착지 마커
              markerImg = "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_e.png";
              pType = "E";
            } else { //각 포인트 마커
              markerImg = "http://topopen.tmap.co.kr/imgs/point.png";
              pType = "P"
            }

            // 경로들의 결과값들을 포인트 객체로 변환 
            var latlon = new Tmapv2.Point(
              geometry.coordinates[0],
              geometry.coordinates[1]);
            // 포인트 객체를 받아 좌표값으로 다시 변환
            var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
              latlon);

            var routeInfoObj = {
              markerImage: markerImg,
              lng: convertPoint._lng,
              lat: convertPoint._lat,
              pointType: pType
            };

            // Marker 추가
            addMarkers(routeInfoObj);
          }
        }//for문 [E]

      },
      error: function (request, status, error) {
        console.log("code:"
          + request.status + "\n"
          + "message:"
          + request.responseText
          + "\n" + "error:" + error);
      }
    });

  //마커 생성하기
  function addMarkers(infoObj) {
    var size = new Tmapv2.Size(24, 38);//아이콘 크기 설정합니다.

    if (infoObj.pointType == "P") { //포인트점일때는 아이콘 크기를 줄입니다.
      size = new Tmapv2.Size(8, 8);
    }

    marker_p = new Tmapv2.Marker({
      position: new Tmapv2.LatLng(infoObj.lat, infoObj.lng),
      icon: infoObj.markerImage,
      iconSize: size,
      map: map
    });

    dResultMarkerArr.push(marker_p);
  }

  //라인그리기
  function dDrawLine(arrPoint, traffic) {
    var polyline_ = new Tmapv2.Polyline({
      path: arrPoint,
      strokeColor: "#2979ff",
      strokeWeight: 6,
      map: map
    });
    dResultdrawArr.push(polyline_);
  }
}
// ------------TMAP API------------

$("#searchForm").submit((e) => {
  console.log("asd");
  e.preventDefault();

  const SearchCarPool = {
    pDate: $('#datepicker').val().toString(),
    pCommute: $("input:radio[name='work']:checked").val().toString(),
    pGender: $("input:radio[name='gender']:checked").val().toString(),
    pBoardingTime: $('#boardingTime').val(),
    // pEndTime: $('#tocommutes').val(),
    // pStartPoint: $('#start').val().toString(),
    // pEndPoint: $('#end').val().toString(),
    // 위도 경도가 같으면 에러 발생 -> +1씩 해줘서 에러발생안하도록 -> 좌표상 차이 거의 없음
    pStartLon: $('#startlon').val() + 1,
    pStartLat: $('#startlat').val() + 1,
    pEndLon: $('#endlon').val() + 1,
    pEndLat: $('#endlat').val() + 1
  }

  $.ajax({
    url: '/passenger/passengerCarpool/search',
    type: 'POST',
    data: SearchCarPool,
    success: function (data, status) {
      console.log(status);
      console.log(data);
      if (status == 'success') {
        alert('찾기성공.');
      } else {
        alert('찾기실패.');
      }
      var html = '';
      if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
          html += '<div id="match">';
          // html += '<img src="img/car.png>" id="matchimg">';
          html += '<input type="hidden" id="' + data[i].drIdx + '"><input type="hidden" id="' + data[i].didx + '">';
          html += '<span id="fdate">' + data[i].ddate.substring(0, 10); + '</span>\t' + data[i].dcommute + '<br>\n';
          html += '<span class="fitem">픽업가능시간</span>\t' + data[i].dstartTime + '\t -\t ' + data[i].dendTime + '<br>\n';
          html += '<span class="fitem">출발</span>\t' + data[i].dstartPoint + '<br>\n';
          html += '<span class="fitem">도착</span>\t' + data[i].dendPoint + '<br>\n';
          html += '<span class="fitem">요금</span>\t' + data[i].dfee + '원 <br>';
          html += '<button id="view" onclick="dRoute(' + data[i].dstartlon + ', ' + data[i].dstartlat + ', ' + data[i].dendlon + ', ' + data[i].dendlat + ')" class="btn btn-primary rsvsbtn">경로보기</button>\t';
          html += '<button id="select" onclick="selectCarpool(' + data[i].drIdx + ')" class="btn btn-primary rsvsbtn" data-bs-toggle="modal" data-bs-target="#selectModal">예약하기</button>';
          html += '</div>';
        }
      } else {
        html += '<h4>검색하신 조건으로 등록된 카풀이 없습니다.</h4><br>\n';
        html += '<h4>다시 검색해주세요!</h4><br>\n';
      }
      $('#searchCarpoolList').html(html);
    }
  });
})

function search() {

}

function selectCarpool(drIdx) {
  $.ajax({
    url: '/passenger/passengerCarpool/reservation/' + drIdx,
    type: 'GET',
    success: function (data) {
      console.log(data);
      $('#pIdx').val("임시");
      $('#drIdx').val(data.drIdx);
      $('#dDate').val(data.ddate.substring(0, 10));
      $('#dStartTime').val(data.dstartTime);
      $('#dEndTime').val(data.dendTime);
      $('#dStartPoint').val(data.dstartPoint);
      $('#dEndPoint').val(data.dendPoint);
      $('#dFee').val(data.dfee);
    }
  });
}

function requestReserve() {
  $.ajax({
    url: '/passenger/passengerCarpool/reservation/request',
    type: 'POST',
    data: $('#selectCp').serialize(),
    success: function (data) {
      // true면 이미 db에 같은 내용으로 예약된 내역이 존재함
      if (data === true) {
        alert("이미 해당 카풀로 예약된 내역이 존재합니다.");
      } else {
        alert('카풀 예약 요청이 운전자님께 전달되었습니다!\n운전자님의 예약 요청 수락/거절 여부는 이메일로 받으실 수 있습니다.');
        $('#selectModal').modal('hide');
        $('#searchCarpoolList').css('display', 'none');
        $('#searchForm')[0].reset();
        console.log(data);
        window.location.reload();
      }
    }
  });
}

$('#searchCarpool').click(function () {

  if ($(':radio[name="work"]:checked').length < 1) {
    $('#workCheck').css('display', 'block');
    return false;
  } else {
    $('#workCheck').css('display', 'none');
  }

  if ($(':radio[name="gender"]:checked').length < 1) {
    $('#genderCheck').css('display', 'block');
    return false;
  } else {
    $('#genderCheck').css('display', 'none');
  }

  if ($('#datepicker').val() == "") {
    $('#dateRecheck').css('display', 'none');
    $('#dateCheck').css('display', 'block');
    return false;
  } else {
    $('#dateCheck').css('display', 'none');
  }

  if ($('#tocommute').val() == "선택") {
    $('#timeCheck').css('display', 'block');
    return false;
  } else {
    $('#timeCheck').css('display', 'none');
  }

  if ($('#start').val() == "" || $('#end').val() == "") {
    $('#routeCheck').css('display', 'block');
    return false;
  } else {
    $('#routeCheck').css('display', 'none');
  }

  if ($('#fare').val() == "") {
    $('#searchCheck').css('display', 'block');
    return false;
  } else {
    $('#searchCheck').css('display', 'none');
  }

});
