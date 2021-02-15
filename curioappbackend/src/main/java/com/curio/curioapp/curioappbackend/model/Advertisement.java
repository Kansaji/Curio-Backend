package com.curio.curioapp.curioappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Advertisement {

	@Id
	@Column(name = "advertisementId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long advertisementId;
	@Column(name = "organization")
	private String organization;
	@Column(name = "subject")
	private String subject;
	@Column(name = "description")
	private String description;
	@Column(name = "contactDetails")
	private String contactDetails;
	@Column(name = "expiryDate")
	private String expiryDate;
	@Column(name = "postedDate")
    private String postedDate;
	
	
	public long getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(long advertisementId) {
		this.advertisementId = advertisementId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	
	
}
