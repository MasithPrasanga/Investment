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

	@Column(name = "share_price", nullable = false)
	private int sharePrice;
	
	@Column(name = "imageurl")
	private String imageUrl;
	
	@Column(name = "videourl")
	private String videoUrl;
	
	@Column(name="full_amount")
	private int fullAmmount;
	
	@Column(name="invested_amount")
	private int investedAmount;
	
	@Column(name="noofshares")
	private int noOfShares;
	
	@Column(name="min_ammount")
	private int mininumAmmount;
	
	@Column(name="can_invest")
	private String canInvest;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typeid")
	private Type type = null;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_type_id")
	private CustomerType customerType;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "raw_project_info_id",nullable = false)
	private RawProjectInfo rawProjectInfo = null;

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

	public RawProjectInfo getRawProjectInfo() {
		return rawProjectInfo;
	}

	public void setRawProjectInfo(RawProjectInfo rawProjectInfo) {
		this.rawProjectInfo = rawProjectInfo;
	}

	public int getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(int investedAmount) {
		this.investedAmount = investedAmount;
	}

	public String getCanInvest() {
		return canInvest;
	}

	public void setCanInvest(String canInvest) {
		this.canInvest = canInvest;
	}
	
	
}














