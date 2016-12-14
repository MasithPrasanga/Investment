package com.investment.emai.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.investment.email.json.JsonEmailMessage;
import com.investment.email.json.JsonEmailRecipient;
import com.investment.entity.CoreUser;


@Service("MailServicePlayImpl")
public class MailServicePlayImpl implements MailServicePlay {
	
	private String playUrl = "http://52.77.160.52:9000";

	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

	/*public ORLogger getLOGGER() {
		return LOGGER;
	}

	public void setLOGGER(ORLogger lOGGER) {
		LOGGER = lOGGER;
	}

	ORLogger LOGGER = new ORLogger(this.getClass());*/

	public boolean sendUserMandrill(CoreUser coreUser, String activationLink, String templateName,String router) {
		JsonEmailMessage jsonmail = new JsonEmailMessage();
		jsonmail.setFromName("Toopsie");
		jsonmail.setTemplateName(templateName);
		jsonmail.setActivationLink(activationLink);
		jsonmail.setAudienceIdentifier("C");
		Map<String, String> templateFields = new HashMap<String, String>();
		templateFields.put("company", "Toopsie");
		jsonmail.setTemplateFields(templateFields);
		List<JsonEmailRecipient> listRec = new ArrayList<JsonEmailRecipient>();
		JsonEmailRecipient jsonRec = new JsonEmailRecipient();
		jsonRec.setToemail(coreUser.getUserEmail());
		jsonRec.setToname(coreUser.getFirstName());
		listRec.add(jsonRec);
		jsonmail.setRecipients(listRec);
		//LOGGER.debug("sendUserMandrill --- > "+getPlayUrl()+router + "  "+ new Gson().toJson(jsonmail));
		//postData("http://localhost:9000/"+router, new Gson().toJson(jsonmail));
		System.out.println("Url is : "+"http://52.77.160.52:9000/"+router);
		postData("http://52.77.160.52:9000/"+router, new Gson().toJson(jsonmail));
		//postData(getPlayUrl()+router, new Gson().toJson(jsonmail));
		return true;
	}

/*	public boolean sendMerchantMandrill(Company company, String templateName,String router) {
		
		CoreUser coreuser=company.getUsers().get(0);
		
		JsonEmailMessage jsonmail = new JsonEmailMessage();
		jsonmail.setFromName("Toopsie");
		jsonmail.setTemplateName(templateName);
		jsonmail.setActivationLink("");
		jsonmail.setAudienceIdentifier("B");
		Map<String, String> templateFields = new HashMap<String, String>();
		templateFields.put("company", "Toopsie");
		jsonmail.setTemplateFields(templateFields);
		List<JsonEmailRecipient> listRec = new ArrayList<>();
		JsonEmailRecipient jsonRec = new JsonEmailRecipient();
		jsonRec.setToemail(company.getEmail());
		jsonRec.setToname(company.getName() );
		listRec.add(jsonRec);
		jsonmail.setRecipients(listRec);
		LOGGER.debug("sendMerchantMandrill --- > "+getPlayUrl()+router + "  "+ new Gson().toJson(jsonmail));
		postData(getPlayUrl()+router, new Gson().toJson(jsonmail));
		return true;
	}
*/
	public boolean sendAdminMandrill(CoreUser coreUser, String templateName,String router) {
		JsonEmailMessage jsonmail = new JsonEmailMessage();
		jsonmail.setFromName("Toopsie");
		jsonmail.setTemplateName(templateName);
		jsonmail.setActivationLink("");
		jsonmail.setAudienceIdentifier("C");
		Map<String, String> templateFields = new HashMap<String, String>();
		templateFields.put("company", "Toopsie");
		jsonmail.setTemplateFields(templateFields);
		List<JsonEmailRecipient> listRec = new ArrayList<JsonEmailRecipient>();
		JsonEmailRecipient jsonRec = new JsonEmailRecipient();
		jsonRec.setToemail(coreUser.getUserEmail());
		jsonRec.setToname(coreUser.getFirstName());
		listRec.add(jsonRec);
		jsonmail.setRecipients(listRec);
		//LOGGER.debug("sendAdminMandrill --- > "+getPlayUrl()+router + "  "+ new Gson().toJson(jsonmail));
		postData("http://localhost:9000/"+router, new Gson().toJson(jsonmail));
		//postData(getPlayUrl()+router+ "notifications", new Gson().toJson(jsonmail));
		return true;
	}

	public String postData(final String url, final String jsonString) {

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
		HttpClient httpclient = new DefaultHttpClient(httpParams);
		HttpPost httppost = new HttpPost(url);
		StringBuilder builder = new StringBuilder();

		try {
			httppost.setEntity(new StringEntity(jsonString, "UTF8"));
			httppost.setHeader("Content-type", "application/json");

			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			System.err.println(line);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		}
		return jsonString;

	}

	public static void main(String ar[]) throws Exception {
		MailServicePlayImpl mailSr = new MailServicePlayImpl();
		CoreUser core = new CoreUser();
		core.setUserEmail("cdharshans@gmail.com");
		core.setFirstName("Dharshan");
		// mailSr.sendUserMandrill(core, "http://localhost:8080/lbs-subscriber-portal/accounts/activate/fe7ed0be-32f3-4c86-92e9-411d43cad30e", "toopsie_activation","activate");
		//mailSr.sendMerchantMandrill(core, "", "save_promotion");
		// mailSr.sendRegistrationMail(core, "", "toopsie_activation");
		 mailSr.sendAdminMandrill(core,"toopsie_approved","notifications");
	}



}
