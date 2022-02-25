package com.course5i.base.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.course5i.base.framework")
@EnableSwagger2
public class Application {

	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		System.out.println("before SpringApplication");

		SpringApplication.run(Application.class, args);
		System.out.println("after SpringApplication");

	}

}
