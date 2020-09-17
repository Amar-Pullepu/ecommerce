package com.ecommerce.service.user;

public interface UserRegisterService{
	public void save(String username, String password);
	public boolean changePassword(String username, String oldPasswd, String passwd);
	public boolean changeUsername(String currUsername, String newUsername);
	public boolean isUserPresent(String username);
}
