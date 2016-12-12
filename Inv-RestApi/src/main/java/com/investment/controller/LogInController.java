package com.investment.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.CoreUserDto;
import com.investment.dto.response.CoreUserResponseDto;
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
	public ResponseEntity<Void> uploadUrls(@RequestBody CoreUserDto coreUserDto) {
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








