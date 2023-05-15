//package com.example.question;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class QuestionController {
//
//	 @Autowired
//	  private QuestionRepository questionRepository;
//
//
//	 //to show all question present in database
//	 @GetMapping("/question")
//	 public List<Question> getAllQuestion(){
//		 return (List<Question>) this.questionRepository.findAll();
//	 }
//
//
//	 //add a question in a particular exam
//	 @PostMapping("/question")
//	 public Question addNewQuestion(@RequestBody Question question ){
//		 return this.questionRepository.save(question);
//	 }
//
//
//	 //to get details of all question of that particular exam   (ofcourse using exam_id)
//	 @GetMapping("/exam/{id}/question")
//	 public List<Question> getAllQuestionForExam(@PathVariable("id") int id){
//		 return this.questionRepository.findByEnameId(id);
//	 }
//
//
//	 //edit a question in a particular exam
//	 @PutMapping("/question/{id}")
//	  public Question updateQuestion(@PathVariable("id") int id , @RequestBody Question question) {
//	  	   	 question.setId(id);
//		  return this.questionRepository.save(question);
//	  }
//
//
//	 // delete a question in a particular exam
//	 @DeleteMapping("/question/{id}")
//	 public void deleteQuestion(@PathVariable("id") int id) {
//		 this.questionRepository.deleteById(id);
//	 }
//
//
//}
package com.example.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	//to show all question present in database
	@GetMapping("/question")
	public List<Question> getAllQuestion(HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		List<Question> questions = (List<Question>) this.questionRepository.findAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "All questions fetched successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return questions;
	}

	//add a question in a particular exam
	@PostMapping("/question")
	public Question addNewQuestion(@RequestBody Question question, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		Question savedQuestion = this.questionRepository.save(question);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "POST";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "New question added successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return savedQuestion;
	}

	//to get details of all question of that particular exam   (of course using exam_id)
	@GetMapping("/exam/{id}/question")
	public List<Question> getAllQuestionForExam(@PathVariable("id") int id, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		List<Question> questions = this.questionRepository.findByEnameId(id);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "All questions of exam " + id + " fetched successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return questions;

	}
	// edit a question in a particular exam
	@PutMapping("/question/{id}")
	public Question updateQuestion(@PathVariable("id") int id, @RequestBody Question question, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		question.setId(id);
		Question updatedQuestion = this.questionRepository.save(question);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "PUT";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "Question updated successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return updatedQuestion;
	}

	// delete a question in a particular exam
	@DeleteMapping("/question/{id}")
	public void deleteQuestion(@PathVariable("id") int id, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		this.questionRepository.deleteById(id);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "DELETE";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.NO_CONTENT.value();

		// ... your code here ...
		String requestData = "Question deleted successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
	}
}


