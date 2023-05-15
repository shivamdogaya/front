//package com.example.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AdminController {
//
//	@Autowired
//	private AdminRepository adminRepository ;
//
//	// get admin detail by admin_name
//
//	@GetMapping("/admin/{name}")
//	public Admin getAdminDetails(@PathVariable("name") String name){
//		return this.adminRepository.findByName(name);
//	}
//
//	@GetMapping("admin/test")
//	public String testAdminEndpoint() {
//		return "Hello from the admin endpoint";
//	}
//
//}
package com.example.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@GetMapping("/admin/{name}")
	public Admin getAdminDetails(@PathVariable("name") String name, HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		Admin admin = this.adminRepository.findByName(name);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "Admin details fetched successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return admin;
	}

	@GetMapping("/admin/test")
	public String testAdminEndpoint(HttpServletRequest req) {
		long startTime = System.currentTimeMillis();

		String message = "Hello from the admin endpoint";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
		String timestamp = LocalDateTime.now().format(formatter);
		String httpRequestMethod = "GET";
		String uriPath = req.getRequestURI();
		int httpStatus = HttpStatus.OK.value();

		// ... your code here ...
		String requestData = "Test endpoint accessed successfully";
		// Calculate the response time
		long endTime = System.currentTimeMillis();
		double responseTime = (endTime - startTime) / 1000.0;

		logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

		return message;
	}

}
