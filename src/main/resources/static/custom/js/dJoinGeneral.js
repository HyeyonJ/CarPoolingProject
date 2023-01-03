/* $("#dUserVcode").css("display", "none");
$("#sendVcode").css("display", "none"); */
$("#inputVcode").css("display", "none");
$('#checkIdMsg').html('<span style="color:red">아이디 중복 확인 필요</span>');
$('#checkEmailMsg').html('<span style="color:red">이메일 인증 필요</span>');

/* 가입유형 자동 선택 (일반), 숨김처리 */
$("#dUserType1").attr("checked", true);
$("#inputUserType").css("display", "none");

/* bootstrap 유효성 검사 */
window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');

    Array.prototype.filter.call(forms, (form) => {
	    form.addEventListener('submit', function (event) {
	    	if (form.checkValidity() === false) {
	        	event.preventDefault();
	            event.stopPropagation();
	        }
	        form.classList.add('was-validated');
	    }, false);
    });
}, false);


/* 아이디 중복 체크 */
$("#checkId").click(function() {
	const regId = /^[a-zA-Z0-9]{4,12}$/;
	if ( regId.test($("#dUserId").val()) ) {
		$.ajax({
			type: "GET",
			url: "/driver/check/id",
			data: { "id": $("#dUserId").val() },
			success: function (res,status) {
				/* 아이디 중복 체크 */
				if(res == true){
					swal("이미 사용중인 아이디입니다.", "다른 아이디를 입력해주세요.", "error");
				} else {
					swal("사용 가능한 아이디입니다.", "이메일 인증을 진행해주세요.", "success").then((OK)=>{
						if(OK) {
							/* 중복확인 후 버튼색상, 메세지 변경 */
							$('#checkId').removeClass('btn-dark');
							$('#checkId').addClass('btn-outline-dark');
							$('#checkIdMsg').html('<span style="color:darkblue">아이디 중복 확인 완료</span>');
						}
					})
				}
			}
		})
	} else { swal("사용할 수 없는 아이디입니다.", "다시 확인해주세요.", "error"); }
})


/* 이메일 인증 (인증코드 발송 > 결과 확인) */
$("#checkEmail").click(function () {
	const reg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	if( reg.test($("#dUserEmail").val()) ) {
		swal("사용 가능한 이메일입니다.", "입력하신 이메일로 인증코드가 발송됩니다.", "success").then((OK)=>{
			if(OK) {
				$.ajax({
						type: "GET",
						url: "/driver/send/email",
						data:{ "email": $("#dUserEmail").val() },
						success: ()=>{ /* 인증 메일 발송 후 인증 코드 입력 폼 출력 */
						$("#inputVcode").css("display", "block"); }
				})
								
			}
		})
	} else {
		swal("이메일을 확인해주세요.", "다시 입력하시고 인증 버튼을 눌러주세요.", "error")
	}
})


/* 인증 코드 입력 & 확인 버튼 */
$("#checkVcode").click(function () {
	const regVcode = /^[A-Z0-9]{10}$/;
	if ( regVcode.test($("#dUserVcode").val()) ){
		$.ajax({
				type: "GET",
				url: "/driver/check/vCode",
				data: { "code": $("#dUserVcode").val() },
				success: function (res,status) {
					console.log(res);
					console.log(status);
					if(res == true){
						swal("이메일 인증이 완료되었습니다.", "회원가입을 진행해주세요.", "success").then((OK)=>{
							if(OK) {
								/* 인증완료 후 입력폼 숨기기, 버튼색상/메세지 변경, 입력폼 숨기기 */
								$('#checkEmail').removeClass('btn-dark');
								$('#checkEmail').addClass('btn-outline-dark');
								$("#inputVcode").css("display", "none");
								$('#checkEmailMsg').html('<span style="color:darkblue">이메일 인증 완료</span>');
							}
						})
					} else {
						swal("인증코드가 일치하지않습니다.", "인증코드를 다시 확인해주세요.", "error")
					}
				}
		})
	} else { swal("잘못 입력하셨습니다.", "인증코드를 다시 확인해주세요.", "error") }
})


/* 휴대폰 번호 '-' 자동 입력 */
var autoHypenPhone = function(tel){
      tel = tel.replace(/[^0-9]/g, '');
      var tmp = '';
      if( tel.length < 4){
          return tel;
      }else if(tel.length < 7){
          tmp += tel.substr(0, 3);
          tmp += '-';
          tmp += tel.substr(3);
          return tmp;
      }else if(tel.length < 11){
          tmp += tel.substr(0, 3);
          tmp += '-';
          tmp += tel.substr(3, 3);
          tmp += '-';
          tmp += tel.substr(6);
          return tmp;
      }else{              
          tmp += tel.substr(0, 3);
          tmp += '-';
          tmp += tel.substr(3, 4);
          tmp += '-';
          tmp += tel.substr(7);
          return tmp;
      }  
      return tel;
}

var dUserTel = document.getElementById('dUserTel');

dUserTel.onkeyup = function(){
  this.value = autoHypenPhone( this.value ) ;  
}


/* 면허 번호 '-' 자동 입력 */
var autoHypenLicenseNum = function(licenseNum){
      licenseNum = licenseNum.replace(/[^0-9]/g, '');
      var tmp = '';
      if( licenseNum.length < 3){
          return licenseNum;
      }else if(licenseNum.length < 5){
          tmp += licenseNum.substr(0, 2);
          tmp += '-';
          tmp += licenseNum.substr(2);
          return tmp;
      }else if(licenseNum.length < 11){
          tmp += licenseNum.substr(0, 2);
          tmp += '-';
          tmp += licenseNum.substr(2, 2);
          tmp += '-';
          tmp += licenseNum.substr(4);
          return tmp;
      } else{              
          tmp += licenseNum.substr(0, 2);
          tmp += '-';
          tmp += licenseNum.substr(2, 2);
          tmp += '-';
          tmp += licenseNum.substr(4, 6);
          tmp += '-';
          tmp += licenseNum.substr(10);
          return tmp;
      }
      return licenseNum;
}

var dLicenseNum = document.getElementById('dLicenseNum');

dLicenseNum.onkeyup = function(){
  this.value = autoHypenLicenseNum( this.value ) ;
}