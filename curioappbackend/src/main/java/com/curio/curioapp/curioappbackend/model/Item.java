package com.curio.curioapp.curioappbackend.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Item {
	@Id
	@Column(name = "itemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;
	@Column(name = "itemName")
	private String itemName;
	@Column(name = "type")
	private String type;
	@Lob
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="photo" , columnDefinition="MEDIUMBLOB")
	private byte[] photo;
	@ManyToOne
	private User postedUser;
	@Column(name = "postedTimeStamp")
	private String postedTimeStamp;
	@ManyToOne
	private User boughtUser;	
	@Column(name = "soldFlag")
	private String soldFlag;

	
	
	
	@OneToMany(mappedBy = "inquiredItem")
	private List<Inquiry>inquiries = new ArrayList<>();
	@ManyToMany(mappedBy="inquiredItems",fetch=FetchType.LAZY)
	private List<User> inquiredUsers = new ArrayList<>();
	
	@Column(name = "sale")
	private String sale;
	@Column(name = "donation")
	private String donation;
	@Column(name = "exchange")
	private String exchange;
	@Column(name = "renting")
	private String renting;
	
	@Column(name = "quality")
	private String quality;
	
	
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
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public User getPostedUser() {
		return postedUser;
	}
	public void setPostedUser(User postedUser) {
		this.postedUser = postedUser;
	}
	public String getPostedTimeStamp() {
		return postedTimeStamp;
	}
	public void setPostedTimeStamp(String postedTimeStamp) {
		this.postedTimeStamp = postedTimeStamp;
	}
	public User getBoughtUser() {
		return boughtUser;
	}
	public void setBoughtUser(User boughtUser) {
		this.boughtUser = boughtUser;
	}
	public String getSoldFlag() {
		return soldFlag;
	}
	public void setSoldFlag(String soldFlag) {
		this.soldFlag = soldFlag;
	}
	
	
	public List<Inquiry> getInquiries() {
		return inquiries;
	}
	public void setInquiries(List<Inquiry> inquiries) {
		this.inquiries = inquiries;
	}
	public List<User> getInquiredUsers() {
		return inquiredUsers;
	}
	public void setInquiredUsers(List<User> inquiredUsers) {
		this.inquiredUsers = inquiredUsers;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getDonation() {
		return donation;
	}
	public void setDonation(String donation) {
		this.donation = donation;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getRenting() {
		return renting;
	}
	public void setRenting(String renting) {
		this.renting = renting;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	
	
	

}
