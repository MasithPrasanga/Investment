package com.investment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "investment")
public class Investment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "invested_date", nullable = false)
	private Date investedDate;
	
	@Column(name = "invested_amount", nullable = false)
	private Integer investedAmouont;
	
	@Column(name = "no_of_shares", nullable = false)
	private Integer noOfShares;
	
	@Column(name = "precentage_of_full_amount", nullable = false)
	private double presentageOfFullAmount;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "investor_id",nullable = false)
	private CoreUser investor;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "entrepreneur_id",nullable = false)
	private CoreUser entrepreneur;
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	@JoinColumn(name = "processed_project_info_id",nullable = false)
	private ProcessedProjectInfo processedProject = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInvestedDate() {
		return investedDate;
	}

	public void setInvestedDate(Date investedDate) {
		this.investedDate = investedDate;
	}

	public Integer getInvestedAmouont() {
		return investedAmouont;
	}

	public void setInvestedAmouont(Integer investedAmouont) {
		this.investedAmouont = investedAmouont;
	}

	public Integer getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(Integer noOfShares) {
		this.noOfShares = noOfShares;
	}

	public double getPresentageOfFullAmount() {
		return presentageOfFullAmount;
	}

	public void setPresentageOfFullAmount(double presentageOfFullAmount) {
		this.presentageOfFullAmount = presentageOfFullAmount;
	}
	
	public CoreUser getInvestor() {
		return investor;
	}

	public void setInvestor(CoreUser investor) {
		this.investor = investor;
	}

	public CoreUser getEntrepreneur() {
		return entrepreneur;
	}

	public void setEntrepreneur(CoreUser entrepreneur) {
		this.entrepreneur = entrepreneur;
	}

	public ProcessedProjectInfo getProcessedProject() {
		return processedProject;
	}

	public void setProcessedProject(ProcessedProjectInfo processedProject) {
		this.processedProject = processedProject;
	}	

}

























