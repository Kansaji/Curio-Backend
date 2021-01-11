package com.curio.curioapp.curioappbackend.dto;


public class ItemDto {

	private long itemId;
	private String itemName;
	private String type;
	private String description;
	private String soldFlag;
	private String photo;
	private String postedTimeStamp;
	private String postedUser;
	private double away;
	private int inquiredUsers;
	private int inquiries;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSoldFlag() {
		return soldFlag;
	}
	public void setSoldFlag(String soldFlag) {
		this.soldFlag = soldFlag;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPostedTimeStamp() {
		return postedTimeStamp;
	}
	public void setPostedTimeStamp(String postedTimeStamp) {
		this.postedTimeStamp = postedTimeStamp;
	}
	public String getPostedUser() {
		return postedUser;
	}
	public void setPostedUser(String postedUser) {
		this.postedUser = postedUser;
	}
	public double getAway() {
		return away;
	}
	public void setAway(double away) {
		this.away = away;
	}
	public int getInquiredUsers() {
		return inquiredUsers;
	}
	public void setInquiredUsers(int inquiredUsers) {
		this.inquiredUsers = inquiredUsers;
	}
	public int getInquiries() {
		return inquiries;
	}
	public void setInquiries(int inquiries) {
		this.inquiries = inquiries;
	}
	
	
	
	
	
}
