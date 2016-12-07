package com.investment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.investment.entity.metadata.Category;
import com.investment.entity.metadata.Currency;
import com.investment.entity.metadata.CustomerType;
import com.investment.entity.metadata.Type;

@Entity
@Table(name = "processed_project_info")
public class ProcessedProjectInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "project_name", unique = true, nullable = false)
	private String projectName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeid")
	private Type type = null;

	@Column(name = "share_price", nullable = false)
	private int sharePrice;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private CoreUser coreUser = null;
	
	@Column(name = "imageurl")
	private String imageUrl;
	
	@Column(name = "videourl")
	private String videoUrl;
	
	@Column(name="full_amount")
	private int fullAmmount;
	
	@Column(name="noofshares")
	private int noOfShares;
	
	@Column(name="min_ammount")
	private int mininumAmmount;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_type_id")
	private CustomerType customerType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}

	public CoreUser getCoreUser() {
		return coreUser;
	}

	public void setCoreUser(CoreUser coreUser) {
		this.coreUser = coreUser;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getFullAmmount() {
		return fullAmmount;
	}

	public void setFullAmmount(int fullAmmount) {
		this.fullAmmount = fullAmmount;
	}

	public int getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}

	public int getMininumAmmount() {
		return mininumAmmount;
	}

	public void setMininumAmmount(int mininumAmmount) {
		this.mininumAmmount = mininumAmmount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	
	
}














