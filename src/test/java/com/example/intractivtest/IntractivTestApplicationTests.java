package com.example.intractivtest;

import com.example.intractivtest.controller.ComplianceController;
import com.example.intractivtest.controller.UserController;
import org.assertj.core.api.Assertions;
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
	public void contextLoads() {
		Assertions.assertThat(userController).isNot(null);
		Assertions.assertThat(complianceController).isNot(null);
	}

}
