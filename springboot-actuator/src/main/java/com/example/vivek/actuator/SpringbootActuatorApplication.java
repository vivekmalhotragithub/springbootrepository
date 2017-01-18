package com.example.vivek.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringbootActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootActuatorApplication.class, args);
	}
}

@RestController
class GreetingController {

	static Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	Environment env;

	@RequestMapping("/greeting")
	@ResponseBody
	public HttpEntity<Greet> greeting(
			@RequestParam(value = "name", required = false, defaultValue = "HATEAS") String name) {
		Greet greet = new Greet("Hello World " + name);

		//
		String developer = env.getProperty("developer.name", "unknown person");
		logger.info("name of developer: " + developer);

		return new ResponseEntity<Greet>(greet, HttpStatus.OK);
	}
}

class Greet {
	private String message;

	public Greet() {
	}

	public Greet(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}