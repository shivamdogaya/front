//package com.example.result;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ResultController {
//
//	  @Autowired
//	  private ResultRepository resultRepository;
//
//	  //get all result present in database
//	  @GetMapping("/result")
//	  public List<Result> getAllResult(){
//		  return (List<Result>)this.resultRepository.findAll();
//	  }
//
//
//	   //to save result
//	  @PostMapping("/result")
//	  public  Result addNewResult(@RequestBody Result result){
//		  return this.resultRepository.save(result);
//	  }
//
//	   //get all result of a particular student
//	  @GetMapping("/user/{email}/result")
//	  public List<Result> getAllResultForStudent(@PathVariable("email") String email){
//		  return this.resultRepository.findByEmailEmail(email);
//
//	  }
//
//}
package com.example.result;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {

	@Autowired
	private ResultRepository resultRepository;

	private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

	@GetMapping("/result")
	public List<Result> getAllResult(HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		List<Result> result = (List<Result>) this.resultRepository.findAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "All result fetched successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime,
				requestData);

		return result;
	}

	@PostMapping("/result")
	public ResponseEntity<Result> addNewResult(@RequestBody Result result, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		Result newResult = this.resultRepository.save(result);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "POST";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.CREATED.value();

		// ... your code here ...
		String requestData = "Result added successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime,
				requestData);

		return new ResponseEntity<>(newResult, HttpStatus.CREATED);
	}

	@GetMapping("/user/{email}/result")
	public List<Result> getAllResultForStudent(@PathVariable("email") String email, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		List<Result> result = this.resultRepository.findByEmailEmail(email);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "All result fetched successfully for student with email: " + email;
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime,
				requestData);

		return result;

	}

}

