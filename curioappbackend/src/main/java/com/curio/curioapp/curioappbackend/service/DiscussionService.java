package com.curio.curioapp.curioappbackend.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.AnswerDto;

import com.curio.curioapp.curioappbackend.dto.QuestionDto;
import com.curio.curioapp.curioappbackend.model.Answer;

import com.curio.curioapp.curioappbackend.model.Question;
import com.curio.curioapp.curioappbackend.repository.AnswerRepository;
import com.curio.curioapp.curioappbackend.repository.QuestionRepository;
import com.curio.curioapp.curioappbackend.repository.UserRepository;

@Service
public class DiscussionService {
	@Autowired
	private AuthService authservice;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	public boolean postQuestion(QuestionDto questionDto) {
		boolean posted=false;
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		if(user!=null) {
			Question question = new Question();
			question.setQuestionedUser(user);
			question.setQuestionContent(questionDto.getQuestionContent());
			question.setSubject(questionDto.getSubject());
			question.setQuestionedTimeStamp(questionDto.getQuestionedTimeStamp());
			questionRepository.save(question);
			posted=true;
		}
		return posted;
	}
	
	public List<QuestionDto> showAllQuestions(){
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		List<Question> sendingQuestions=new ArrayList<>();
		if(user!=null) {
			List<Question>questions=questionRepository.findAll();
			for(Question q: questions) {
				if(!q.getQuestionedUser().equals(user)) {
					sendingQuestions.add(q);
				}
				
			}
			
		}
		return sendingQuestions.stream().map(this::mapFromQuestionToDto).collect(Collectors.toList());
	}
	
	public List<QuestionDto> showMyQuestions(){
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		List<Question> questions=null;
		if(user!=null) {
			questions=questionRepository.findByQuestionedUser(user);
			
		}
		return questions.stream().map(this::mapFromQuestionToDto).collect(Collectors.toList());
	}
	
	public boolean postAnswer(AnswerDto answerDto) {
		boolean posted=false;
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		if (user!=null) {
			Answer answer = new Answer();
			Question question = questionRepository.findById(answerDto.getQuestionId()).get();
			answer.setQuestion(question);
			answer.setAnsweredUser(user);
			answer.setAnswerContent(answerDto.getAnswerContent());
			answer.setAnsweredTimeStamp(answerDto.getAnsweredTimeStamp());
			answerRepository.save(answer);
			posted=true;
		}
		return posted;
	}
	
	public List<AnswerDto> showAnswers(long questionId){
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		List<Answer> answers=new ArrayList<Answer>();
	
		if(user!=null) {
			Optional<Question> questionFound=questionRepository.findById(questionId);
			if(questionFound!=null) {
				
				Question question=questionFound.get();
				
			
					Answer dummy=new Answer();
					dummy.setQuestion(question);
					dummy.setAnswerContent("");
					dummy.setAnsweredTimeStamp("");
					dummy.setAnsweredUser(null);
					dummy.setAnswerId(0);
				
					
			
				answers=answerRepository.findByQuestion(question);
				answers.add(0,dummy);
				System.out.println(answers.get(0).getQuestion().getQuestionContent());
			}
		}
		return answers.stream().map(this::mapFromAnswerToDto).collect(Collectors.toList());
	}
	
	private QuestionDto mapFromQuestionToDto(Question question) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setQuestionId(question.getQuestionId());
		questionDto.setSubject(question.getSubject());
		questionDto.setQuestionContent(question.getQuestionContent());
		questionDto.setQuestionedTimeStamp(question.getQuestionedTimeStamp());
		questionDto.setAskedUsername(question.getQuestionedUser().getUsername());
		System.out.println(questionDto.getSubject());
		return questionDto;
	}
	
	private AnswerDto mapFromAnswerToDto(Answer answer) {
		AnswerDto answerDto = new AnswerDto();
		answerDto.setAnswerId(answer.getAnswerId());
		
		answerDto.setAnswerContent(answer.getAnswerContent());
		answerDto.setAnsweredTimeStamp(answer.getAnsweredTimeStamp());
		if(answer.getAnsweredUser()!=null) {
			answerDto.setAnsweredUsername(answer.getAnsweredUser().getUsername());
		}
		answerDto.setSubject(answer.getQuestion().getSubject());
		System.out.println("printing subject");
		System.out.println(answerDto.getSubject());
		System.out.println(answerDto.getQuestionContent());
		answerDto.setQuestionContent(answer.getQuestion().getQuestionContent());
		return answerDto;
	}
	
	private com.curio.curioapp.curioappbackend.model.User getCurrentlyLoggedInUser() {
		com.curio.curioapp.curioappbackend.model.User user=null;
		User username = authservice.getCurrentUser().orElseThrow(()->
		new IllegalArgumentException("No user logged in"));
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			user= optionalUser.get();
		}
		return user;
	}
}
