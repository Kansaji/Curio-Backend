package com.curio.curioapp.curioappbackend.dto;



public class InquiryResponse {
	private String from;
	private String to;
	private long itemId;
	private String message;
	private String inquireTimeStamp;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getInquireTimeStamp() {
		return inquireTimeStamp;
	}
	public void setInquireTimeStamp(String inquireTimeStamp) {
		this.inquireTimeStamp = inquireTimeStamp;
	}
	
	
}
