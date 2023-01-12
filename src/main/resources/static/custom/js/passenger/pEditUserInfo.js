var userPw = document.getElementById("pUserPw");
var userTel = document.getElementById("pUserTel");


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

