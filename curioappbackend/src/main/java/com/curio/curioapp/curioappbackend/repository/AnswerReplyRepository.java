package com.curio.curioapp.curioappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curio.curioapp.curioappbackend.model.Answer;
import com.curio.curioapp.curioappbackend.model.AnswerReply;

public interface AnswerReplyRepository extends JpaRepository<AnswerReply , Long> {

	List<AnswerReply> findByAnswer(Answer answer);
	
}
