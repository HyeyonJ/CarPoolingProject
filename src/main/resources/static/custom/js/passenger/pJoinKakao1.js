console.log("asd");
$('#checkIdMsg').html('<span style="color:red">아이디 중복 확인 필요</span>');
$('#checkEmailMsg').html('<span style="color:red">이메일 인증 필요</span>');

/* 가입유형/성별/인증번호 입력폼 숨김처리 */
$("#inputUserType").css("display", "none");
$("#inputUserGender").css("display", "none");
$("#inputVcode").css("display", "none");

/* 가입유형(일반) 자동 선택 */
$("#pUserType1").attr("checked", true);

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
	if ( regId.test($("#pUserId").val()) ) {
		$.ajax({
			type: "GET",
			url: "/passenger/check/id",
			data: { "id": $("#pUserId").val() },
			success: function (res,status) {
				/* 아이디 중복 체크 */
				if(res == true){
					swal("이미 사용중인 아이디입니다.", "다른 아이디를 입력해주세요.", "error");
				} else {
					swal("사용 가능한 아이디입니다.", "회원가입을 진행해주세요.", "success").then((OK)=>{
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
	const regEmail = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	if( regEmail.test($("#pUserEmail").val()) ) {
		swal("사용 가능한 이메일입니다.", "입력하신 이메일로 인증코드가 발송됩니다.", "success").then((OK)=>{
			if(OK) {
				$.ajax({
						type: "GET",
						url: "/passenger/send/email",
						data:{ "email": $("#pUserEmail").val() },
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
	if ( regVcode.test($("#pUserVcode").val()) ){
		$.ajax({
				type: "GET",
				url: "/passenger/check/vCode",
				data: { "code": $("#pUserVcode").val() },
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
      } else if(tel.length < 7){
          tmp += tel.substr(0, 3);
          tmp += '-';
          tmp += tel.substr(3);
          return tmp;
      } else if(tel.length < 11){
          tmp += tel.substr(0, 3);
          tmp += '-';
          tmp += tel.substr(3, 3);
          tmp += '-';
          tmp += tel.substr(6);
          return tmp;
      } else {              
          tmp += tel.substr(0, 3);
          tmp += '-';
          tmp += tel.substr(3, 4);
          tmp += '-';
          tmp += tel.substr(7);
          return tmp;
      }  
      return tel;
}

var userTel = document.getElementById('pUserTel');

userTel.onkeyup = function(){
  this.value = autoHypenPhone( this.value ) ;  
}

/* 주민등록번호 '-' 자동 입력 */
var autoHypenIdNum = function(idNum){
	idNum = idNum.replace(/[^0-9]/g, '');
      var tmp = '';
      if( idNum.length < 7 ) {		  
          return idNum;
      } else {
          tmp += idNum.substr(0, 6);
          tmp += '-';
          tmp += idNum.substr(6);
          $("#dIdNum").val(tmp);
          return tmp;          
      }
      return idNum;
}

var autoMaskingIdNum = function(idNum){
	tmp = idNum.replaceAll(/([0-9]{6})-([1-4]{1})([0-9]{6})/g, "$1-$2******");
	return tmp;
}

var idNum = document.getElementById('pIdNumM');

idNum.onkeyup = function(){
	this.value = autoHypenIdNum( this.value );
}

idNum.onblur = function(){
	this.value = autoMaskingIdNum (this.value);
/* 성별 자동 선택*/
	if (($("#pIdNum").val()).substr(7,1) === "1" || ($("#pIdNum").val()).substr(7,1) === "3" ){
		$("#pUserGender1").attr("checked", true);
	} else {
		$("#pUserGender2").attr("checked", true);
	};
}


