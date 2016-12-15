package com.investment.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.request.PasswordResetRequestDto;
import com.investment.dto.request.UserRequestDto;
import com.investment.dto.response.PasswordResetResponseDto;
import com.investment.dto.response.SignInResponseDto;
import com.investment.dto.response.UserResponseDto;
import com.investment.entity.CoreUser;
import com.investment.handler.LogInHandler;
import com.investment.service.CoreUserService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/user")
public class LogInController {

	@Autowired
	private LogInHandler logInHandler = null;
	
	@Autowired
	private CoreUserService coreUserService = null;

	// SignUp (User Registration End Point) (R)
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<UserResponseDto> SignUp(@RequestBody UserRequestDto userRequest) {
		UserResponseDto userResponse = new UserResponseDto();
		try {
			if (userRequest.getUserEmail() == null || userRequest.getUserEmail().equals("")
					|| userRequest.getPassword() == null || userRequest.getPassword().equals("")) {
				userResponse.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.BAD_REQUEST);
			}
			CoreUser user = coreUserService.findByEmail(userRequest.getUserEmail());
			if (user != null) {
				userResponse.setStatus(HttpStatus.ALREADY_REPORTED);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.ALREADY_REPORTED);
			}
			boolean status = logInHandler.createNewUser(userRequest);
			if(status){
				userResponse.setStatus(HttpStatus.CREATED);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.CREATED);
			}
			else{
				userResponse.setStatus(HttpStatus.EXPECTATION_FAILED);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception e) {
			userResponse.setStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.EXPECTATION_FAILED);
		}
	}

	// SignIn EndPoint
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<SignInResponseDto> SignIn(@RequestBody UserRequestDto coreUserDto) {
		SignInResponseDto signInResponse = new SignInResponseDto();
		try {
			if (coreUserDto.getUserEmail() == null || coreUserDto.getUserEmail().equals("")
					|| coreUserDto.getPassword() == null || coreUserDto.getPassword().equals("")) {
				signInResponse.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.BAD_REQUEST);
			}
			CoreUser user = coreUserService.findByEmail(coreUserDto.getUserEmail());
			if (user == null) {
				signInResponse.setStatus(HttpStatus.NOT_FOUND);
				return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.NOT_FOUND);
			}

			if (user.getActivationStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)) {
				signInResponse.setStatus(HttpStatus.UNAUTHORIZED);
				return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.UNAUTHORIZED);
			}

			if (coreUserDto.getUserEmail().equals(user.getUserEmail())) {
				StandardPasswordEncoder encoder = new StandardPasswordEncoder();
				boolean correctPsd = encoder.matches(coreUserDto.getPassword(), user.getPassword());
				if (correctPsd) {
					signInResponse.setUserEmail(user.getUserEmail());
					signInResponse.setStatus(HttpStatus.ACCEPTED);
					signInResponse.setAccountType(user.getAccountType());
					return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.ACCEPTED);
				}
			}
			signInResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
			return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.NOT_ACCEPTABLE);

		} catch (Exception e) {
			signInResponse.setStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Reset Password EndPoint
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public ResponseEntity<PasswordResetResponseDto> ResetPassword( @RequestBody PasswordResetRequestDto resetPasswordRequest) {
		PasswordResetResponseDto passwordResetResponse = new PasswordResetResponseDto();
		try {
			if (resetPasswordRequest.getEmail() == null 
					|| resetPasswordRequest.getEmail().equals("")
					|| resetPasswordRequest.getCurrentPassword() == null
					|| resetPasswordRequest.getCurrentPassword().equals("")
					|| resetPasswordRequest.getNewPassword() == null
					|| resetPasswordRequest.getNewPassword().equals("")) {

				passwordResetResponse.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<PasswordResetResponseDto>(passwordResetResponse, HttpStatus.BAD_REQUEST);
			}
			CoreUser user = coreUserService.findByEmail(resetPasswordRequest.getEmail());
			if (user == null) {
				passwordResetResponse.setStatus(HttpStatus.NOT_FOUND);
				return new ResponseEntity<PasswordResetResponseDto>(passwordResetResponse, HttpStatus.NOT_FOUND);
			}
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			boolean correctPsd = encoder.matches(resetPasswordRequest.getCurrentPassword(), user.getPassword());
			if (correctPsd) {	
				StandardPasswordEncoder psdEncorder = new StandardPasswordEncoder();
				String en = psdEncorder.encode(resetPasswordRequest.getNewPassword());
				user.setPassword(en);	
				coreUserService.update(user);
				passwordResetResponse.setStatus(HttpStatus.OK);
			}
			return new ResponseEntity<PasswordResetResponseDto>(passwordResetResponse, HttpStatus.OK);

		} catch (Exception e) {
			passwordResetResponse.setStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<PasswordResetResponseDto>(passwordResetResponse, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "account/activate/{activationCode}", method = RequestMethod.GET)
	public ResponseEntity<UserResponseDto> userActivation(@PathVariable String activationCode) {
		UserResponseDto actiavtionResponse = new UserResponseDto();
		CoreUser user = null;
		try {
			if (activationCode == null) {
				//actiavtionResponse.setStatus(ApiConstants.INVALID_ACTIVATION_CODE);
			}
			user = coreUserService.findByActivationCode(activationCode);
			if (user == null) {
				//actiavtionResponse.setStatus(ApiConstants.NO_ACCOUNT_TO_ACTIVATE);
			}
			if (user.getActivationStatus().equals(ApiConstants.ADMIN_APPROVED)) {
				//actiavtionResponse.setStatus(ApiConstants.ALREADY_ACTIVATED_ACCOUNT);
			} else {
				user.setActivatedDate(new Date());
				user.setActivationStatus(ApiConstants.ADMIN_APPROVED);
				boolean updateStatus = coreUserService.update(user);
				//actiavtionResponse.setStatus(ApiConstants.ACCOUNT_ACTIAVAED);
			}
		} catch (Exception e) {
			//actiavtionResponse.setStatus(ApiConstants.ACTIVATION_FAILED);
		}

		return new ResponseEntity<UserResponseDto>(actiavtionResponse, HttpStatus.OK);
	}
}



































