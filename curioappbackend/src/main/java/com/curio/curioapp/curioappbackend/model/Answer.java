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
public class Answer {
	@Id
	@Column(name = "answerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long answerId;
	@Column(name = "answerContent")
	private String answerContent;
	@ManyToOne
	private Question question;
	@ManyToOne
	private User answeredUser;
	@Column(name = "answeredTimeStamp")
	private String answeredTimeStamp;
	
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
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public User getAnsweredUser() {
		return answeredUser;
	}
	public void setAnsweredUser(User answeredUser) {
		this.answeredUser = answeredUser;
	}
	public String getAnsweredTimeStamp() {
		return answeredTimeStamp;
	}
	public void setAnsweredTimeStamp(String answeredTimeStamp) {
		this.answeredTimeStamp = answeredTimeStamp;
	}
	
	
}

