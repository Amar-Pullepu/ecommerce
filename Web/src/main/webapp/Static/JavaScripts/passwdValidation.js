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
    return true;
}