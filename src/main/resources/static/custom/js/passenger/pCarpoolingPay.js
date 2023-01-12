//결제 버튼 클릭
$("#requestPay").click(function() {
	IMP.init("imp58566348");

	$.ajax({
		type: 'GET',
		url: '/passenger/carpoolingPay/request'
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
				//console.log("거래 승인 완료");
				const imp_uid = rsp.imp_uid;
				//console.log("결제 검증 진행");					
				//결제 검증 요청
				$.ajax({	//가맹점 서버 결제 API > 토큰 발급 > imp_uid에 해당하는 결제 정보 조회
					type: 'POST',
					url: '/passenger/carpoolingPay/verifyIamport/' + imp_uid
				}).done(function(result) {
					console.log("검증 완료 : 결제 성공");
					console.log(result.response);
				}).fail(function(error) {
					console.log("검증 불가 : 검증 로직 확인 요망");
					console.log("error : " + error);
				})
			} else {	//결제 승인 실패
				console.log("거래 승인 실패");
				alert("결제 실패했습니다." + "에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);
			}
		})
	})
});

//결제취소 버튼 클릭


$("#cancelPay").click(function() {
const corsErr = "http://cors-anywhere.herokuapp.com/";
	$.ajax({
		url: "/passenger/carpoolingPay/cancel/requestIamport", // 토큰 요청
		type: "POST"
	}).done(function(data, status) {
		console.log(data.response);
		console.log(data.response.token);
		const token = data.response.token;
		$.ajax({
			url: corsErr + "https://api.iamport.kr/payments/cancel",
			method: "post",
			headers: {
				"Authorization": token // 아임포트 서버로부터 발급받은 엑세스 토큰
			},
			data: {
				merchant_uid: "carpooling_1673322965492", // 예: carpooling_12345567677888
				amount: 1000, // 환불금액
				reason: "Carpooling 예약 취소"
			}
		}).done(function(data, status) {			
			console.log("data : " + data);
			console.log("status : " + status);
		}).fail(function(error){
			console.log("error : " + error);
		})

	});





});
