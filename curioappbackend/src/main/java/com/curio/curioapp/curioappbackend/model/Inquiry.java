package com.curio.curioapp.curioappbackend.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Inquiry {
	@Id
	@Column(name = "inquiryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inquiryId;
	@Column(name = "messageContent")
	private String messageContent;
	@Column(name = "inquiredTimeStamp")
	private String inquiredTimeStamp;
	@ManyToOne
	private Item inquiredItem;
	@ManyToOne
	private User sentBy;
	@ManyToOne
	private User receivedBy;
	
	public long getInquiryId() {
		return inquiryId;
	}
	public void setInquiryId(long inquiryId) {
		this.inquiryId = inquiryId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getInquiredTimeStamp() {
		return inquiredTimeStamp;
	}
	public void setInquiredTimeStamp(String inquiredTimeStamp) {
		this.inquiredTimeStamp = inquiredTimeStamp;
	}
	public Item getInquiredItem() {
		return inquiredItem;
	}
	public void setInquiredItem(Item inquiredItem) {
		this.inquiredItem = inquiredItem;
	}
	public User getSentBy() {
		return sentBy;
	}
	public void setSentBy(User sentBy) {
		this.sentBy = sentBy;
	}
	public User getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(User receivedBy) {
		this.receivedBy = receivedBy;
	}
	
	

	
}
