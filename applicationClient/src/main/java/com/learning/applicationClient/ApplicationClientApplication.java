package com.learning.applicationClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import io.micrometer.core.ipc.http.HttpSender.Response;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ApplicationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationClientApplication.class, args);
	}

	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	
	
	//TODO 
	@RequestMapping("/")
	public String getValue() {
		
	 InstanceInfo i  =  eurekaClient.getNextServerFromEureka("service1", false);
	 
	 String baseUrl = i.getHomePageUrl();
		
		RestTemplate restTemplate =  restTemplateBuilder.build();
		ResponseEntity<String> resposne =   restTemplate.exchange(baseUrl+"/hello", HttpMethod.GET, null, String.class);
		return resposne.getBody();
	}
	
}
