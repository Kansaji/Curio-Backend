package com.curio.curioapp.curioappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curio.curioapp.curioappbackend.model.Question;
import com.curio.curioapp.curioappbackend.model.User;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	List<Question> findByQuestionedUser(User user);
}
