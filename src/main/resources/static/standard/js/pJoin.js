/* modal 사용 */
var myModal = document.getElementById('myModal')
var myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', function () { myInput.focus() })

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

window.onload = function () {
	
$("#checkEmail").click(function () {
	let pUserEmail = $("#pUserEmail").val();

$.ajax({
	type: "GET",
	url: "/join/check",
	data: {
		"email": pUserEmail
	},
	success: function (res) {
		if(res['check']){
			$('#checkMsg').html('<p style="color:red">이미 사용중인 이메일 입니다.</p>');
		} else {
			swal("발송 완료!", "입력하신 이메일로 인증코드가 발송되었습니다.", "success").then((OK)=>{
				if(OK) {
					$.ajax({
						type: "POST",
						url: "/join/mail",
						data:{
							"email": pUserEmail
						}						
					})
					
				}
			})
			$('#checkMsg').html('<p style="color:darkblue"></p>');
		}
	}
})

})

}