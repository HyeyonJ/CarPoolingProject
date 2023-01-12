setInterval(function () {
  $.ajax({
    url: "/driver/driverCarpool/completeStatus",
    type: "GET",
    data: { rIdx: $("#rIdx").val() },
    success: function (data, status) {
      if (data !== "") {
        $("#reivewDataForm").submit();
      }
    }
  })
}, 1000);

$(document).ready(function () {
  $.ajax({
    url: "/driver/driverCarpool/drivingPage",
    type: "POST",
    data: { drIdx: $("#drIdx").val() },
    success: function (data, status) {
      const dRegistration = data.dRegistration;
      console.log(data);
      $("#pIdx").val(data.P_IDX);
      $("#rIdx").val(data.R_IDX);
      route(dRegistration.dstartLon, dRegistration.dstartLat, dRegistration.dendLon, dRegistration.dendLat);

    }
  })
});

var map
var drawInfoArr = [];
var resultMarkerArr = [];
var resultdrawArr = [];

function zoom(map, count) {
  for (let i = 0; i < count; i++) {
    map.zoomOut();
  }
}

function route(startX, startY, endX, endY) {

  var map;

  // 1. 지도 띄우기
  map = new Tmapv2.Map("map_div", {
    width: "800px",
    height: "700px",
    zoom: 17,
  });

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
