package com.curio.curioapp.curioappbackend.dto;

public class AnswerDto {
	
	
	private long answerId;
	private String answerContent;
	private String answeredTimeStamp;
	private String answeredUsername;
	private long questionId;
	private String subject;
	private String questionContent;
	
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnsweredTimeStamp() {
		return answeredTimeStamp;
	}
	public void setAnsweredTimeStamp(String answeredTimeStamp) {
		this.answeredTimeStamp = answeredTimeStamp;
	}
	public String getAnsweredUsername() {
		return answeredUsername;
	}
	public void setAnsweredUsername(String answeredUsername) {
		this.answeredUsername = answeredUsername;
	}
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	

}
