function validate() {
    checkPassword();
    if(!passwdFlag){
        alert("Password length should be minimum 8 Charecters");
        return false;
    }
	checkRePassword();
	if(!rePasswdFlag){
        alert("Both passwords didn't match");
        return false;
    }
	userCheck();
	if(!userFlag){
        alert("Username already taken");
        return false;
    }
    return true;
}