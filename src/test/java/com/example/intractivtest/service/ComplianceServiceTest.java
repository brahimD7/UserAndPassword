package com.example.intractivtest.service;

import com.example.intractivtest.dto.Compliance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ComplianceServiceTest {

    private static final String AT_LEAST_8_CHARACTERS_MESSAGE = "at least 8 characters, ";
    private static final String AT_LEAST_ONE_DIGIT_MESSAGE = "at least one digit, ";
    private static final String AT_LEAST_ONE_SPECIAL_CHARACTER_MESSAGE = "at least one special character (!,#,$,%,&,@), ";
    private static final String AT_LEAST_ONE_UPPERCASE_MESSAGE = "at least one uppercase";

    @InjectMocks
    ComplianceService complianceService;

    @Test
    void getPasswordCompliance_Ok() {

        String password = "PasswordCompliant77!";

        Compliance compliance = complianceService.getPasswordCompliance(password);

        assertTrue(compliance.isValid());
        assertNull(compliance.getReason());
    }

    @Test
    void getPasswordCompliance_NotValid() {

        String password = "xx";

        Compliance compliance = complianceService.getPasswordCompliance(password);

        assertFalse(compliance.isValid());
        assertTrue(compliance.getReason().contains(AT_LEAST_8_CHARACTERS_MESSAGE));
        assertTrue(compliance.getReason().contains(AT_LEAST_ONE_DIGIT_MESSAGE));
        assertTrue(compliance.getReason().contains(AT_LEAST_ONE_SPECIAL_CHARACTER_MESSAGE));
        assertTrue(compliance.getReason().contains(AT_LEAST_ONE_UPPERCASE_MESSAGE));
    }
}