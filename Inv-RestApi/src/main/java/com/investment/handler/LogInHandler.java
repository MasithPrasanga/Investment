package com.investment.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

import com.investment.dto.request.UserRequestDto;
import com.investment.entity.CoreUser;
import com.investment.service.CoreUserService;
import com.investment.util.ApiConstants;
import com.investment.util.CommonUtil;

@Component
public class LogInHandler {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CoreUserService coreUserService = null;

	public boolean createNewUser(UserRequestDto userRequest) {

		Session session = null;
		Transaction transaction = null;
		boolean transactionStatus = false;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			CoreUser user = new CoreUser();
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			user.setUserEmail(userRequest.getUserEmail());

			// set the encripted password
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			String encriptedPassword = encoder.encode(userRequest.getPassword());
			user.setPassword(encriptedPassword);

			user.setMobileNumber(userRequest.getMobileNumber());
			user.setLandNumber(userRequest.getLandNumber());
			user.setBirthDate(userRequest.getBirthDate());
			user.setGender(userRequest.getGender());
			user.setAccountType(userRequest.getAccountType());
			user.setCreatedDate(userRequest.getCreatedDate());
			user.setActivationStatus(ApiConstants.ADMIN_NOT_APPROVED);

			// set activation link
			String activationCode = CommonUtil.generateUserActivationKey();
			user.setActivationCode(activationCode);
			
			int userid = (int) coreUserService.insert(user);
			
			System.out.println("Activation url : " + getLocalActivationCode() + "/" + user.getActivationCode());
			userRequest.setActivationCode(getLocalActivationCode() + "/" + user.getActivationCode());
			//System.out.println("Activation url : " + getActivationCode() + "/" + user.getActivationCode());
			//coreUserDto.setActivationCode(getActivationCode() + "/" + user.getActivationCode());
			// send the email
			if (userid != ApiConstants.PERSISTED_EXCEPTION) {
				transactionStatus = true;
			}
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return transactionStatus;
	}

	private String getLocalActivationCode() {
		return "http://" + "localhost" + ":" + "8090" + "/api/v1/user/account/activate/";
	}

	private String getActivationCode() {
		return "http://" + "54.169.191.223" + ":" + "8080" + "/api/v1/user/account/activate/";
	}

}
