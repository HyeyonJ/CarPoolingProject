/* 이메일 인증 (중복 체크 > 인증코드 발송 > 결과 확인) */
$("#checkEmail").click(function () {
	/* 이메일 중복 체크 버튼 */
	$.ajax({
		type: "GET",
		url: "/passenger/join/email/check",
		data: { "email": $("#pUserEmail").val() },
		success: function (res,status) {
			/* 이메일 중복 체크 후 인증 메일 발송 */
			if(res == true){
				/* $('#checkMsg').html('<p style="color:darkblue">이미 사용중인 이메일입니다.</p>'); */
				swal("이미 사용중인 이메일입니다.", "다른 이메일을 입력해주세요.", "error");
			} else {
				swal("사용 가능한 이메일입니다.", "입력하신 이메일로 인증코드가 발송됩니다.", "success").then((OK)=>{
					if(OK) {
						$.ajax({
							type: "GET",
							url: "/passenger/join/email/send",
							data:{
								"email": $("#pUserEmail").val()
							},
							success: ()=>{
							/* 인증 메일 발송 후 인증 코드 입력 폼 출력 */
								$("#inputVcode").css("display", "block");
							}
						})
						
					}
				})
				/* $('#checkMsg').html('<p style="color:darkblue">사용 가능한 이메일입니다.</p>'); */
			}
			
		}
	})		
})