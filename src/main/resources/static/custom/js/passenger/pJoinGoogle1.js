var userEmail = document.getElementById("pUserEmail");
var userPw = document.getElementById("pUserPw");
var userPwCheck = document.getElementById("pUserPwCheck");
var userName = document.getElementById("pUserName");
var userTel = document.getElementById("pUserTel");
var userIdNum = document.getElementById("pIdNumM");


/* 가입유형/성별/인증번호 입력폼 숨김처리 */
$("#inputUserType").css("display", "none");
$("#inputUserGender").css("display", "none");
$("#inputVcode").css("display", "none");

/* 가입유형(일반) 자동 선택 */
$("#pUserType4").attr("checked", true);

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
	if (regPw.test($("#pUserPw").val())) {
		$("#checkPwMsg").html('<span style="color:darkblue"> 확인 완료</span>');
	}
}


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

