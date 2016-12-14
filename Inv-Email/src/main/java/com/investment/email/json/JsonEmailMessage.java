package com.investment.email.json;

import java.util.List;
import java.util.Map;

public class JsonEmailMessage {

    private Integer appId;
    private String appName;
    private String audienceIdentifier;

    private Integer companyId;
    private String companyame;
    private Integer userId;
    private String userName;

    private String messageCategory;
    private String messageHeader;

    private String fromEmail;
    private String fromName;

    private String templateName;
    private String activationCode;
    private String activationLink;
    private String licenseEmail;
    private String licenseKey;

    private Map<String, String> templateFields;

    private List<JsonEmailAttachment> attachments;

    private List<JsonEmailRecipient> recipients;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAudienceIdentifier() {
        return audienceIdentifier;
    }

    public void setAudienceIdentifier(String audienceIdentifier) {
        this.audienceIdentifier = audienceIdentifier;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyame() {
        return companyame;
    }

    public void setCompanyame(String companyame) {
        this.companyame = companyame;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, String> getTemplateFields() {
        return templateFields;
    }

    public void setTemplateFields(Map<String, String> templateFields) {
        this.templateFields = templateFields;
    }

    public List<JsonEmailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<JsonEmailAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<JsonEmailRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<JsonEmailRecipient> recipients) {
        this.recipients = recipients;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getActivationLink() {
        return activationLink;
    }

    public void setActivationLink(String activationLink) {
        this.activationLink = activationLink;
    }

    public String getLicenseEmail() {
        return licenseEmail;
    }

    public void setLicenseEmail(String licenseEmail) {
        this.licenseEmail = licenseEmail;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
