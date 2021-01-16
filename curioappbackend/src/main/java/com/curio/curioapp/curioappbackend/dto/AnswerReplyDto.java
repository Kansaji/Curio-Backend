package com.curio.curioapp.curioappbackend.dto;


public class AnswerReplyDto {
	
	private long answerReplyId;
	private String answerReplyContent;
	private String answerReplyTimeStamp;
	private long answerId;
	private String answerReplyUsername;
	public long getAnswerReplyId() {
		return answerReplyId;
	}
	public void setAnswerReplyId(long answerReplyId) {
		this.answerReplyId = answerReplyId;
	}
	public String getAnswerReplyContent() {
		return answerReplyContent;
	}
	public void setAnswerReplyContent(String answerReplyContent) {
		this.answerReplyContent = answerReplyContent;
	}
	public String getAnswerReplyTimeStamp() {
		return answerReplyTimeStamp;
	}
	public void setAnswerReplyTimeStamp(String answerReplyTimeStamp) {
		this.answerReplyTimeStamp = answerReplyTimeStamp;
	}
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	public String getAnswerReplyUsername() {
		return answerReplyUsername;
	}
	public void setAnswerReplyUsername(String answerReplyUsername) {
		this.answerReplyUsername = answerReplyUsername;
	}
	
	

}
