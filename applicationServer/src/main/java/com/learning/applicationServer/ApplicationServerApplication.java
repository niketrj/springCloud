package com.learning.applicationServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ApplicationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationServerApplication.class, args);
	}
	
	
	@Value("${spring.instance.name}")
	private String a ;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello from "+a;
	}

}
