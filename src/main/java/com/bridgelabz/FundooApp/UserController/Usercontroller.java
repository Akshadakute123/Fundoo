
package com.bridgelabz.FundooApp.UserController;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooApp.Exceptions.UserExceptions;
import com.bridgelabz.FundooApp.Exceptions.Response;
import com.bridgelabz.FundooApp.Repository.UserRepository;
import com.bridgelabz.FundooApp.UserDTO.ForgetPasswordDTO;
import com.bridgelabz.FundooApp.UserDTO.LoginDTO;
import com.bridgelabz.FundooApp.UserDTO.ResetPasswordDto;
import com.bridgelabz.FundooApp.UserModell.UserInformation;
import com.bridgelabz.FundooApp.UserService.Loginservice;
import com.bridgelabz.FundooApp.UserService.UserService;
import com.bridgelabz.FundooApp.Utility.TokenService;

@RestController
@RequestMapping("/home")
public class Usercontroller {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private UserService userSevice;
	@Autowired
	private Loginservice loginservice;
	@Autowired
	private TokenService tokenservice;

	@PostMapping("/newRegistration")
	public Response newregistration(@Valid @RequestBody UserInformation userInformation) throws UserExceptions {

		userSevice.userRegistration(userInformation);
		return new Response(new Date(), "Registration succesfull ", 200, "OK");

	}

	@GetMapping("/get")
	public List<UserInformation> getinformation() {
		return userSevice.userinfo();
	}

	@PostMapping("/login")
	public Response Logininfo(@Valid @RequestBody LoginDTO logindto) throws UserExceptions {
		String usertoken = tokenservice.createToken(logindto.getemail());

		userSevice.login(logindto);

		return new Response(new Date(), "login succesfull", 200, usertoken);
	}

	@PostMapping("/forget")
	public Response forgetpassword(@RequestBody ForgetPasswordDTO forgetpassword) throws UserExceptions {
		userSevice.forgetPassword(forgetpassword);
		return new Response(new Date(), "forget password link send on your registered email id", 200, "ok");

	}

	@PostMapping("/reset")
	public Response resetpassword(@Valid @RequestBody ResetPasswordDto restepassworddto) throws UserExceptions {
		userSevice.resetPasswords(restepassworddto);
		return new Response(new Date(), " password reset succesully", 200, "ok");

	}
}
