//package com.example.subject;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class SubjectController {
//
//	 @Autowired
//	 private SubjectRepository subjectRepository;
//
//	    //to get all subject
//	   @GetMapping("/subject")
//	   public List<Subject> getAllSubjects(){
//		   return  (List<Subject>)this.subjectRepository.findAll();
//	   }
//
//	    //to add a new subject
//	   @PostMapping("/subject")
//	    public Subject addNewSubject(@RequestBody Subject subject ){
//		   return  this.subjectRepository.save(subject);
//	    }
//
//	    // to delete a subject
//	   @DeleteMapping("/subject/{name}")
//	    public String deleteSubject(@PathVariable("name") String name){
//		   int row =this.subjectRepository.deleteByName(name);
//		    return row + " row get deleted";
//		}
//
//}
package com.example.subject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;

	private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

	@GetMapping("/subject")
	public List<Subject> getAllSubjects(HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		List<Subject> subjects = (List<Subject>) this.subjectRepository.findAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();
		String requestData = "All subjects retrieved successfully";
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		// Log the request information
		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return subjects;
	}

	@PostMapping("/subject")
	public Subject addNewSubject(@RequestBody Subject subject, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		Subject newSubject = this.subjectRepository.save(subject);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "POST";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();
		String requestData = String.format("New subject added with name '%s'", subject.getName());
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		// Log the request information
		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return newSubject;
	}

	@DeleteMapping("/subject/{name}")
	public String deleteSubject(@PathVariable("name") String name, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		int rowsAffected = this.subjectRepository.deleteByName(name);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "DELETE";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();
		String requestData = String.format("%d row(s) deleted for subject '%s'", rowsAffected, name);
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		// Log the request information
		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return requestData;
	}
}
