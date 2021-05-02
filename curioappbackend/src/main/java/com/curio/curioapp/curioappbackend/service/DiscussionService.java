package com.curio.curioapp.curioappbackend.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.AnswerDto;
import com.curio.curioapp.curioappbackend.dto.AnswerReplyDto;
import com.curio.curioapp.curioappbackend.dto.QuestionDto;
import com.curio.curioapp.curioappbackend.model.Answer;
import com.curio.curioapp.curioappbackend.model.AnswerReply;
import com.curio.curioapp.curioappbackend.model.Question;
import com.curio.curioapp.curioappbackend.repository.AnswerReplyRepository;
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
	@Autowired
	private AnswerReplyRepository answerReplyRepository;
	
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
	
	public List<QuestionDto> searchQuestion(String search){
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		List<Question> sendingQuestions=new ArrayList<>();
		if(user!=null) {
			List<Question>questions=questionRepository.findAll();
			for(Question q: questions) {
				int editDist= calcEditDist(q.getSubject(),search,q.getSubject().length(),search.length());
				if(editDist<10) {
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
	
	public boolean postAnswerReply(AnswerReplyDto answerReplyDto) {
		boolean posted=false;
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		if(user!=null) {
			AnswerReply answerReply = new AnswerReply();
			answerReply.setAnswer(answerRepository.findById(answerReplyDto.getAnswerId()).get());
			answerReply.setAnswerReplyContent(answerReplyDto.getAnswerReplyContent());
			answerReply.setAnswerReplyTimeStamp(answerReplyDto.getAnswerReplyTimeStamp());
			answerReply.setAnswerReplyUser(user);
			answerReplyRepository.save(answerReply);
			posted=true;
		}
		return posted;
	}
	
	public List<AnswerReplyDto> showAnswerReplies(long answerId){
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		List<AnswerReply> answerReplies=new ArrayList<>();
		if(user!=null) {
			answerReplies = answerReplyRepository.findByAnswer(answerRepository.findById(answerId).get());
		
		}
		return answerReplies.stream().map(this::mapFromAnswerReplyToDto).collect(Collectors.toList());
	}
	
	
	public boolean removeQuestion(long questionId) {
		boolean removed=false;
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		Optional<Question> questionFound = questionRepository.findById(questionId);
		if(questionFound.isPresent()) {
			
			Question question=questionFound.get();
			com.curio.curioapp.curioappbackend.model.User postedUser=question.getQuestionedUser();
			if(postedUser.getUserId()==user.getUserId()) {
				
				
					
				question.setQuestionContent("");
				question.setSubject("[DELETED]");
				questionRepository.save(question);
				
				removed=true;
				
			}
			
		}
		return removed;
	}
	
	public boolean removeAnswer(long answerId) {
		boolean removed=false;
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		Optional<Answer> answerFound = answerRepository.findById(answerId);
		if(answerFound.isPresent()) {
			Answer answer=answerFound.get();
			com.curio.curioapp.curioappbackend.model.User postedUser=answer.getAnsweredUser();
			if(postedUser.getUserId()==user.getUserId()) {
				
				if(answer.getAsnwerReplies().size()>0) {
					answer.setAnswerContent("[This answer was deleted by "+user.getUsername()+"]");
					answer.setAnsweredUser(null);
					answerRepository.save(answer);
					removed=true;
				}else {
					answerRepository.deleteById(answerId);
					removed=true;
				}
				
			}
		
			
			
		}
		
		
		return removed;
	}
	
	public boolean removeAnswerReply(long answerReplyId) {
		boolean removed=false;
		com.curio.curioapp.curioappbackend.model.User user = getCurrentlyLoggedInUser();
		Optional<AnswerReply> answerReplyFound = answerReplyRepository.findById(answerReplyId);
		if(answerReplyFound.isPresent()) {
		
			AnswerReply answerReply=answerReplyFound.get();
		
			com.curio.curioapp.curioappbackend.model.User postedUser=answerReply.getAnswerReplyUser();
			if(postedUser.getUserId()==user.getUserId()) {
				Answer answer=answerReply.getAnswer();
				answerReplyRepository.deleteById(answerReplyId);
				removed=true;
				if(answer.getAsnwerReplies().size()<1 && answer.getAnsweredUser()==null) {
					long aid=answer.getAnswerId();
					answerRepository.deleteById(aid);
				}
				
			}
			
		
		}
		
		
		return removed;
	}
	
	
	
	private QuestionDto mapFromQuestionToDto(Question question) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setQuestionId(question.getQuestionId());
		questionDto.setSubject(question.getSubject());
		questionDto.setQuestionContent(question.getQuestionContent());
		questionDto.setQuestionedTimeStamp(question.getQuestionedTimeStamp());
		
		if(question.getQuestionedUser()!=null) {
			questionDto.setAskedUsername(question.getQuestionedUser().getUsername());
		}else {
			questionDto.setAskedUsername("");
		}
		
		questionDto.setNumOfAnswers(question.getAnswers().size());
		System.out.println(questionDto.getSubject());
		return questionDto;
	}
	
	private AnswerReplyDto mapFromAnswerReplyToDto(AnswerReply answerReply) {
		AnswerReplyDto answerReplyDto = new AnswerReplyDto();
		answerReplyDto.setAnswerId(answerReply.getAnswer().getAnswerId());
		answerReplyDto.setAnswerReplyContent(answerReply.getAnswerReplyContent());
		answerReplyDto.setAnswerReplyId(answerReply.getAnswerReplyId());
		answerReplyDto.setAnswerReplyTimeStamp(answerReply.getAnswerReplyTimeStamp());
		answerReplyDto.setAnswerReplyUsername(answerReply.getAnswerReplyUser().getUsername());
		return answerReplyDto;
	}
	
	private AnswerDto mapFromAnswerToDto(Answer answer) {
		AnswerDto answerDto = new AnswerDto();
		answerDto.setAnswerId(answer.getAnswerId());
		answerDto.setNumOfReplies(answer.getAsnwerReplies().size());
		
		answerDto.setAnswerContent(answer.getAnswerContent());
		answerDto.setAnsweredTimeStamp(answer.getAnsweredTimeStamp());
		if(answer.getAnsweredUser()!=null) {
			answerDto.setAnsweredUsername(answer.getAnsweredUser().getUsername());
		}else {
			answerDto.setAnsweredUsername("");
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
	
	
	
	private int calcEditDist(String str1, String str2, int m, int n) {
		if(m==0) {
			return n;
		}
		if(n==0) {
			return m;
		}
		
		if(str1.charAt(m-1)== str2.charAt(n-1)) {
			return calcEditDist(str1,str2,m-1,n-1);
		}
		return 1 + getMin(calcEditDist(str1,str2,m,n-1), calcEditDist(str1,str2,m-1,n), calcEditDist(str1,str2,m-1,n-1));
	}
	
	private int getMin(int a, int b ,int c) {
		if(a<=b && a<=c) {
			return a;
		}
		if(b<=a && b<=c) {
			return b;
		}
		else {
			return c;
		}
	}
}
