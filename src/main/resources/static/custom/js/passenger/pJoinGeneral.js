var userId = document.getElementById("pUserId");
var userPw = document.getElementById("pUserPw");
var userPwCheck = document.getElementById("pUserPwCheck");
var userName = document.getElementById("pUserName");
var userIdNum = document.getElementById("pIdNumM");
var userEmail = document.getElementById("pUserEmail");
var inputCode = document.getElementById("pUserVcode");
var userTel = document.getElementById("pUserTel");


/* 가입유형/성별/인증번호 입력폼 숨김처리 */
$("#inputUserType").css("display", "none");
$("#inputUserGender").css("display", "none");
$("#inputVcode").css("display", "none");

/* 가입유형(일반) 자동 선택 */
$("#pUserType1").attr("checked", true);

/* bootstrap 유효성 검사 */
window.addEventListener(
  "load",
  () => {
    const forms = document.getElementsByClassName("validation-form");

    Array.prototype.filter.call(forms, (form) => {
      form.addEventListener(
        "submit",
        function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add("was-validated");
        },
        false
      );
    });
  },
  false
);


/* 아이디 체크 */
userId.onkeyup = function(){
	const regId = /^[a-zA-Z0-9]{4,12}$/;
	$("#checkIdMsg").html('<span style="color:red"> 영문, 숫자 포함 4자리에서 최대 12자리</span>');
	if (regId.test($("#dUserId").val())) {
		$("#checkIdMsg").html('<span style="color:red"> 중복 확인 필요</span>');
	}
}

$("#checkId").click(function() {
	$("#checkId").removeClass("btn-outline-dark");
	$("#checkId").addClass("btn-dark");
	const regId = /^[a-zA-Z0-9]{4,12}$/;
	if (regId.test($("#pUserId").val())) {
		$.ajax({
			type: "GET",
			url: "/passenger/check/id",
			data: { id: $("#pUserId").val() },
			success: function(res, status) {
				/* 아이디 중복 체크 */
				if (res == true) {
					Swal.fire(
						"이미 사용중인 아이디입니다.",
						"다른 아이디를 입력해주세요.",
						"error"
					);
				} else {
					Swal.fire(
						"사용 가능한 아이디입니다.",
						"회원가입을 진행해주세요.",
						"success"
					).then((OK) => {
						if (OK) {
							/* 확인 완료 후 버튼색상, 메세지 변경 */
							$("#checkId").removeClass("btn-dark");
							$("#checkId").addClass("btn-outline-dark");
							$("#checkIdMsg").html('<span style="color:darkblue"> 확인 완료</span>');
						}
					});
				}
			},
		});
	} else {
		Swal.fire("사용할 수 없는 아이디입니다.", "다시 확인해주세요.", "error");
	}
});


/* 패스워드 체크 */
userPw.onkeyup = function() {
	const regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[.$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
	$("#checkPwMsg").html('<span style="color:red"> 영문, 숫자, 특수문자 각 1개 이상 포함 최소 8자리에서 최대 16자리</span>');
	if (regPw.test($("#pUserPw").val())) {
		$("#checkPwMsg").html('<span style="color:darkblue"> 확인 완료</span>');
	}
}

/* 패스워드 확인 */
userPwCheck.onkeyup = function() {
	const regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[.$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
	$("#checkPwMsg2").html('<span style="color:red"> 영문, 숫자, 특수문자 각 1개 이상 포함 최소 8자리에서 최대 16자리</span>');
	if (regPw.test($("#pUserPwCheck").val())) {
		$("#checkPwMsg2").html('<span style="color:red"> 불일치</span>');
		if( ($("#pUserPwCheck").val())==($("#pUserPw").val()) ) {
			$("#checkPwMsg2").html('<span style="color:darkblue"> 일치</span>');
		}
	}
};


/* 이름 입력 */
userName.onkeyup = function(){
	const regName = /^([가-힣a-zA-Z]{2,10})$/;
	$("#checkNameMsg").html('<span style="color:red"> 한글/영문 최대 10자리</span>');
	if (regName.test($("#pUserName").val())) {
		$("#checkNameMsg").html('<span style="color:darkblue"> 사용 가능</span>');
	}
};


/* 주민등록번호 '-' 자동 입력 */
var autoHypenIdNum = function (idNum) {
  idNum = idNum.replace(/[^0-9]/g, "");
  var tmp = "";
  if (idNum.length < 7) {
    return idNum;
  } else {
    tmp += idNum.substr(0, 6);
    tmp += "-";
    tmp += idNum.substr(6);
    $("#pIdNum").val(tmp);
    return tmp;
  }
  return idNum;
};

var autoMaskingIdNum = function (idNum) {
  tmp = idNum.replaceAll(/([0-9]{6})-([1-4]{1})([0-9]{6})/g, "$1-$2******");
  return tmp;
};

userIdNum.onkeyup = function () {
  this.value = autoHypenIdNum(this.value);
  const regIdNum = /^([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1])-[1-4][0-9*]{6})$/;
  $("#checkIdNumMsg").html('<span style="color:red"> 13자리 숫자만 입력</span>');
	if (regIdNum.test($("#pIdNum").val())) {
		$("#checkIdNumMsg").html('<span style="color:red"> 확인 필요</span>');
		$.ajax({
			type: "GET",
			url: "/passenger/check/idNum",
			data: { idNum: $("#pIdNum").val() },
			success: function (res, status) {
				if(res == true) {
					$("#checkIdNumMsg").html('<span style="color:red"> 이미 가입된 주민등록번호입니다.</span>');
				} else {
					$("#checkIdNumMsg").html('<span style="color:darkblue"> 확인 완료</span>');
				}

			}
		});
	}
};

userIdNum.onblur = function () {
  this.value = autoMaskingIdNum(this.value);
  /* 성별 자동 선택*/
  if (
    $("#pIdNum").val().substr(7, 1) === "1" ||
    $("#pIdNum").val().substr(7, 1) === "3"
  ) {
    $("#pUserGender1").attr("checked", true);
  } else {
    $("#pUserGender2").attr("checked", true);
  }
};


/* 이메일 인증 (인증코드 발송 > 결과 확인) */
userEmail.onkeyup = function(){
	const regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	$("#checkEmailMsg").html('<span style="color:red"> carpooling@sample.com</span>');
	if (regEmail.test($("#pUserEmail").val())) {
		$("#checkEmailMsg").html('<span style="color:red"> 인증 필요</span>');
	}
}
$("#checkEmail").click(function() {
	$("#inputVcode").css("display", "none");
	inputCode.value = null;
	const regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	$("#checkEmail").removeClass("btn-outline-dark");
	$("#checkEmail").addClass("btn-dark");
	if (regEmail.test($("#pUserEmail").val())) {
		//이메일 중복 체크
		$.ajax({
			type: "GET",
			url: "/passenger/check/email",
			data: { email: $("#pUserEmail").val() },
			success: function(res, status) {
				if (res == false) {
					Swal.fire(
						"사용 가능한 이메일입니다.",
						"입력하신 이메일로 인증코드가 발송됩니다.",
						"success"
					).then((OK) => {
						if (OK) {
							/* 인증 코드 입력 폼 출력 & 인증 메일 발송 */
							$("#inputVcode").css("display", "block");
							$.ajax({
								type: "GET",
								url: "/passenger/send/email",
								data: { email: $("#pUserEmail").val() },
								success: () => {
									/* 유효시간 출력 */
								}
							});
						}
					});
				} else {
					Swal.fire(
						"이미 사용중인 이메일 입니다.",
						"다시 입력하시고 인증 버튼을 눌러주세요.",
						"error"
					);
				}
			}
		});
	}
});

/* 인증 코드 입력 & 확인 버튼 */
$("#checkVcode").click(function() {
	const regVcode = /^[A-Z0-9]{10}$/;
	if (regVcode.test($("#pUserVcode").val())) {
		$.ajax({
			type: "GET",
			url: "/passenger/check/vCode",
			data: { code: $("#pUserVcode").val() },
			success: function(res, status) {
				if (res == true) {
					Swal.fire(
						"이메일 인증이 완료되었습니다.",
						"회원가입을 진행해주세요.",
						"success"
					).then((OK) => {
						if (OK) {
							/* 인증완료 후 입력폼 숨기기, 버튼색상/메세지 변경, 입력폼 숨기기 */
							$("#checkEmail").removeClass("btn-dark");
							$("#checkEmail").addClass("btn-outline-dark");
							$("#inputVcode").css("display", "none");
							$("#checkEmailMsg").html('<span style="color:darkblue"> 인증 완료</span>');
						}
					});
				} else {
					Swal.fire(
						"인증코드가 일치하지않습니다.",
						"인증코드를 다시 확인해주세요.",
						"error"
					);
				}
			},
		});
	} else {
		Swal.fire("잘못 입력하셨습니다.", "인증코드를 다시 확인해주세요.", "error");
	}
});


/* 휴대폰 번호 '-' 자동 입력 */
var autoHypenPhone = function (tel) {
  tel = tel.replace(/[^0-9]/g, "");
  var tmp = "";
  if (tel.length < 4) {
    return tel;
  } else if (tel.length < 7) {
    tmp += tel.substr(0, 3);
    tmp += "-";
    tmp += tel.substr(3);
    return tmp;
  } else if (tel.length < 11) {
    tmp += tel.substr(0, 3);
    tmp += "-";
    tmp += tel.substr(3, 3);
    tmp += "-";
    tmp += tel.substr(6);
    return tmp;
  } else {
    tmp += tel.substr(0, 3);
    tmp += "-";
    tmp += tel.substr(3, 4);
    tmp += "-";
    tmp += tel.substr(7);
    return tmp;
  }
  return tel;
};

userTel.onkeyup = function() {
	this.value = autoHypenPhone(this.value);
	const regTel = /^(010-\d{3,4}-\d{4})$/;
	$("#checkTelMsg").html('<span style="color:red"> 010포함 숫자만 입력</span>');
	if (regTel.test($("#pUserTel").val())) {
		$("#checkTelMsg").html('<span style="color:darkblue"> 확인 완료</span>');
	}
};

