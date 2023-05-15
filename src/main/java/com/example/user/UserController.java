//package com.example.user;
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
//public class UserController {
//
//	   @Autowired
//	   private UserRepository userRepository;
//
//	     // get all user
//		   @GetMapping("/user")
//		   public List<User> getAllUser() {
//			   return (List<User>)this.userRepository.findAll();
//		   }
//
//
//	   // get user detail by its email
//	   @GetMapping("/user/{email}")
//	   public User getUserDetails(@PathVariable("email") String email) {
//		   return this.userRepository.findByEmail(email);
//	   }
//
//
//	   // to add a new user
//	   @PostMapping("/user")
//	   public User addNewUser(@RequestBody User user) {
//		   return this.userRepository.save(user);
//	   }
//}
package com.example.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/user")
	public List<User> getAllUser(HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		List<User> userList = (List<User>) this.userRepository.findAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "All users fetched successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return userList;
	}

	@GetMapping("/user/{email}")
	public User getUserDetails(@PathVariable("email") String email, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		User user = this.userRepository.findByEmail(email);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "User details fetched successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return user;
	}

	@PostMapping("/user")
	public User addNewUser(@RequestBody User user, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		User newUser = this.userRepository.save(user);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "POST";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.CREATED.value();

		// ... your code here ...
		String requestData = "New user added successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return newUser;
	}
}
