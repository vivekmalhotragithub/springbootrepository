package com.example.vivek.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringcloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigApplication.class, args);
	}
}
