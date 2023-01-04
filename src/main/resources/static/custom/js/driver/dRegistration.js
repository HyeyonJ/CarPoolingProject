 $(function () {
        $("#datepicker").datepicker({
          dateFormat: 'yy-mm-dd'
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
      });

      $("#datepicker").change(() => {

        if ($("#tocommute").val() === "선택" || $("#tocommutes").val() === "선택") {
          $('#timeRecheck3').css('display', 'none');
        }
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
          $('#dateCheck').css('display', 'none');
          $('#dateRecheck').css('display', 'block');
          $("#datepicker").val("");
          return false;
        } else {
          $('#dateRecheck').css('display', 'none');
        }

        const tocommuteVal = $("#tocommute").val();

        if ((year + month + date === drYear + drMonth + drDate) && tocommuteVal !== "선택") {
          var hours = today.getHours().toString();
          var minutes = today.getMinutes().toString();
          if (hours < 10) {
            hours = "0" + hours;
          }
          if (minutes < 10) {
            minutes = "0" + minutes;
          }

          const drHour = tocommuteVal.substring(0, 2);
          const drMinute = tocommuteVal.substring(3, 5);

          if (hours > drHour || (hours === drHour && minutes > drMinute)) {
            console.log("5번들어옴");
            $('#timeRecheck3').css('display', 'block');
            $("#tocommute").val("선택");
            $("#tocommutes").val("선택");
            return false;
          } else {
            console.log("6번들어옴");
            $('#timeRecheck3').css('display', 'none');
          }

        }
      })

      $("#tocommute").change(() => {
        if ($(':radio[name="work"]:checked').length < 1) {
          $('#workCheck').css('display', 'block');
          $("#tocommute").blur();
          return false;
        } else {
          $('#workCheck').css('display', 'none');
        }

        const tocommute = ($("#tocommute").val()).substring(0, 2) + ($("#tocommute").val()).substring(3, 5);
        const tocommutes = ($("#tocommutes").val()).substring(0, 2) + ($("#tocommutes").val()).substring(3, 5);

        if (tocommute > tocommutes) {
          console.log("1번");
          $('#timeCheck').css('display', 'none');
          $('#timeRecheck1').css('display', 'block');
          $("#tocommutes").val("선택");
          return false;
        } else {
          console.log("2번");
          $('#timeRecheck').css('display', 'none');
        }

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

            const tocommuteVal = $("#tocommute").val();
            const drHour = tocommuteVal.substring(0, 2);
            const drMinute = tocommuteVal.substring(3, 5);

            if (hours > drHour || (hours === drHour && minutes > drMinute)) {
              console.log("6번");
              $('#timeRecheck3').css('display', 'block');
              $("#tocommute").val("선택");
              $("#tocommutes").val("선택");
              return false;
            } else {
              console.log("7번");
              $('#timeRecheck3').css('display', 'none');
            }
          }
        }



      })

      $("#tocommutes").change(() => {
        if ($(':radio[name="work"]:checked').length < 1) {
          $('#workCheck').css('display', 'block');
          $("#tocommutes").blur();
          return false;
        } else {
          $('#workCheck').css('display', 'none');
        }

        const tocommute = ($("#tocommute").val()).substring(0, 2) + ($("#tocommute").val()).substring(3, 5);
        const tocommutes = ($("#tocommutes").val()).substring(0, 2) + ($("#tocommutes").val()).substring(3, 5);

        if (tocommute === "선택") {
          $('#timeCheck').css('display', 'none');
          $('#timeRecheck3').css('display', 'none');
          $('#timeRecheck').css('display', 'block');
          $("#tocommutes").val("선택");
          return false;
        } else {
          $('#timeRecheck').css('display', 'none');
        }

        if (tocommute > tocommutes) {
          console.log("3번");
          $('#timeCheck').css('display', 'none');
          $('#timeRecheck1').css('display', 'block');
          $("#tocommutes").val("선택");
          return false;
        } else {
          console.log("4번");
          $('#timeRecheck').css('display', 'none');
          $('#timeRecheck1').css('display', 'none');
          $('#timeRecheck2').css('display', 'none');
        }
      })



      // ------------TMAP API------------
      var map, marker;
      var markerArr = [];
      var drawInfoArr = [];
      var resultMarkerArr = [];
      var resultdrawArr = [];

      $(document).ready(function () {
        initTmap();
      });

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
              var tDistance = (resultData[0].properties.totalDistance / 1000).toFixed(1);
              if (tDistance < 10) {
                console.log("10km미만");
                map.zoomOut();
              } else if (tDistance < 20) {
                console.log("20km미만");
                map.zoomOut();
                map.zoomOut();
                map.zoomOut();
                map.zoomOut();
                map.zoomOut();
              } else if (tDistance < 30) {
                console.log("30km미만");
                map.zoomOut();
                map.zoomOut();
                map.zoomOut();
                map.zoomOut();
                map.zoomOut();
                map.zoomOut();
              } else if (tDistance < 40) {
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

              var tTime = (resultData[0].properties.totalTime / 60).toFixed(0);
              var tFare = resultData[0].properties.taxiFare;
              console.log(parseFloat(tDistance));

              // $('#routeTime').text('약 ' + tTime + '분 정도 걸리는 거리입니다.');
              $('#distance').val(parseFloat(tDistance));
              $('#time').val(parseInt(tTime));
              //택시비의 60프로 저렴한 가격
              $('#fare').val(parseInt(tFare) * 0.6);

              console.log(parseInt(tTime));

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

          resultMarkerArr.push(marker_p);
        }

        //라인그리기
        function drawLine(arrPoint, traffic) {
          var polyline_ = new Tmapv2.Polyline({
            path: arrPoint,
            strokeColor: "#DD0000",
            strokeWeight: 6,
            map: map
          });
          resultdrawArr.push(polyline_);
        }
      }
      // ------------TMAP API------------

      $('#submit').click(function () {

        if ($(':radio[name="work"]:checked').length < 1) {
          $('#workCheck').css('display', 'block');
          return false;
        } else {
          $('#workCheck').css('display', 'none');
        }

        if ($('#datepicker').val() == "") {
          $('#dateRecheck').css('display', 'none');
          $('#dateCheck').css('display', 'block');
          return false;
        } else {
          $('#dateCheck').css('display', 'none');
        }

        if ($('#tocommute').val() == "선택" || $('#tocommutes').val() == "선택") {
          $('#timeRecheck').css('display', 'none');
          $('#timeRecheck1').css('display', 'none');
          $('#timeRecheck2').css('display', 'none');
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

        const CarPoolRegistraion = {
          dDate: $('#datepicker').val().toString(),
          dCommute: $("input:radio[name='work']:checked").val().toString(),
          dStartTime: $('#tocommute').val(),
          dEndTime: $('#tocommutes').val(),
          dStartPoint: $('#start').val().toString(),
          dEndPoint: $('#end').val().toString(),
          dFee: $('#fare').val(),
          dDistance: $('#distance').val(),
          dTime: $('#time').val(),
          dStartlon: $('#startlon').val(),
          dStartlat: $('#startlat').val(),
          dEndlon: $('#endlon').val(),
          dEndlat: $('#endlat').val()
        }

        $.ajax({
          url: '/driver/driverCarpool/registration',
          type: 'POST',
          data: CarPoolRegistraion,
          success: function (data) {
            console.log(data);
            if (data == '등록성공') {
              console.log(data);
              alert('정상적으로 등록되었습니다.');
              window.location.reload();
            } else {
              alert('등록에 실패했습니다.');
            }
          }
        });
      });
