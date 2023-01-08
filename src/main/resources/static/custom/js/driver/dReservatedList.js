var count = 0;

var d = new Date();
var thisyear = d.getFullYear() + "년";
var today;
var future;

//날짜 비교 변수
//10월 미만, 10일 미만 은 0붙여주기
if (d.getMonth() + 1 < 10 && d.getDate() < 10) {
  thisday =
    d.getFullYear() + "-" + "0" + (d.getMonth() + 1) + "-" + "0" + d.getDate();
  //그냥 10월 미만
} else if (d.getMonth() + 1 < 10) {
  thisday =
    d.getFullYear() + "-" + "0" + (d.getMonth() + 1) + "-" + d.getDate();
  //10일 미만
} else if (d.getDate() < 10) {
  thisday =
    d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + "0" + d.getDate();
} else if (d.getMonth() + 1 == 13) {
  thisday = d.getFullYear() + "-" + "01" + "-" + d.getDate();
} else {
  thisday = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
}

//오늘
today = d.getMonth() + 1 + "/" + d.getDate();

//미래
//32일이 되어버린다면..
if (d.getDate() + 1 == 32) {
  future = d.getMonth() + 2 + "/1" + "~";
} else {
  future = d.getMonth() + 1 + "/" + (d.getDate() + 1) + "~";
}

$(document).ready(() => {
  // val로 하면 안먹힘
  /*
        element.insertAdjacentHTML(position, text);
        postion에는 beforebegin, afterbegin, beforeend, afterend가 있다.
        text(인자)는 HTML 또는 XML로 해석될 수 있는 문자열이다.
        position의 예시
        [beforebegin] : element 앞에
        [afterbegin] : element 안에 가장 첫번째 child
        [beforeend] : element 안에 가장 마지막 child
        [afterend] : element 뒤에
        */
  // https://stackoverflow.com/questions/6588630/jquery-append-text 보안때문에 createTextNode or insertAdjacentHTML사용
  $("#today").append(document.createTextNode(today));
  $("#future").append(document.createTextNode(future));

  // document.getElementById('today').insertAdjacentHTML("beforeend", today);
  // document.getElementById('future').insertAdjacentHTML("beforeend", future);

  list();

  //리스트 none
  /*$('#todayList').css('display','none');*/
  $("#futureList").css("display", "none");

  //날짜 버튼 클릭시 디스플레이
  $("#past").click(function () {
    $("#pastList").css("display", "inline");
    $("#todayList").css("display", "none");
    $("#futureList").css("display", "none");
    //버튼활성
    $("#pastbutton").css("display", "block");
    $("#todaybutton").css("display", "block");
    $("#futurebutton").css("display", "block");
  });

  $("#today").click(function () {
    $("#todayList").css("display", "inline");
    $("#pastList").css("display", "none");
    $("#futureList").css("display", "none");
    //버튼활성
    $("#pastbutton").css("display", "block");
    $("#todaybutton").css("display", "block");
    $("#futurebutton").css("display", "block");
  });

  $("#future").click(function () {
    $("#futureList").css("display", "inline");
    $("#pastList").css("display", "none");
    $("#todayList").css("display", "none");
    //버튼활성
    $("#pastbutton").css("display", "block");
    $("#todaybutton").css("display", "block");
    $("#futurebutton").css("display", "block");
  });
});

function list() {
  $.ajax({
    url: "/driver/driverCarpool/list/reservatedList",
    type: "POST",
    success: (data) => {
      console.log(data);

      var todayhtml = "";
      var futurehtml = "";

      for (var i = 0; i < data.length; i++) {
        console.log(data[i].D_FEE);

        var fee = data[i].D_FEE.toString();
        var feeCut = fee.substring(fee.length, fee.length - 3);
        var split = fee.split(feeCut);
        var feeSplit = split[0] + "," + feeCut;

        var dDate = data[i].D_DATE.substring(0, 10);

        if (dDate == thisday) {
          console.log("1번");

          todayhtml += '<div class="pastDiv">';
          todayhtml +=
            '<input class="drIdx" type="hidden" value=' +
            data[i].DR_IDX +
            "></input><br>\n";
          todayhtml +=
            '<span class="CommuteSPAN">' + data[i].D_COMMUTE + "</span><br>\n";
          todayhtml +=
            "<span>탑승자 닉네임 : " + data[i].P_USER_NAME + "</span><br>\n";

          todayhtml +=
            '<span class="DateSPAN">날짜 : ' + "</span>" + dDate + "<br>\n";
          todayhtml +=
            '<span class="STimeSPAN">출발시간 : ' +
            "</span>" +
            data[i].D_START_TIME +
            " <br>\n";
          todayhtml +=
            '<span class="ETimeSPAN">도착시간 : </span>' +
            data[i].D_END_TIME +
            "<br>\n";
          todayhtml +=
            '<span class="SpointSPAN">출발장소 : </span>' +
            data[i].D_START_POINT +
            "<br>\n";
          todayhtml +=
            '<span class="EpointSPAN">도착장소 : </span>' +
            data[i].D_END_POINT +
            "<br>\n";
          todayhtml +=
            '<span class="FeeSPAN">요금 : </span>' + feeSplit + "원<br>\n";
          todayhtml +=
            '<button class="btn btn-primary" id="todayNbtn" onclick="route(' +
            data[i].D_START_LON +
            ", " +
            data[i].D_START_LAT +
            ", " +
            data[i].D_END_LON +
            ", " +
            data[i].D_END_LAT +
            ')">지도보기</button>';
          todayhtml +=
            '<button class="btn btn-primary" id="todayNbtn" onclick="cancelRegistration(' +
            data[i].DR_IDX +
            ", " +
            data[i].P_IDX +
            ')">취소</button>' +
            "<br><br>\n";
          todayhtml += "</div>";

          //미래
        } else if (dDate > thisday) {
          console.log("2번");
          futurehtml += '<div class="pastDiv">';
          futurehtml +=
            '<input class="drIdx" type="hidden" value=' +
            data[i].DR_IDX +
            "></input><br>\n";
          futurehtml +=
            '<span class="CommuteSPAN">' + data[i].D_COMMUTE + "</span><br>\n";
          futurehtml +=
            "<span>탑승자 닉네임 : " + data[i].P_USER_NAME + "</span><br>\n";

          futurehtml +=
            '<span class="DateSPAN">날짜 : ' + "</span>" + dDate + "<br>\n";
          futurehtml +=
            '<span class="STimeSPAN">출발시간 : ' +
            "</span>" +
            data[i].D_START_TIME +
            " <br>\n";
          futurehtml +=
            '<span class="ETimeSPAN">도착시간 : </span>' +
            data[i].D_END_TIME +
            "<br>\n";
          futurehtml +=
            '<span class="SpointSPAN">출발장소 : </span>' +
            data[i].D_START_POINT +
            "<br>\n";
          futurehtml +=
            '<span class="EpointSPAN">도착장소 : </span>' +
            data[i].D_END_POINT +
            "<br>\n";
          futurehtml +=
            '<span class="FeeSPAN">요금 : </span>' + feeSplit + "원<br>\n";
          futurehtml +=
            '<button class="btn btn-primary" id="todayNbtn" onclick="route(' +
            data[i].D_START_LON +
            ", " +
            data[i].D_START_LAT +
            ", " +
            data[i].D_END_LON +
            ", " +
            data[i].D_END_LAT +
            ')">지도보기</button>';
          futurehtml +=
            '<button class="btn btn-primary" id="futureNbtn" onclick="cancelRegistration(' +
            data[i].DR_IDX +
            ')">취소</button>' +
            "<br><br>\n";
          futurehtml += "</div>";
        }
      }

      $("#todayList").html(todayhtml);
      $("#futureList").html(futurehtml);
    },
  });
}

/*수락*/
function concent(dr_idx, p_idx) {
  confirm("수락 하시겠습니까?");

  $.ajax({
    url: "/driver/driverCarpool/reqList/accept",
    type: "PUT",
    data: {
      drIdx: dr_idx,
      pIdx: p_idx,
    },
    success: function (data) {
      if (data === "true") {
        alert("수락 되었습니다.");
        window.location.reload();
      }
      list();
    },
  });
}

/*취소*/
function cancelRegistration(dr_idx) {
  confirm("취소 하시겠습니까?");

  $.ajax({
    url: "/driver/driverCarpool/list/reservatedList/cancellation",
    type: "DELETE",
    data: {
      drIdx: dr_idx,
    },
    success: function (data, status) {
      if (status === "success") {
        // 환불코드
      }
      list();
    },
  });
}

function pastMapClose(count) {
  if (count == 0) {
    var html =
      '<button id="closebutton" onclick="closebutton()">지도 닫기</button>';

    $("#pastMapClose").append(html);
  }
}

function closebutton() {
  $("#map_div").remove();
  $("#closebutton").css("display", "none");
}

// ------------------------TMAP API------------------------

function zoom(map, count) {
  for (let i = 0; i < count; i++) {
    map.zoomOut();
  }
}

var map;
var drawInfoArr = [];
var resultMarkerArr = [];
var resultdrawArr = [];

function route(d_startlon, d_startlat, d_endlon, d_endlat) {
  //팝업창을 가운데로 띄우기 위해 현재 화면의 가운데 값과 스크롤 값을 계산하여 팝업창 CSS 설정
  $("#popupDiv").css({
    top: ($(window).height() - $("#popupDiv").outerHeight()) / 2 + "px",
    left: ($(window).width() - $("#popupDiv").outerWidth()) / 2 + "px",
  });

  $("#popup_mask").css("display", "block"); //팝업 뒷배경 display block
  $("#popupDiv").css("display", "block"); //팝업창 display block
  $("body").css("overflow", "hidden"); //body 스크롤바 없애기

  if (count == 1) {
    var html = '<div id="map_div"></div>';
    $("#popupDiv").append(html);
  }

  // 지도 띄우기
  map = new Tmapv2.Map("map_div", {
    width: "450px",
    height: "450px",
    zoom: 17,
  });

  // ------ 초기화 ------

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
          drawLine(drawInfoArr, "0");
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

    resultMarkerArr.push(marker_p);
  }

  //라인그리기
  function drawLine(arrPoint, traffic) {
    var polyline_ = new Tmapv2.Polyline({
      path: arrPoint,
      strokeColor: "#2979ff",
      strokeWeight: 6,
      map: map,
    });
    resultdrawArr.push(polyline_);
  }

  $("#popCloseBtn").click(function () {
    $("#popup_mask").css("display", "none"); //팝업창 뒷배경 display none
    $("#popupDiv").css("display", "none"); //팝업창 display none
    $("body").css("overflow", "auto"); //body 스크롤바 생성
    $("#map_div").remove();
    count = 1;
  });
}
