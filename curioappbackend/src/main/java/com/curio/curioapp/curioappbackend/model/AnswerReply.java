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
public class AnswerReply {
	@Id
	@Column(name = "answerReplyId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long answerReplyId;
	@Column(name = "answerReplyContent" ,columnDefinition="TEXT")
	private String answerReplyContent;
	@Column(name = "answerReplyTimeStamp")
	private String answerReplyTimeStamp;
	@ManyToOne
	private Answer answer;
	@ManyToOne
	private User answerReplyUser;
	
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
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public User getAnswerReplyUser() {
		return answerReplyUser;
	}
	public void setAnswerReplyUser(User answerReplyUser) {
		this.answerReplyUser = answerReplyUser;
	}
	
	

}
