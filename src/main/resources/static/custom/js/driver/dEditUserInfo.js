var userPw = document.getElementById("dUserPw");
var userTel = document.getElementById("dUserTel");
var accountBank = document.getElementById("dAccountBank");
var accountNum = document.getElementById("dAccountNum");
var licenseNum = document.getElementById("dLicenseNum");
var licenseIdNum = document.getElementById("dLicenseIdNum");
var carNum = document.getElementById("dCarNum");


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


/* 패스워드 체크 */
userPw.onkeyup = function() {
	const regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[.$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
	$("#checkPwMsg").html('<span style="color:red"> 영문, 숫자, 특수문자 각 1개 이상 포함 최소 8자리에서 최대 16자리</span>');
	if (regPw.test($("#dUserPw").val())) {
		$("#checkPwMsg").html('<span style="color:darkblue"> 확인 완료</span>');
	}
}


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
	if (regTel.test($("#dUserTel").val())) {
		$("#checkTelMsg").html('<span style="color:darkblue"> 확인 완료</span>');
	}
};


/* 계좌 정보 체크 */
accountBank.onkeyup = $("#checkAccBankMsg").html('<span style="color:red"> 신한</span>');
accountNum.onkeyup = $("#checkAccNumMsg").html('<span style="color:red"> 110xxxoooooo</span>');

accountBank.onblur = $("#checkAccBankMsg").html('<span style="color:darkblue"> 일단 입력</span>');
accountNum.onblur = $("#checkAccNumMsg").html('<span style="color:darkblue"> 추후 보완합시다.</span>');



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
	const regCarNum = /^(\d{2,3})([가-힣]{1})(\d{4})$/;
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

