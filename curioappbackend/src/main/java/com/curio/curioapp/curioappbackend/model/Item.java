package com.curio.curioapp.curioappbackend.model;

import java.time.Instant;
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
import javax.persistence.OneToOne;
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
	@Column(name = "description")
	private String description;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] photo;
	@ManyToOne
	private User postedUser;
	@Column(name = "postedTimeStamp")
	private Instant postedTimeStamp;
	@ManyToOne
	private User boughtUser;	
	@Column(name = "soldFlag")
	private int soldFlag;
	@OneToOne(mappedBy = "item")
	private Toy toy;
	@OneToOne(mappedBy = "item")
	private Gear gear;
	@OneToMany(mappedBy = "inquiredItem")
	private List<Inquiry>inquiries = new ArrayList<>();
	@ManyToMany(mappedBy="inquiredItems",fetch=FetchType.LAZY)
	private List<User> inquiredUsers = new ArrayList<>();
	
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
	public Instant getPostedTimeStamp() {
		return postedTimeStamp;
	}
	public void setPostedTimeStamp(Instant postedTimeStamp) {
		this.postedTimeStamp = postedTimeStamp;
	}
	public User getBoughtUser() {
		return boughtUser;
	}
	public void setBoughtUser(User boughtUser) {
		this.boughtUser = boughtUser;
	}
	public int getSoldFlag() {
		return soldFlag;
	}
	public void setSoldFlag(int soldFlag) {
		this.soldFlag = soldFlag;
	}
	public Toy getToy() {
		return toy;
	}
	public void setToy(Toy toy) {
		this.toy = toy;
	}
	public Gear getGear() {
		return gear;
	}
	public void setGear(Gear gear) {
		this.gear = gear;
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
	
	
	
	

}
