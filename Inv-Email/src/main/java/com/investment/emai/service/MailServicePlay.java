package com.investment.emai.service;

import com.investment.entity.CoreUser;

public interface MailServicePlay {
	
	public boolean sendUserMandrill(CoreUser coreUser, String activationLink, String templateName,String router) throws Exception;
/*	public boolean sendMerchantMandrill(Company company, String templateName,String router) throws Exception;*/
	public boolean sendAdminMandrill(CoreUser coreUser, String templateName,String router) throws Exception;
	public String postData(final String url, final String jsonString) throws Exception;
}
