var passwdFlag = false;
var rePasswdFlag = false;

var $passwd = $('#password');
$passwd.on('input', function () {
    checkPassword()
});

var $repasswd = $('#repassword');
$repasswd.on('input', function () {
    checkRePassword()
});


function checkPassword() {
    var passwd = document.getElementById("password").value;
    passwdFlag = false;
    if(passwd.length < 8){
        document.getElementById("password").style.borderColor  = "#e03024";
    }
    else{
        document.getElementById("password").style.borderColor = "#34eb4f";
        passwdFlag = true;
    } 
}

function checkRePassword() {
    var rePasswd = document.getElementById("repassword").value;
    var passwd = document.getElementById("password").value;
    rePasswdFlag = false;
    if(rePasswd !== passwd){
        document.getElementById("repassword").style.borderColor  = "#e03024";
    }
    else{
        document.getElementById("repassword").style.borderColor = "#34eb4f";
        rePasswdFlag = true;
    } 
}

