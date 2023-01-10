var count = 0;

var dDrawInfoArr = [];
var dResultMarkerArr = [];
var dResultdrawArr = [];

function acceptedRsv() {
  $("#waitingList").css("display", "none");
  $("#refusedList").css("display", "none");
  $("#pastList").css("display", "none");
  $("#acceptedList").css("display", "block");
  $.ajax({
    url: "/passenger/passengerCarpool/list/currentList",
    type: "GET",
    success: function (data) {
      console.log(data);
      var html = "";
      for (var i = 0; i < data.length; i++) {
        var dDate = data[i].D_DATE.substring(0, 10);
        var rDate = data[i].R_DATE.substring(0, 10);

        html += '<div class="rsv">\n';
        html +=
          '<span class="fitem">운전자</span>\t' +
          data[i].D_USER_NAME +
          "<br>\n";
        html +=
          '<span class="fitem">카풀일시</span>\t' +
          dDate +
          "\t" +
          data[i].D_START_TIME +
          "\t -\t " +
          data[i].D_END_TIME +
          "<br>\n";
        html +=
          '<span class="fitem">출발지</span>\t' +
          data[i].D_START_POINT +
          "<br>\n";
        html +=
          '<span class="fitem">도착지</span>\t' +
          data[i].D_END_POINT +
          "<br>\n";
        html +=
          '<span class="fitem">요금</span>\t' + data[i].D_FEE + "원 <br>\n";
        html += '<span id="frdate">예약일자 ' + rDate + "</span><br>\n";
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
          data[i].R_IDX +
          ')" >탑승대기</button>\t';
        html +=
          '<button id="delete" onclick="cancelReservation(' +
          data[i].DR_IDX +
          ')" class="btn btn-primary rsvsbtn">카풀취소</button>\t';
        html +=
          '<button class="btn btn-primary">채팅</button>';
        html += "</div>";
      }
      $("#acceptedList").html(html);
    },
  });
}

function waitingRsv() {
  $("#acceptedList").css("display", "none");
  $("#refusedList").css("display", "none");
  $("#pastList").css("display", "none");
  $("#waitingList").css("display", "block");
  $.ajax({
    url: "/passenger/passengerCarpool/list/pastList",
    type: "GET",
    success: function (data) {
      console.log(data);
      var html = "";
      for (var i = 0; i < data.length; i++) {
        var dDate = data[i].D_DATE.substring(0, 10);
        var rDate = data[i].R_DATE.substring(0, 10);

        html += '<div class="rsv">\n';
        html +=
          '<span class="fitem">운전자</span>\t' +
          data[i].D_USER_NAME +
          "<br>\n";
        html +=
          '<span class="fitem">카풀일시</span>\t' +
          dDate +
          "\t" +
          data[i].D_START_TIME +
          "\t -\t " +
          data[i].D_END_TIME +
          "<br>\n";
        html +=
          '<span class="fitem">출발지</span>\t' +
          data[i].D_START_POINT +
          "<br>\n";
        html +=
          '<span class="fitem">도착지</span>\t' +
          data[i].D_END_POINT +
          "<br>\n";
        html +=
          '<span class="fitem">요금</span>\t' + data[i].D_FEE + "원 <br>\n";
        html += '<span id="frdate">예약일자 ' + rDate + "</span><br>\n";
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
          '<button class="btn btn-primary rsvsbtn" id="delete" onclick="deleteReq(' +
          data[i].DR_IDX +
          ')" >예약취소</button>\t';
        html += "</div>";
      }
      $("#waitingList").html(html);
    },
  });
}

/*거절*/
function cancelReservation(dr_idx) {
  confirm("예약을 취소하시겠습니까?");

	$.ajax({
		url: "/passenger/passengerCarpool/list/currentList/cancellation",
		type: "DELETE",
		data: { drIdx: dr_idx },
		success: function(data) {
			// 결제 취소 데이터
			console.log(data);
			const cancelData = {
				payIdx: data.payIdx,
				pIdx: data.pidx,
				amount: data.amount,
				cancelAmount: data.cancelAmount,
				receiptUrl: data.receiptUrl
			};
			
			// 결제 취소 요청
			const corsErr = "http://cors-anywhere.herokuapp.com/";
			$.ajax({
				url: "/passenger/carpoolingPay/cancel/requestIamport", // 토큰 요청
				type: "POST"
			}).done(function(data, status) {
				const token = data.response.token;
				$.ajax({
					url: corsErr + "https://api.iamport.kr/payments/cancel",
					method: "post",
					headers: {
						"Authorization": token // 아임포트 서버로부터 발급받은 엑세스 토큰
					},
					data: {
						merchant_uid: cancelData.payIdx, // 예: carpooling_12345567677888
						amount: cancelData.cancelAmount, // 환불금액
						reason: "Carpooling 예약 취소"
					}
				}).done(function(data, status) {
					cancelData.receiptUrl = data.response.receipt_url;
					$.ajax({
						url: "/passenger/carpoolingPay/cancel/complete",
						type: "POST",
						data: cancelData
					}).done(function(data, status) {
						console.log(status);
					})

				}).fail(function(error) {
					console.log("error : " + error);
				})

			});

		},
	});
}

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
