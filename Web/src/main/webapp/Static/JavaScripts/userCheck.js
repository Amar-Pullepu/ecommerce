var userFlag = false;

var TypingTimer;                //timer identifier
var DoneTypingInterval = 1500;  //time in ms, 3 second for example
var $input = $('#username');

$input.on('keyup', function () {
  clearTimeout(TypingTimer);
  TypingTimer = setTimeout(userCheck, DoneTypingInterval);
});

//on keydown, clear the countdown 
$input.on('keydown', function () {
  clearTimeout(TypingTimer);
});

function userCheck() {
    userFlag = true;
    var username = document.getElementById("username").value;
	if(username === ""){
		document.getElementById("username").style.borderColor  = "#e03024";
		return;
	}	
    $.ajax({
        url: '/account/is-username-taken',
        async: false,
        data: {
          'username': username
        },
        dataType: 'json',
        success: function (data) {
          if (data.bool === "true") {
            userFlag = false;
          }
        }
      });
    
    if(!userFlag){
        document.getElementById("username").style.borderColor  = "#e03024";
    }
    else{
        document.getElementById("username").style.borderColor = "#34eb4f";
    }
}