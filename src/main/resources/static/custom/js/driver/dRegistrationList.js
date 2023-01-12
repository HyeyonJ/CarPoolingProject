function reservatedRgsList() {
  $("#reservatedRgsList").css("display", "block");
  $("#waitingRgsList").css("display", "none");
  $("#pastRgsList").css("display", "none");
  $("#completedRgsList").css("display", "none");
  $("#canceledRgsList").css("display", "none");

  $.ajax({
    url: "/driver/driverCarpool/list/reservatedRgsList",
    type: "GET",
    success: function (data) {
      console.log(data);
      var html = "";
      for (var i = 0; i < data.length; i++) {
        var dDate = data[i].D_DATE.substring(0, 10);

        html += '<div class="rsv">\n';
        html += '<span class="CommuteSPAN">' + data[i].D_COMMUTE + '</span><br>\n';
        html +=
          '<span>탑승자</span>\t' +
          data[i].P_USER_NAME +
          "<br>\n";
        html +=
          '<span>카풀일시</span>\t' +
          dDate +
          "\t" +
          data[i].D_START_TIME +
          "\t -\t " +
          data[i].D_END_TIME +
          "<br>\n";
        html +=
          '<span>출발지</span>\t' +
          data[i].D_START_POINT +
          "<br>\n";
        html +=
          '<span>도착지</span>\t' +
          data[i].D_END_POINT +
          "<br>\n";
        html +=
          '<span>요금</span>\t' + data[i].D_FEE + "원 <br>\n";
        html +=
          '<button id="view" onclick="viewRoute(' +
          data[i].D_START_LON +
          ", " +
          data[i].D_START_LAT +
          ", " +
          data[i].D_END_LON +
          ", " +
          data[i].D_END_LAT +
          ')" class="btn btn-primary rsvsbtn" data-toggle="modal" data-target="#viewModal">경로보기</button>\t';
        html +=
          '<button class="btn btn-primary rsvsbtn" onclick="drivingStart(' +
          data[i].DR_IDX +
          ')" >운행시작</button>\t';
        html +=
          '<button id="PUT" onclick="cancelReservatedRgs(' +
          data[i].DR_IDX +
          "," +
          data[i].P_IDX +
          ')" class="btn btn-primary rsvsbtn">카풀취소</button>\t';
        //		Chatting Room 생성
        html +=
        	'<form action="/chatting/room/dr" method="post">';
        html +=
        		'<input type="hidden" name="pIdx" value="' +
        		data[i].P_IDX + 
        		'"></input>'
        html +=
        		'<input type="hidden" name="rIdx" value="' +
        		data[i].DR_IDX + 
        		'"></input>' 
        html +=
        		'<input type="hidden" name="name" value="' +
        		data[i].DR_IDX + 
        		"번 방"+
        		'"></input>' 		
		html +=
          '<button class="btn btn-primary">채팅</button>';				
        html +=  
        	'</form>'
        html += "</div>";
      }
      $("#reservatedRgsList").html(html);
    },
  });
}

function waitingRgsList() {
  $("#reservatedRgsList").css("display", "none");
  $("#waitingRgsList").css("display", "block");
  $("#pastRgsList").css("display", "none");
  $("#completedRgsList").css("display", "none");
  $("#canceledRgsList").css("display", "none");

  $.ajax({
    url: "/driver/driverCarpool/list/waitingRgsList",
    type: "GET",
    success: function (data) {
      console.log(data);
      var html = "";
      for (var i = 0; i < data.length; i++) {
        var dDate = data[i].D_DATE.substring(0, 10);

        html += '<div class="rsv">\n';
        html += '<span class="CommuteSPAN">' + data[i].D_COMMUTE + '</span><br>\n';
        html +=
          '<span>카풀일시</span>\t' +
          dDate +
          "\t" +
          data[i].D_START_TIME +
          "\t -\t " +
          data[i].D_END_TIME +
          "<br>\n";
        html +=
          '<span>출발지</span>\t' +
          data[i].D_START_POINT +
          "<br>\n";
        html +=
          '<span>도착지</span>\t' +
          data[i].D_END_POINT +
          "<br>\n";
        html +=
          '<span>요금</span>\t' + data[i].D_FEE + "원 <br>\n";
        html +=
          '<button id="view" onclick="viewRoute(' +
          data[i].D_START_LON +
          ", " +
          data[i].D_START_LAT +
          ", " +
          data[i].D_END_LON +
          ", " +
          data[i].D_END_LAT +
          ')" class="btn btn-primary rsvsbtn" data-toggle="modal" data-target="#viewModal">경로보기</button>\t';
        html +=
          '<button id="PUT" onclick="cancelWaitingRgs(' +
          data[i].DR_IDX +
          "," +
          data[i].P_IDX +
          ')" class="btn btn-primary rsvsbtn">카풀취소</button>\t';
        html += "</div>";
      }
      $("#waitingRgsList").html(html);
    },
  });
}

function pastRgsList() {
  $("#reservatedRgsList").css("display", "none");
  $("#waitingRgsList").css("display", "none");
  $("#pastRgsList").css("display", "block");
  $("#completedRgsList").css("display", "none");
  $("#canceledRgsList").css("display", "none");

  $.ajax({
    url: "/driver/driverCarpool/list/pastRgsList",
    type: "GET",
    success: function (data) {
      console.log(data);
      var html = "";
      for (var i = 0; i < data.length; i++) {
        var dDate = data[i].D_DATE.substring(0, 10);

        html += '<div class="rsv">\n';
        html += '<span class="CommuteSPAN">' + data[i].D_COMMUTE + '</span><br>\n';
        html +=
          '<span>탑승자</span>\t' +
          data[i].P_USER_NAME +
          "<br>\n";
        html +=
          '<span>카풀일시</span>\t' +
          dDate +
          "\t" +
          data[i].D_START_TIME +
          "\t -\t " +
          data[i].D_END_TIME +
          "<br>\n";
        html +=
          '<span>출발지</span>\t' +
          data[i].D_START_POINT +
          "<br>\n";
        html +=
          '<span>도착지</span>\t' +
          data[i].D_END_POINT +
          "<br>\n";
        html +=
          '<span>요금</span>\t' +
          data[i].cancelAmount +
          "원 <br>\n";
        html +=
          '<button id="view" onclick="viewRoute(' +
          data[i].D_START_LON +
          ", " +
          data[i].D_START_LAT +
          ", " +
          data[i].D_END_LON +
          ", " +
          data[i].D_END_LAT +
          ')" class="btn btn-primary rsvsbtn" data-toggle="modal" data-target="#viewModal">경로보기</button>\t';
        html += "</div>";
      }
      $("#pastRgsList").html(html);
    },
  });
}

function completedRgsList() {
  $("#reservatedRgsList").css("display", "none");
  $("#waitingRgsList").css("display", "none");
  $("#pastRgsList").css("display", "none");
  $("#completedRgsList").css("display", "block");
  $("#canceledRgsList").css("display", "none");

  $.ajax({
    url: "/driver/driverCarpool/list/completeRgsList",
    type: "GET",
    success: function (data) {
      console.log(data);
      var html = "";
      for (var i = 0; i < data.length; i++) {
        var dDate = data[i].D_DATE.substring(0, 10);

        html += '<div class="rsv">\n';
        html += '<span class="CommuteSPAN">' + data[i].D_COMMUTE + '</span><br>\n';
        html +=
          '<span>카풀일시</span>\t' +
          dDate +
          "\t" +
          data[i].D_START_TIME +
          "\t -\t " +
          data[i].D_END_TIME +
          "<br>\n";
        html +=
          '<span>출발지</span>\t' +
          data[i].D_START_POINT +
          "<br>\n";
        html +=
          '<span>도착지</span>\t' +
          data[i].D_END_POINT +
          "<br>\n";
        html +=
          '<span>요금</span>\t' + data[i].D_FEE + "원 <br>\n";
        html +=
          '<button id="view" onclick="viewRoute(' +
          data[i].D_START_LON +
          ", " +
          data[i].D_START_LAT +
          ", " +
          data[i].D_END_LON +
          ", " +
          data[i].D_END_LAT +
          ')" class="btn btn-primary rsvsbtn" data-toggle="modal" data-target="#viewModal">경로보기</button>\t';
        html +=
          '<button id="PUT" onclick="cancelWaitingRgs(' +
          data[i].DR_IDX +
          "," +
          data[i].P_IDX +
          ')" class="btn btn-primary rsvsbtn">리뷰작성</button>\t';
        html += "</div>";
      }
      $("#completedRgsList").html(html);
    },
  });
}

function canceledRgsList() {
  $("#reservatedRgsList").css("display", "none");
  $("#waitingRgsList").css("display", "none");
  $("#pastRgsList").css("display", "none");
  $("#completedRgsList").css("display", "none");
  $("#canceledRgsList").css("display", "block");

  $.ajax({
    url: "/driver/driverCarpool/list/canceledRgsList",
    type: "GET",
    success: function (data) {
      console.log(data);
      var html = "";
      for (var i = 0; i < data.length; i++) {
        var dDate = data[i].D_DATE.substring(0, 10);
        html += '<div class="rsv">\n';
        html += '<span class="CommuteSPAN">' + data[i].D_COMMUTE + '</span><br>\n';
        html +=
          '<span>카풀일시</span>\t' +
          dDate +
          "\t" +
          data[i].D_START_TIME +
          "\t -\t " +
          data[i].D_END_TIME +
          "<br>\n";
        html +=
          '<span>출발지</span>\t' +
          data[i].D_START_POINT +
          "<br>\n";
        html +=
          '<span>도착지</span>\t' +
          data[i].D_END_POINT +
          "<br>\n";
        html +=
          '<span>요금</span>\t' + data[i].D_FEE + "원 <br>\n";
        html +=
          '<button id="view" onclick="viewRoute(' +
          data[i].D_START_LON +
          ", " +
          data[i].D_START_LAT +
          ", " +
          data[i].D_END_LON +
          ", " +
          data[i].D_END_LAT +
          ')" class="btn btn-primary rsvsbtn" data-toggle="modal" data-target="#viewModal">경로보기</button>\t';
        html += "</div>";
      }
      $("#canceledRgsList").html(html);
    },
  });
}

/*거절*/
function cancelReservatedRgs(drIdx, pIdx) {
  Swal.fire({
    title: "등록을 취소하시겠습니까?",
    text: "픽업가능 출발시간으로부터 24시간미만이면 1회 경고가 누적되며 5회 이상 누적시 서비스이용이 정지됩니다. 신중히 선택바랍니다.",
    icon: "warning",

    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
    confirmButtonColor: "#3085d6", // confrim 버튼 색깔 지정
    cancelButtonColor: "#d33", // cancel 버튼 색깔 지정
    confirmButtonText: "승인", // confirm 버튼 텍스트 지정
    cancelButtonText: "취소", // cancel 버튼 텍스트 지정

    reverseButtons: false, // 버튼 순서 거꾸로
  }).then((result) => {
    // 만약 Promise리턴을 받으면,
    if (result.isConfirmed) {
      // 만약 모달창에서 confirm 버튼을 눌렀다면
      $.ajax({
        url: "/driver/driverCarpool/list/reservatedRgsList/cancellation",
        type: "PUT",
        data: { drIdx: drIdx, pIdx: pIdx },
        success: function (data) {
          // 결제 취소 데이터
          const cancelData = {
            payIdx: data.payIdx,
            pIdx: data.pidx,
            amount: data.amount,
            cancelAmount: data.amount,
          };
          // 결제 취소 요청
          const corsErr = "http://cors-anywhere.herokuapp.com/";
          $.ajax({
            url: "/carpoolingPay/cancel/requestIamport", // 토큰 요청
            type: "POST",
          }).done(function (data, status) {
            const token = data.response.token;
            $.ajax({
              url: corsErr + "https://api.iamport.kr/payments/cancel",
              method: "POST",
              headers: {
                Authorization: token, // 아임포트 서버로부터 발급받은 엑세스 토큰
              },
              data: {
                merchant_uid: cancelData.payIdx, // 예: carpooling_12345567677888
                amount: cancelData.cancelAmount, // 환불금액
                reason: "Carpooling 예약 취소",
              },
            })
              .done(function (data, status) {
                const cancelResData = data;
                cancelData.receiptUrl = data.response.cancel_receipt_urls[0];
                $.ajax({
                  url: "/carpoolingPay/cancel/completed",
                  type: "POST",
                  data: cancelData,
                }).done(function (data, status) {
                  console.log(status);
                  if (
                    cancelResData.response.amount ===
                    cancelResData.response.cancel_amount
                  ) {
                    Swal.fire(
                      "등록취소성공!",
                      "카풀 등록이 취소되었습니다.\n선결제하신 금액은 전액 즉시 반환됩니다.",
                      "success"
                    ).then((OK) => {
                      if (OK) {
                        window.location.reload();
                      }
                    });
                  } else {
                    Swal.fire(
                      "카풀취소성공!",
                      "카풀 등록이 취소되었습니다.\n취소수수료를 제외한 선결제 금액이 즉시 반환됩니다.\n취소수수료\n(24시간전->20%, 12시간전->25%, 6시간전->30%)\n" +
                      "자세한 사항은 결제취소내역에서 확인할 수 있습니다.",
                      "success"
                    ).then((OK) => {
                      if (OK) {
                        window.location.reload();
                      }
                    });
                  }
                });
              })
              .fail(function (error) {
                console.log("error : " + error);
              });
          });
        },
      });
    }
  });
}

function cancelWaitingRgs(drIdx) {
  Swal.fire({
    title: "등록을 취소하시겠습니까?",
    text: "다시 되돌릴 수 없습니다. 신중하세요.",
    icon: "warning",

    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
    confirmButtonColor: "#3085d6", // confrim 버튼 색깔 지정
    cancelButtonColor: "#d33", // cancel 버튼 색깔 지정
    confirmButtonText: "승인", // confirm 버튼 텍스트 지정
    cancelButtonText: "취소", // cancel 버튼 텍스트 지정

    reverseButtons: false, // 버튼 순서 거꾸로
  }).then((result) => {
    // 만약 Promise리턴을 받으면,
    if (result.isConfirmed) {
      // 만약 모달창에서 confirm 버튼을 눌렀다면
      $.ajax({
        url: "/driver/driverCarpool/list/waitingRgsList/cancellation",
        type: "PUT",
        data: { drIdx: drIdx },
        success: function (data, status) {
          if (status === "success") {
            Swal.fire(
              "등록취소성공!",
              "카풀 등록이 취소되었습니다.",
              "success"
            ).then((OK) => {
              if (OK) {
                window.location.reload();
              } else {
                Swal.fire(
                  "등록취소실패!",
                  "카풀 등록 취소에 실패하였습니다.",
                  "error"
                );
              }
            });
          }
        },
      });
    }
  });
}

// 운행시작
function drivingStart(drIdx) {
  $.ajax({
    url: "/driver/driverCarpool/drivingPage",
    type: "PUT",
    data: { drIdx: drIdx },
    success: function (data, status) {
      if (status === "success") {
        window.location.href = "http://localhost:8080/driver/driverCarpool/drivingPage?drIdx=" + drIdx;
      }
    },
  });
}

var count = 0;

var dDrawInfoArr = [];
var dResultMarkerArr = [];
var dResultdrawArr = [];

function viewRoute(d_startlon, d_startlat, d_endlon, d_endlat) {
  $("#popupDiv").css({
    top: ($(window).height() - $("#popupDiv").outerHeight()) / 2 + "px",
    left: ($(window).width() - $("#popupDiv").outerWidth()) / 2 + "px",
    //팝업창을 가운데로 띄우기 위해 현재 화면의 가운데 값과 스크롤 값을 계산하여 팝업창 CSS 설정
  });

  $("#popup_mask").css("display", "block"); //팝업 뒷배경 display block
  $("#popupDiv").css("display", "block"); //팝업창 display block

  $("body").css("overflow", "hidden"); //body 스크롤바 없애기

  if (count == 1) {
    var html = '<div id="map_div"></div>';
    $("#popupDiv").append(html);
  }

  var map;

  // 1. 지도 띄우기
  map = new Tmapv2.Map("map_div", {
    width: "490px",
    height: "450px",
    zoom: 17,
  });

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
      var positionBounds = new Tmapv2.LatLngBounds(); //맵에 결과물 확인 하기

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
      var distance = resultData[0].properties.totalDistance / 1000;
      if (distance < 10) {
        console.log("10km미만");
        map.zoomOut();
        map.zoomOut();
        map.zoomOut();
      } else if (distance < 20) {
        console.log("20km미만");
        map.zoomOut();
        map.zoomOut();
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
        map.zoomOut();
        map.zoomOut();
      } else if (distance < 40) {
        console.log("40km미만");
        map.zoomOut();
        map.zoomOut();
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

      var tDistance = (resultData[0].properties.totalDistance / 1000).toFixed(
        1
      );
      var tTime = (resultData[0].properties.totalTime / 60).toFixed(0);
      var tFare = resultData[0].properties.taxiFare;

      //택시비의 60프로 저렴한 가격
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

  $("#popCloseBtn").click(function (event) {
    $("#popup_mask").css("display", "none"); //팝업창 뒷배경 display none
    $("#popupDiv").css("display", "none"); //팝업창 display none
    $("body").css("overflow", "auto"); //body 스크롤바 생성
    $("#map_div").remove();
    count = 1;
  });
}
