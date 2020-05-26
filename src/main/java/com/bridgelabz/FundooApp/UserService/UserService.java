package com.bridgelabz.FundooApp.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.FundooApp.Exceptions.Response;
import com.bridgelabz.FundooApp.Exceptions.UserExceptions;
import com.bridgelabz.FundooApp.Repository.UserRepository;
import com.bridgelabz.FundooApp.UserDTO.ForgetPasswordDTO;
import com.bridgelabz.FundooApp.UserDTO.LoginDTO;
import com.bridgelabz.FundooApp.UserDTO.ResetPasswordDto;
import com.bridgelabz.FundooApp.UserModell.UserInformation;
import com.bridgelabz.FundooApp.Utility.JMS;
import com.bridgelabz.FundooApp.Utility.TokenService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenservice;
	@Autowired
	private JMS jms;

	public String userRegistration(UserInformation userInformation) throws UserExceptions /* throws UserExceptions */ {
		String emailnew = userInformation.getemail();
		Optional<UserInformation> newuser = userRepository.findByEmail(emailnew);
		if (!newuser.isPresent()) {

			userRepository.save(userInformation);
			return "user added";

		} else {
			throw new UserExceptions("user is already present");
		}

	}

	public List<UserInformation> userinfo() {

		List<UserInformation> userlist = new ArrayList<UserInformation>();
		userRepository.findAll().forEach(userlist::add);

		return userlist;
	}

	public String login(LoginDTO logindto) throws UserExceptions {
		String email = logindto.getemail();

		Optional<UserInformation> newlogin = userRepository.findByEmail(email);
		System.out.println(newlogin);

		if (!newlogin.isPresent()) {
			throw new UserExceptions("user not exist");
		}
		if (!newlogin.get().getPassword().equals(logindto.getPassword())) {
			throw new UserExceptions("password mismatch");
		}
		String usertoken = tokenservice.createToken(newlogin.get().getemail());
		System.out.println(usertoken);
		//jms.sendMail(logindto.getemail(), usertoken);
		return usertoken;

	}

//	public String login(UserInformation user) throws UserExceptions {
//		String email = user.getemail();
//
//		Optional<UserInformation> newlogin = userRepository.findByEmail(email);
//		System.out.println(newlogin);
//
//		if (!newlogin.isPresent()) {
//			throw new UserExceptions("user not exist");
//		}
//		if (!newlogin.get().getPassword().equals(user.getPassword())) {
//			throw new UserExceptions("password mismatch");
//		}
//		String usertoken = tokenservice.createToken(newlogin.get().getemail());
//		System.out.println(usertoken);
//		//jms.sendMail(logindto.getemail(), usertoken);
//		return usertoken;
//
//	}

	public String forgetPassword(ForgetPasswordDTO forgetpassword) throws UserExceptions {
		Optional<UserInformation> userexist = userRepository.findByEmail(forgetpassword.getEmialid());
		if (userexist.isPresent()) {
			jms.sendMail(forgetpassword.getEmialid(), "http://localhost:8080/home/reset");
			return "click on the link";
		} else {
			throw new UserExceptions("user is not registered user");
		}

	}

	public String resetPasswords(ResetPasswordDto restepassworddto) throws UserExceptions {
		Optional<UserInformation> userexist = userRepository.findByEmail(restepassworddto.getEmail());
		System.out.println(userexist);
		if (userexist.isPresent()) {
			if (restepassworddto.getPassword().equals(restepassworddto.getConfirm_password())) {
				userexist.get().setPassword(restepassworddto.getPassword());
				userexist.get().setConfirm_password(restepassworddto.getConfirm_password());
				userRepository.save(userexist.get());
				jms.sendMail(restepassworddto.getEmail(), "password updated succesfully");
			} else {
				throw new UserExceptions("user is not present ");
			}
		}
		return "password updated";
	}

	public Response uploadProPic(String token, MultipartFile file) throws IOException {
		String emailid = tokenservice.getUserIdFromToken(token);
		Optional<UserInformation> user = userRepository.findByEmail(emailid);
		if (user.isPresent()) {
		System.out.println("hey");
		File uploadFile = new File(file.getOriginalFilename());
//		System.out.println(uploadFile);
		BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
		outStream.write(file.getBytes());
		outStream.close();
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "patilvarad2011", "api_key",
		"814469859839278", "api_secret", "xyd8OV451Efmc9DvwFKL0xxNfi4"));
		System.out.println(ObjectUtils.emptyMap());
		Map<?, ?> uploadProfile;
		//uploadProfile = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
		uploadProfile = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());


		//user.get().setImage(uploadProfile.get("secure_url").toString());
		user.get().setImage(uploadProfile.get("secure_url").toString());
		userRepository.save(user.get());
		// return "profile picture set successfully " +uploadProfile.get("secure_url").toString();
		return new Response( new Date(),"Profile picture set successfully", 200, uploadProfile.get("secure_url").toString());                                                                                                                                                                                                                                                                                                                                                                
		}
		return null;

		}

}
