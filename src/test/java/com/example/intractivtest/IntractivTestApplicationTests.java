package com.example.intractivtest;

import com.example.intractivtest.controller.ComplianceController;
import com.example.intractivtest.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntractivTestApplicationTests {

	@Autowired
	UserController userController;

	@Autowired
	ComplianceController complianceController;

	@Test
	void contextLoads() {}

}
