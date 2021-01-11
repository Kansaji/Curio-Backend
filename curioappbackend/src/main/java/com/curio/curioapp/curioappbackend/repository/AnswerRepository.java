package com.curio.curioapp.curioappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curio.curioapp.curioappbackend.model.Answer;
import com.curio.curioapp.curioappbackend.model.Question;

public interface AnswerRepository extends JpaRepository<Answer,Long> {

	List<Answer> findByQuestion(Question question);
}
