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

import com.investment.dto.request.ResetPasswordRequestDto;
import com.investment.dto.request.UserRequestDto;
import com.investment.dto.response.ResetPasswordResponseDto;
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
				userResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
				userResponse.setMessage(ApiConstants.INCORRECT_CREDENTIALS);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.BAD_REQUEST);
			}
			CoreUser user = coreUserService.findByEmail(userRequest.getUserEmail());
			if (user != null) {
				userResponse.setHttpStatus(HttpStatus.ALREADY_REPORTED);
				userResponse.setMessage(ApiConstants.ALREADY_REGISTERED);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.ALREADY_REPORTED);
			}
			boolean status = logInHandler.createNewUser(userRequest);
			if(status){
				userResponse.setHttpStatus(HttpStatus.CREATED);
				userResponse.setMessage(ApiConstants.SUCCESSFULLY_REGISTERED);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.CREATED);
			}
			else{
				userResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
				userResponse.setMessage(ApiConstants.EXCEPTION_FAILED);
				return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception e) {
			userResponse.setMessage(ApiConstants.INTERNAL_SERVER_ERROR);
			userResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<UserResponseDto>(userResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// SignIn EndPoint (R)
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<SignInResponseDto> SignIn(@RequestBody UserRequestDto userRequest) {
		SignInResponseDto signInResponse = new SignInResponseDto();
		try {
			if (userRequest.getUserEmail() == null || userRequest.getUserEmail().equals("")
					|| userRequest.getPassword() == null || userRequest.getPassword().equals("")) {
				signInResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
				signInResponse.setMessage(ApiConstants.INCORRECT_CREDENTIALS);
				return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.BAD_REQUEST);
			}
			CoreUser user = coreUserService.findByEmail(userRequest.getUserEmail());
			if (user == null) {
				signInResponse.setHttpStatus(HttpStatus.NOT_FOUND);
				signInResponse.setMessage(ApiConstants.USER_NOT_FOUND);
				return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.NOT_FOUND);
			}

			if (user.getActivationStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)) {
				signInResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
				signInResponse.setMessage(ApiConstants.UNREGISTERED_ACCOUNT);
				return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.UNAUTHORIZED);
			}

			if (userRequest.getUserEmail().equals(user.getUserEmail())) {
				StandardPasswordEncoder encoder = new StandardPasswordEncoder();
				boolean correctPsd = encoder.matches(userRequest.getPassword(), user.getPassword());
				if (correctPsd) {
					signInResponse.setUserEmail(user.getUserEmail());
					signInResponse.setHttpStatus(HttpStatus.ACCEPTED);
					signInResponse.setAccountType(user.getAccountType());
					signInResponse.setMessage(ApiConstants.SUCCESSFULLY_SIGNIN);
					return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.ACCEPTED);
				}
			}
			signInResponse.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
			signInResponse.setMessage(ApiConstants.INCORRECT_CREDENTIALS);
			return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.NOT_ACCEPTABLE);

		} catch (Exception e) {
			signInResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			signInResponse.setMessage(ApiConstants.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Reset Password EndPoint
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public ResponseEntity<ResetPasswordResponseDto> ResetPassword( @RequestBody ResetPasswordRequestDto resetPasswordRequest) {
		ResetPasswordResponseDto resetPasswordResponse = new ResetPasswordResponseDto();
		try {
			if (resetPasswordRequest.getEmail() == null 
					|| resetPasswordRequest.getEmail().equals("")
					|| resetPasswordRequest.getCurrentPassword() == null
					|| resetPasswordRequest.getCurrentPassword().equals("")
					|| resetPasswordRequest.getNewPassword() == null
					|| resetPasswordRequest.getNewPassword().equals("")) {

				resetPasswordResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
				resetPasswordResponse.setMessage(ApiConstants.INCORRECT_CREDENTIALS);
				return new ResponseEntity<ResetPasswordResponseDto>(resetPasswordResponse, HttpStatus.BAD_REQUEST);
			}
			CoreUser user = coreUserService.findByEmail(resetPasswordRequest.getEmail());
			if (user == null) {
				resetPasswordResponse.setHttpStatus(HttpStatus.NOT_FOUND);
				resetPasswordResponse.setMessage(ApiConstants.USER_NOT_FOUND);
				return new ResponseEntity<ResetPasswordResponseDto>(resetPasswordResponse, HttpStatus.NOT_FOUND);
			}
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			boolean correctPsd = encoder.matches(resetPasswordRequest.getCurrentPassword(), user.getPassword());
			if (correctPsd) {	
				StandardPasswordEncoder psdEncorder = new StandardPasswordEncoder();
				String en = psdEncorder.encode(resetPasswordRequest.getNewPassword());
				user.setPassword(en);	
				coreUserService.update(user);
				resetPasswordResponse.setMessage(ApiConstants.PASSWORD_SUCCESSFULLY_CHANGED);
				resetPasswordResponse.setHttpStatus(HttpStatus.OK);
			}
			return new ResponseEntity<ResetPasswordResponseDto>(resetPasswordResponse, HttpStatus.OK);

		} catch (Exception e) {
			resetPasswordResponse.setMessage(ApiConstants.INTERNAL_SERVER_ERROR);
			resetPasswordResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<ResetPasswordResponseDto>(resetPasswordResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Activating Account End Point (R)
	@RequestMapping(value = "account/activate/{activationCode}", method = RequestMethod.GET)
	public ResponseEntity<UserResponseDto> userActivation(@PathVariable String activationCode) {
		UserResponseDto actiavtionResponse = new UserResponseDto();
		CoreUser user = null;
		try {
			if (activationCode == null) {
				actiavtionResponse.setMessage(ApiConstants.INVALID_ACTIVATION_CODE);
			}
			user = coreUserService.findByActivationCode(activationCode);
			if (user == null) {
				actiavtionResponse.setMessage(ApiConstants.NO_ACCOUNT_TO_ACTIVATE);
			}
			if (user.getActivationStatus().equals(ApiConstants.ADMIN_APPROVED)) {
				actiavtionResponse.setMessage(ApiConstants.ALREADY_ACTIVATED_ACCOUNT);
			} else {
				user.setActivatedDate(new Date());
				user.setActivationStatus(ApiConstants.ADMIN_APPROVED);
				coreUserService.update(user);
			    actiavtionResponse.setMessage(ApiConstants.ACCOUNT_ACTIAVAED);
			}
		} catch (Exception e) {
			actiavtionResponse.setMessage(ApiConstants.ACTIVATION_FAILED);
		}

		return new ResponseEntity<UserResponseDto>(actiavtionResponse, HttpStatus.OK);
	}
}





































