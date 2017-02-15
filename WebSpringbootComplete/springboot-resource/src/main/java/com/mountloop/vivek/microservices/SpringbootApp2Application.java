package com.mountloop.vivek.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@EnableAuthorizationServer
//@EnableOAuth2Sso
@EnableResourceServer
@SpringBootApplication
public class SpringbootApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApp2Application.class, args);
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
		logger.info("name of developer: "+developer );
		
		//
		greet.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(GreetingController.class)
						.greeting(name)).withSelfRel());
		return new ResponseEntity<Greet>(greet, HttpStatus.OK);
	}
}

class Greet extends ResourceSupport {
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
