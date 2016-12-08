package com.investment.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.investment.entity.RawProjectInfo;

public class RawProposalResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;

	private Date date;

	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}



