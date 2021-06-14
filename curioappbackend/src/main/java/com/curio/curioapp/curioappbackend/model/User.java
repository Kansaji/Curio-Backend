package com.curio.curioapp.curioappbackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table
public class User {
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@OneToMany(mappedBy = "postedUser")
	private List<Item> postedItems = new ArrayList<>();
	@OneToMany(mappedBy = "boughtUser")
	private List<Item> boughtItems = new ArrayList<>();
	@OneToMany(mappedBy = "questionedUser")
	private List<Question> questions = new ArrayList<>();
	@OneToMany(mappedBy = "sentBy")
	private List<Inquiry> sentInquiries = new ArrayList<>();
	@OneToMany(mappedBy = "receivedBy")
	private List<Inquiry> receivededInquiries = new ArrayList<>();
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="InquiredItems",
			joinColumns= {@JoinColumn(name="userid")},
			inverseJoinColumns= {@JoinColumn(name="itemid")}
			)
	private List<Item> inquiredItems = new ArrayList<>();
	@Column(name = "longitude")
	private double longitude;
	@Column(name = "latitude")
	private double latitude;
	@OneToMany(mappedBy="answerReplyUser")
	private List<AnswerReply>  answerReplies;
	
	@OneToMany(mappedBy="advertisementPostedUser")
	private List<Advertisement>  advertisements;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Item> getPostedItems() {
		return postedItems;
	}
	public void setPostedItems(List<Item> postedItems) {
		this.postedItems = postedItems;
	}
	public List<Item> getBoughtItems() {
		return boughtItems;
	}
	public void setBoughtItems(List<Item> boughtItems) {
		this.boughtItems = boughtItems;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<Inquiry> getSentInquiries() {
		return sentInquiries;
	}
	public void setSentInquiries(List<Inquiry> sentInquiries) {
		this.sentInquiries = sentInquiries;
	}
	public List<Inquiry> getReceivededInquiries() {
		return receivededInquiries;
	}
	public void setReceivededInquiries(List<Inquiry> receivededInquiries) {
		this.receivededInquiries = receivededInquiries;
	}
	public List<Item> getInquiredItems() {
		return inquiredItems;
	}
	public void setInquiredItems(List<Item> inquiredItems) {
		this.inquiredItems = inquiredItems;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public List<AnswerReply> getAnswerReplies() {
		return answerReplies;
	}
	public void setAnswerReplies(List<AnswerReply> answerReplies) {
		this.answerReplies = answerReplies;
	}
	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}
	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}
	
	
	
	
	

}