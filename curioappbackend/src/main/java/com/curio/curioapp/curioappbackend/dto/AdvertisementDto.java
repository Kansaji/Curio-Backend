package com.curio.curioapp.curioappbackend.dto;



public class AdvertisementDto {

	private long advertisementId;
	private String organization;
	private String description;
	private String contactDetails;
	private String subject;
	private String expiryDate;
    private String postedDate;
    private String postedUser;
	
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
	public String getPostedUser() {
		return postedUser;
	}
	public void setPostedUser(String postedUser) {
		this.postedUser = postedUser;
	}
	
	
}
