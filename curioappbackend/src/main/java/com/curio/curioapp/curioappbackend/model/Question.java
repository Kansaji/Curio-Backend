package com.curio.curioapp.curioappbackend.model;

import java.time.Instant;
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
public class Question {
	@Id
	@Column(name = "questionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionId;
	@Column(name = "questionContent")
	private String questionContent;
	@ManyToOne
	private User questionedUser;
	@OneToMany(mappedBy = "question")
	private List<Answer>answers = new ArrayList<>();
	@Column(name = "questionedTimeStamp")
	private Instant questionedTimeStamp;
	
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
	public User getQuestionedUser() {
		return questionedUser;
	}
	public void setQuestionedUser(User questionedUser) {
		this.questionedUser = questionedUser;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public Instant getQuestionedTimeStamp() {
		return questionedTimeStamp;
	}
	public void setQuestionedTimeStamp(Instant questionedTimeStamp) {
		this.questionedTimeStamp = questionedTimeStamp;
	}
	
	
}
