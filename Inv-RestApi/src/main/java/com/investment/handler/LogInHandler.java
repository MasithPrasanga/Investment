package com.investment.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.CoreUserDto;
import com.investment.entity.CoreUser;
import com.investment.entity.UserRole;
import com.investment.service.CoreUserService;
import com.investment.service.UserRoleService;
import com.investment.util.ApiConstants;

@Component
public class LogInHandler {

	@Autowired
	private CoreUserService coreUserService = null;

	@Autowired
	private UserRoleService userRoleService = null;

	@Autowired
	private SessionFactory sessionFactory;

	public boolean createNewUser(CoreUserDto coreUserDto) {

		Session session = null;
		Transaction transaction = null;
		boolean transactionStatus = false;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			CoreUser user = new CoreUser();
			user.setFirstName(coreUserDto.getFirstName());
			user.setLastName(coreUserDto.getLastName());
			user.setUserEmail(coreUserDto.getUserEmail());
			user.setPassword(coreUserDto.getPassword()); // encrypt the password and save it
			user.setMobileNumber(coreUserDto.getMobileNumber());
			user.setLandNumber(coreUserDto.getLandNumber());
			user.setBirthDate(coreUserDto.getBirthDate());
			user.setGender(coreUserDto.getGender());
			user.setAccountType(coreUserDto.getAccountType());
			user.setCreatedDate(coreUserDto.getCreatedDate());
			user.setActivationStatus(coreUserDto.getActivationStatus());
			
			
			UserRole userRole = new UserRole();
			userRole.setAccessType(ApiConstants.ADMIN_ACCESS);
			userRole.setCoreUser(user);
			
			int userid = (int) coreUserService.insert(user);
			
			// send the email to the Admin that new user as registered and himself also

			if (userid != ApiConstants.PERSISTED_EXCEPTION){
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
}












