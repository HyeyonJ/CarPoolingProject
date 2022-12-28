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


var pUserTel = document.getElementById('pUserTel');

pUserTel.onkeyup = function(){
  console.log(this.value);
  this.value = autoHypenPhone( this.value ) ;  
}










  $('#checkId').click(function(){
    if( $(this).hasClass('btn-outline-success') ) $(this).removeClass('btn-outline-success');
    if( !$(this).hasClass('btn-success') ) $(this).addClass('btn-success');
    if( $('#checkEmail').hasClass('btn-success') ) $('#checkEmail').removeClass('btn-success');
    if( !$('#checkEmail').hasClass('btn-outline-success') ) $('#checkEmail').addClass('btn-outline-success');
  });
  
  $('#checkEmail').click(function(){
    if( $(this).hasClass('btn-outline-success') ) $(this).removeClass('btn-outline-success');
    if( !$(this).hasClass('btn-success') ) $(this).addClass('btn-success');
    if( $('#checkId').hasClass('btn-success') ) $('#checkId').removeClass('btn-success');
    if( !$('#checkId').hasClass('btn-outline-success') ) $('#checkId').addClass('btn-outline-success');
  });


$('#checkId').removeClass('btn-success');
$('#checkId').addClass('btn-outline-success');

$('#checkEmail').removeClass('btn-success');
$('#checkEmail').addClass('btn-outline-success');