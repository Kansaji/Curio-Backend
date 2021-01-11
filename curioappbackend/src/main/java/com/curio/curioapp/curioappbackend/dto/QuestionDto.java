package com.curio.curioapp.curioappbackend.dto;



public class QuestionDto {
	
	private long questionId;
	private String subject;
	private String questionContent;
	private String questionedTimeStamp;
	private String askedUsername;
	
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getQuestionedTimeStamp() {
		return questionedTimeStamp;
	}
	public void setQuestionedTimeStamp(String questionedTimeStamp) {
		this.questionedTimeStamp = questionedTimeStamp;
	}
	public String getAskedUsername() {
		return askedUsername;
	}
	public void setAskedUsername(String askedUsername) {
		this.askedUsername = askedUsername;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	

}
