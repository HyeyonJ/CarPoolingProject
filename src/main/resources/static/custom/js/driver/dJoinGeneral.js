var userId = document.getElementById("dUserId");
var userPw = document.getElementById("dUserPw");
var userPwCheck = document.getElementById("dUserPwCheck");
var userEmail = document.getElementById("dUserEmail");
var inputCode = document.getElementById("dUserVcode");
var userTel = document.getElementById("dUserTel");
var userIdNum = document.getElementById("dIdNumM");
var licenseNum = document.getElementById("dLicenseNum");
var licenseIdNum = document.getElementById("dLicenseIdNum");
var carNum = document.getElementById("dCarNum");

/* 가입유형/성별/인증번호 입력폼 숨김처리 */
$("#inputUserType").css("display", "none");
$("#inputUserGender").css("display", "none");
$("#inputVcode").css("display", "none");

/* 가입유형(일반) 자동 선택 */
$("#dUserType1").attr("checked", true);

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
	if (regId.test($("#dUserId").val())) {
		$.ajax({
			type: "GET",
			url: "/driver/check/id",
			data: { id: $("#dUserId").val() },
			success: function(res, status) {
				/* 아이디 중복 체크 */
				if (res == true) {
					Swal.fire(
						"이미 사용중인 아이디입니다.",
						"다른 아이디를 입력해주세요.",
						"error"
					)
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
	if (regPw.test($("#dUserPw").val())) {
		$("#checkPwMsg").html('<span style="color:darkblue"> 확인 완료</span>');
	}
}
/* 패스워드 확인 */
userPwCheck.onkeyup = function() {
	const regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[.$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
	$("#checkPwMsg2").html('<span style="color:red"> 영문, 숫자, 특수문자 각 1개 이상 포함 최소 8자리에서 최대 16자리</span>');
	if (regPw.test($("#dUserPwCheck").val())) {
		$("#checkPwMsg2").html('<span style="color:red"> 불일치</span>');
		if( ($("#dUserPwCheck").val())==($("#dUserPw").val()) ) {
			$("#checkPwMsg2").html('<span style="color:darkblue"> 일치</span>');
		}
	}
};


/* 이메일 인증 (인증코드 발송 > 결과 확인) */
userEmail.onkeyup = function(){
	$("#checkEmailMsg").html('<span style="color:red"> carpooling@sample.com</span>');
	if (regEmail.test($("#dUserEmail").val())) {
		$("#checkEmailMsg").html('<span style="color:red"> 인증 필요</span>');
	}
}
$("#checkEmail").click(function() {
	$("#inputVcode").css("display", "none");
	const regEmail = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	$("#checkEmail").removeClass("btn-outline-dark");
	$("#checkEmail").addClass("btn-dark");
	if (regEmail.test($("#dUserEmail").val())) {
		//이메일 중복 체크
		$.ajax({
			type: "GET",
			url: "/driver/check/email",
			data: { email: $("#dUserEmail").val() },
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
								url: "/driver/send/email",
								data: { email: $("#dUserEmail").val() },
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
	if (regVcode.test($("#dUserVcode").val())) {
		$.ajax({
			type: "GET",
			url: "/driver/check/vCode",
			data: { code: $("#dUserVcode").val() },
			success: function(res, status) {
				console.log(res);
				console.log(status);
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
							inputCode.value = null;
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

userTel.onkeyup = function () {
  this.value = autoHypenPhone(this.value);
  
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
    $("#dIdNum").val(tmp);
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
	if (regIdNum.test($("#dIdNum").val())) {
		$("#checkIdNumMsg").html('<span style="color:red"> 확인 필요</span>');
		$.ajax({
			type: "GET",
			url: "/driver/check/idNum",
			data: { idNum: $("#dIdNum").val() },
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
    $("#dIdNum").val().substr(7, 1) === "1" ||
    $("#dIdNum").val().substr(7, 1) === "3"
  ) {
    $("#dUserGender1").attr("checked", true);
  } else {
    $("#dUserGender2").attr("checked", true);
  }
};

/* 면허 번호 '-' 자동 입력 */
var autoHypenLicenseNum = function (licenseNum) {
  licenseNum = licenseNum.replace(/[^0-9]/g, "");
  var tmp = "";
  if (licenseNum.length < 3) {
    return licenseNum;
  } else if (licenseNum.length < 5) {
    tmp += licenseNum.substr(0, 2);
    tmp += "-";
    tmp += licenseNum.substr(2);
    return tmp;
  } else if (licenseNum.length < 11) {
    tmp += licenseNum.substr(0, 2);
    tmp += "-";
    tmp += licenseNum.substr(2, 2);
    tmp += "-";
    tmp += licenseNum.substr(4);
    return tmp;
  } else {
    tmp += licenseNum.substr(0, 2);
    tmp += "-";
    tmp += licenseNum.substr(2, 2);
    tmp += "-";
    tmp += licenseNum.substr(4, 6);
    tmp += "-";
    tmp += licenseNum.substr(10);
    return tmp;
  }
  return licenseNum;
};

licenseNum.onkeyup = function() {
	this.value = autoHypenLicenseNum(this.value);
	const regLicenseNum = /^(\d{2}-\d{2}-\d{6}-\d{2})$/;
	$("#checkLicenseNumMsg").html('<span style="color:red"> 12자리 숫자만 입력</span>');
	if (regLicenseNum.test($("#dLicenseNum").val())) {
		$.ajax({
			type: "GET",
			url: "/driver/check/license",
			data: { licenseNum: $("#dLicenseNum").val() },
			success: function(res, status) {
				if (res == true) {
					$("#checkLicenseNumMsg").html('<span style="color:red"> 이미 등록된 면허입니다.</span>');
				} else {
					$("#checkLicenseNumMsg").html('<span style="color:darkblue"> 확인 완료</span>');
				}

			}
		});

	}
};


licenseIdNum.onkeyup = function() {
	const regLicenseIdNum = /^([A-Z0-9]{5,6})$/;
	$("#checkLIdNumMsg").html('<span style="color:red"> 영문대문자+숫자 5자리 혹은 6자리(면허증 우측 사진 아래 식별번호)</span>');
	if (regLicenseIdNum.test($("#dLicenseIdNum").val())) {
		$("#checkLIdNumMsg").html('<span style="color:darkblue"> 확인 완료</span>');
	}
}

carNum.onkeyup = function() {
	const regCarNum = /(\d{2,3})([가-힣]{1})(\d{4})/;
	$("#checkCarNumMsg").html('<span style="color:red"> 00땡0000, 000땡0000</span>');
	if (regCarNum.test($("#dCarNum").val())) {
		$.ajax({
			type: "GET",
			url: "/driver/check/carNum",
			data: { carNum: $("#dCarNum").val() },
			success: function(res, status) {
				if (res == true) {
					$("#checkCarNumMsg").html('<span style="color:red"> 이미 등록된 자동차입니다.</span>');
				} else {
					$("#checkCarNumMsg").html('<span style="color:darkblue"> 확인 완료</span>');
				}
			}
		});
	}
};