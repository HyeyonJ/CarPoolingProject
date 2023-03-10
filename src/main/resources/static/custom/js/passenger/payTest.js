//const IMP = window.IMP;   // 생략 가능
//IMP.init("imp58566348");	// 예: imp00000000

var msg;
const corsErr = "http://cors-anywhere.herokuapp.com/";

//결제 버튼 클릭
$("#requestPay").click(function () {
	
	const data = {
		    pg: 'html5_inicis', // PG사 선택
		    pay_method: 'card', // 지불 수단
		    merchant_uid: 'carpooling_' + new Date().getTime(), //가맹점에서 구별할 수 있는 고유한id
		    name: 'CarPooling 결제 테스트', // 상품명
		    amount: $('#dFee').val(), // 가격
		    buyer_email: $('#pUserEmail').val(), // 구매자 이메일
		    buyer_name: $('#pUserName').val(), // 구매자 이름
		    buyer_tel: $('#pUserTel').val(), // 구매자 연락처
		    m_redirect_url: 'http://localhost:8080/passenger/payTest/mobile/complete', // 모바일 결제시 사용할 url
		    digital: true, // 실제 물품인지 무형의 상품인지(핸드폰 결제에서 필수 파라미터)
	    	app_scheme: '' // 돌아올 app scheme
		    };
	
	console.log(data);
	IMP.init("imp58566348");
	// IMP.request_pay(param, callback) 결제창 호출
	IMP.request_pay( data, function(rsp) {
		console.log(rsp);
		if(rsp.success){	// 결제 승인 또는 가상계좌 발급에 성공한 경우
			$.ajax({	// jQuery로 HTTP 요청
				type: 'POST',
				url: 'http://localhost:8080/passenger/carpoolingPay/pay',
				headers: { 'Content-Type': 'application/json' },
				data: JSON.stringify(rsp)
		    }).done(function (result) {	// 가맹점 서버 결제 API 성공 시
				alert("please, Check your payment result page!!");
				console.log(result);
				//imp_uid, merchant_uid 추출
				var imp_uid = result.imp_uid;
				console.log(imp_uid);
				var merchant_uid = result.merchant_uid;
				console.log(merchant_uid);
				//액세스 토큰 발급 받기
			    $.ajax({
					url: corsErr + "https://api.iamport.kr/users/getToken",
		            method: "POST",
		            data: {	//REST API 키, REST API Secret
						imp_key: "5051703506060018",
						imp_secret: "CNUjW9iBntIkvX52eLr2lhoQxrkqhmGVeC7KxGcc7Ry0L0VJd9HFRfhCxVMrTZtc2am1RYKnFvJSQl02"
					}
				}).done(function (result) {
					//인증 토큰
					console.log(result.response.access_token);
		            var access_token = result.response.access_token;
		            $.ajax({
		              url: corsErr + "https://api.iamport.kr/payments?imp_uid[]=" + imp_uid,	// imp_uid 전달
		              method: "GET",
		              headers: { "Authorization": access_token },	// 인증 토큰 Authorization header에 추가
		            }).done(function (result) {
						// 조회한 결제 정보
						console.log(result);
						var paymentData = result.response;
						//결제 검증
						if(rsp.paid_amount === paymentData[0].amount) {
							pageReloadAfterPaying();
							window.location.reload();
						} else {
							alert("결제에 실패했습니다." + "에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);
						}
		            }).fail(function(error) {
						alert(JSON.stringify(error));
					})
				})
		   })
		} else {	//결제 실패한 경우
			alert("결제에 실패했습니다." + "에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);
		}
	})
})

function pageReloadAfterPaying() {
	if (sessionStorage.reloadAfterPageLoad) {
		alert("결제가 완료되었습니다.");
		sessionStorage.reloadAfterPageLoad = false;
	}
};


//결제취소 버튼 클릭
$("#cancelPay").click(function() {
	
});