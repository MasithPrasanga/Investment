package com.investment.email.json;

public class JsonEmailAttachment {

    public Boolean binary;
    public String contentType;
    public String name;
    public String content;
    
    public Boolean getBinary() {
        return binary;
    }
    public void setBinary(Boolean binary) {
        this.binary = binary;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    
}
