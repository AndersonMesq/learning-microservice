package com.amv.naming_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingServerApplication {
//http://localhost:8761/
	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

}
