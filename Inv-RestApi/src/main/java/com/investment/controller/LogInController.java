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

import com.investment.dto.CoreUserDto;
import com.investment.dto.PasswordResetRequestDto;
import com.investment.dto.response.CoreUserResponseDto;
import com.investment.dto.response.PasswordResetResponseDto;
import com.investment.dto.response.SignInResponseDto;
import com.investment.entity.CoreUser;
import com.investment.handler.LogInHandler;
import com.investment.service.CoreUserService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/user")
public class LogInController {

	@Autowired
	private CoreUserService coreUserService = null;

	@Autowired
	private LogInHandler logInHandler = null;

	// SignUp EndPoint
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Void> SignUp(@RequestBody CoreUserDto coreUserDto) {
		try {
			if (coreUserDto.getUserEmail() == null || coreUserDto.getUserEmail().equals("")
					|| coreUserDto.getPassword() == null || coreUserDto.getPassword().equals("")) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			CoreUser user = coreUserService.findByEmail(coreUserDto.getUserEmail());
			if (user != null) {
				return new ResponseEntity<Void>(HttpStatus.ALREADY_REPORTED);
			}
			boolean status = logInHandler.createNewUser(coreUserDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// SignIn EndPoint
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<SignInResponseDto> SignIn(@RequestBody CoreUserDto coreUserDto) {
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
				}
			}
			return new ResponseEntity<SignInResponseDto>(signInResponse, HttpStatus.ACCEPTED);

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
	public ResponseEntity<CoreUserResponseDto> userActivation(@PathVariable String activationCode) {
		CoreUserResponseDto actiavtionResponse = new CoreUserResponseDto();
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
				boolean updateStatus = coreUserService.update(user);
				actiavtionResponse.setMessage(ApiConstants.ACCOUNT_ACTIAVAED);
			}
		} catch (Exception e) {
			actiavtionResponse.setMessage(ApiConstants.ACTIVATION_FAILED);
		}

		return new ResponseEntity<CoreUserResponseDto>(actiavtionResponse, HttpStatus.OK);
	}
}
