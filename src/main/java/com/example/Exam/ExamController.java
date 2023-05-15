//package com.example.Exam;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class ExamController {
//
//	@Autowired
//	private ExamRepository examRepository;
//
//
//   // to get all exam
//	@GetMapping("/exam")
//	public List<Exam> getAllExam(){
//		System.out.println("++++++++++++exam called+++++++++++");
//		return (List<Exam>)this.examRepository.findAll();
//	}
//
//   //to get details of a particular exam
//	@GetMapping("/exam/{id}")
//	public Exam getParticularExam(@PathVariable("id") int id){
// 		 Optional<Exam> optional =  this.examRepository.findById(id);
//		return optional.get();
//	}
//
//    //to add a new exam
//	@PostMapping("/exam")
//	public Exam addNewExam(@RequestBody Exam exam ){
//		return this.examRepository.save(exam);
//	}
//
//	@DeleteMapping("/exam/{id}")
//	public void deleteExam(@PathVariable("id") int id) {
//		examRepository.deleteById(id);
//	}
//
//
//
//
//
//
//}
package com.example.Exam;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ExamController {

	private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

	@Autowired
	private ExamRepository examRepository;

	@GetMapping("/exam")
	public List<Exam> getAllExam(HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		List<Exam> exams = (List<Exam>) this.examRepository.findAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();
		String requestData = "All exams fetched successfully";
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return exams;
	}

	@GetMapping("/exam/{id}")
	public Exam getParticularExam(@PathVariable("id") int id, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		Optional<Exam> optional = this.examRepository.findById(id);
		Exam exam = optional.get();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();
		String requestData = String.format("Exam with id %d fetched successfully", id);
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return exam;
	}

	@PostMapping("/exam")
	public Exam addNewExam(@RequestBody Exam exam, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		Exam savedExam = this.examRepository.save(exam);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "POST";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();
		String requestData = "New exam added successfully";
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return savedExam;
	}

	@DeleteMapping("/exam/{id}")
	public void deleteExam(@PathVariable("id") int id, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		this.examRepository.deleteById(id);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "DELETE";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.NO_CONTENT.value();

		// ... your code here ...
		String requestData = "Exam with id " + id + " deleted successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
	}
}
