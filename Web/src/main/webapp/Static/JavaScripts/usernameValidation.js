function validate() {
    userCheck();
    if(!userFlag){
        alert("Username already taken");
        return false;
    }
    return true;
}