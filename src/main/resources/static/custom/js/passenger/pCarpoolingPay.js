//결제 버튼 클릭
$("#requestPay").click(function() {
	IMP.init("imp58566348");

	$.ajax({
		type: 'GET',
		url: '/passenger/carpoolingPay/rqPay'
	}).done(function(user) {
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
			digital: true, // 실제 물품인지 무형의 상품인지(핸드폰 결제에서 필수 파라미터)
			app_scheme: '' // 돌아올 app scheme
		};

		// IMP.request_pay(param, callback) 결제창 호출
		IMP.request_pay(data, function(rsp) {
			if (rsp.success) {	// 결제 승인 성공
				//console.log("결제 승인 완료");
				const imp_uid = rsp.imp_uid;
				//console.log("결제 검증 진행");					
				//결제 검증 요청
				$.ajax({
					type: 'POST',
					url: '/passenger/carpoolingPay/verifyIamport/' + imp_uid
				}).done(function(result) {	// 가맹점 서버 결제 API > 토큰 발급 > imp_uid에 해당하는 결제정보 조회
					console.log(result.response);
					var paymentData = result.response;
					//결제 요청 금액과 결제 처리 완료된 금액 비교
					if (rsp.paid_amount === paymentData.amount) {
						console.log("(검증 성공 : 결제 완료");
						alert("결제가 성공적으로 완료되었습니다.");
						$.ajax({
							type: 'POST',
							url: '/passenger/carpoolingPay/complete',
							data: {
								payIdx : paymentData.merchant_uid,
								pIdx : user.pIdx,
								amount : paymentData.amount
							}
						})
						console.log(data);
						console.log("결제 테이블 저장 완료");
					} else {
						console.log("검증 실패 : 결제 금액 확인 요망")
						console.log("에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);
					}
				}).fail(function(error) {
					console.log("검증 불가 : 검증 로직 확인 요망");
					console.log("error : " + error);
				})
			} else {	//결제 승인 실패
				console.log("결제 승인 실패");
				alert("결제 실패했습니다." + "에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);
			}
		})
	})
});

//결제취소 버튼 클릭
$("#cancelPay").click(function() {
	$.ajax({
		url: "{환불요청을 받을 서비스 URL}", // 예: http://www.myservice.com/payments/cancel
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			merchant_uid: "{결제건의 주문번호}", // 예: ORD20180131-0000011
			cancel_request_amount: {r}, // 환불금액
			reason: "카풀링 예약 취소" // 환불사유
		}),
		dataType: "json"
	});
});