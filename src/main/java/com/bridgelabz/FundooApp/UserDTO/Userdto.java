package com.bridgelabz.FundooApp.UserDTO;

public class Userdto 
 {
	 private long user_id;
	 private String username;
	 private String email_id;
	 private String password;
	 private String confirm_password;
	public Userdto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getUser_id() {
		return user_id;
	}
  public void setUser_id(long user_id) {
		this.user_id = user_id;
		}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	@Override
	public String toString() {
		return "Userdto [user_id=" + user_id + ", username=" + username + ", email_id=" + email_id + ", password="
				+ password + ", confirm_password=" + confirm_password + "]";
	}
	 
}
