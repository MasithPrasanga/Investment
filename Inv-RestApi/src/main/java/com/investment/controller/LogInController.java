package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.CoreUserDto;
import com.investment.entity.CoreUser;
import com.investment.handler.LogInHandler;
import com.investment.service.CoreUserService;
import com.investment.service.UserRoleService;

@RestController
@RequestMapping("api/v1/user")
public class LogInController {

	@Autowired
	private CoreUserService coreUserService = null;

	@Autowired
	private UserRoleService userRoleService = null;

	@Autowired
	private LogInHandler logInHandler = null;

	// SignUp EndPoint
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadUrls(@RequestBody CoreUserDto coreUserDto) {
		try {
			
			if (coreUserDto.getUserEmail() == null || coreUserDto.getUserEmail().equals("") || coreUserDto.getPassword() == null || coreUserDto.getPassword().equals("")) {
				System.out.println("Inside the Validation");
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			} 
			
			CoreUser user = coreUserService.findByEmail(coreUserDto.getUserEmail());
			if (user != null) {
				System.out.println("Inside already reported");
				return new ResponseEntity<Void>(HttpStatus.ALREADY_REPORTED);
			}
			
			boolean status = logInHandler.createNewUser(coreUserDto);
			
			return new ResponseEntity<Void>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			System.out.println("Exception is : "+e);
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	

	@RequestMapping(value = "account/activate/{activationCode}", method = RequestMethod.GET, produces = {"application/json" })
	public String userActivation(@PathVariable String activationCode) {
	

		System.out.println("Inside the Activation Method");
		
	/*	MobileActivationResponse json = new MobileActivationResponse();
		CoreUser user = null;*/

		/*try {
			Date localDate = new Date();
			DateTimeZone tz = DateTimeZone.getDefault();
			Date utcDate = new Date(tz.convertLocalToUTC(localDate.getTime(), false));

			if (activationCode == null) {
				json.setMessage("Invalid activation code " + activationCode);
				json.setCode(E2E.FAILED);
			}

			LOGGER.info("Looking for user with activation code " + activationCode);
			user = userManager.findByActivationCode(activationCode);

			if (user == null) {
				json.setMessage("Account is not found for the activation code " + activationCode);
				json.setCode(E2E.FAILED);
			}

			LOGGER.info("Found the user with activation code " + user.getUserEmail());

			if (user.isActive()) {
				json.setMessage("Your allready activated !!!!");
				json.setCode(E2E.SUCUESS);
			} else {
				if (UserAccountType.PERSONAL.getType() == user.getAccountType()) {
					user.setActivationStatus(UserStatus.CONSUMER_ACTIVATION_CONFIRMED.getType());
					user.setActive(true);
					user.setActivatedOn(utcDate);
					user = userManager.addUser(user);

					// Preference initialization
					userSalesCategoryManager.initializePreferenceTypes(user);

					json.setUsername(user.getFirstName());
					json.setEmail(user.getUserEmail());
					json.setId(user.getId());
					json.setCode(E2E.SUCUESS);
					json.setMessage("Your Account activated !!!");

				}
			}

		} catch (Exception e) {
			json = new MobileActivationResponse();
			json.setMessage("Your Account activatiion failed Please contact admintrator ");
			json.setCode(E2E.SUCUESS);
			LOGGER.error("Account regestration failed ", e);
		}
*/
		/*model.addAttribute("ActivationResponse", json);*/
		return "activation";
	}
}



























