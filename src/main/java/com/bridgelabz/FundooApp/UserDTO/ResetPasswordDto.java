package com.bridgelabz.FundooApp.UserDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ResetPasswordDto {
	@NotEmpty(message = "email field should not be empty")
	@Email(message = "please enter valid email adress")
	private String email;
	@Pattern(regexp = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})", message = "password must contains the special symbols,numbers and characters")

	private String password;
	@Pattern(regexp = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})", message = "password mismatch")

	private String confirm_password;

	public ResetPasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "ResetPasswordDto [email=" + email + ", password=" + password + ", confirm_password=" + confirm_password
				+ "]";
	}

}
