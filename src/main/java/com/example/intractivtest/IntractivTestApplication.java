package com.example.intractivtest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User an Password",version = "v2"))
public class IntractivTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntractivTestApplication.class, args);
	}

}
