package com.curio.curioapp.curioappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curio.curioapp.curioappbackend.dto.AnswerDto;
import com.curio.curioapp.curioappbackend.dto.QuestionDto;
import com.curio.curioapp.curioappbackend.service.DiscussionService;


@RestController
@RequestMapping("/api/discussion/")
public class DiscussionController {
	@Autowired
	private DiscussionService discussionService;
	

	@PostMapping
	public ResponseEntity<?> postQuestion(@RequestBody QuestionDto questionDto) {
		boolean posted=false;
		
		posted = discussionService.postQuestion(questionDto);
		if(posted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/allquestions")
	public ResponseEntity<List<QuestionDto>> showAllQuestions() {
		return new ResponseEntity<>(discussionService.showAllQuestions(),HttpStatus.OK);	
	}
	
	@GetMapping("/myquestions")
	public ResponseEntity<List<QuestionDto>> showMyQuestions() {
		return new ResponseEntity<>(discussionService.showMyQuestions(),HttpStatus.OK);	
	}
	
	@PostMapping("/postanswer")
	public ResponseEntity<?> postAnswer(@RequestBody AnswerDto answerDto) {
		boolean posted=false;
		
		posted = discussionService.postAnswer(answerDto);
		if(posted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/showanswers/{questionId}")
	public ResponseEntity<List<AnswerDto>> showAnswers(@PathVariable long questionId) {
		return new ResponseEntity<>(discussionService.showAnswers(questionId),HttpStatus.OK);	
	}
	
}
