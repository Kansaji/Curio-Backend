package com.curio.curioapp.curioappbackend.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Answer {
	@Id
	@Column(name = "answerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long answerId;
	@Column(name = "answerTextContent", columnDefinition="TEXT")
	private String answerTextContent;
	@ManyToOne
	private Question question;
	@ManyToOne
	private User answeredUser;
	@Column(name = "answeredTimeStamp")
	private String answeredTimeStamp;
	@OneToMany(mappedBy = "answer")
	private List<AnswerReply> asnwerReplies = new ArrayList<>();
	
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	public String getAnswerContent() {
		return answerTextContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerTextContent = answerContent;
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
	public List<AnswerReply> getAsnwerReplies() {
		return asnwerReplies;
	}
	public void setAsnwerReplies(List<AnswerReply> asnwerReplies) {
		this.asnwerReplies = asnwerReplies;
	}
	
	
}

