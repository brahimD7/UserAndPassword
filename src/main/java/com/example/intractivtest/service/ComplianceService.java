package com.example.intractivtest.service;

import com.example.intractivtest.dto.Compliance;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ComplianceService {

    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!#$%&@]");
    private static final Pattern UPPER_CASE_PATTERN = Pattern.compile("[A-Z]");

    private static final String AT_LEAST_8_CHARACTERS_MESSAGE = "at least 8 characters, ";
    private static final String AT_LEAST_ONE_DIGIT_MESSAGE = "at least one digit, ";
    private static final String AT_LEAST_ONE_SPECIAL_CHARACTER_MESSAGE = "at least one special character (!,#,$,%,&,@), ";
    private static final String AT_LEAST_ONE_UPPERCASE_MESSAGE = "at least one uppercase";

    public Compliance getPasswordCompliance(String password){

        StringBuilder reasonStringBuilder = new StringBuilder();

        if (password == null || password.length() < 8) {
            reasonStringBuilder.append(AT_LEAST_8_CHARACTERS_MESSAGE);
        }

        Matcher digitMatcher = DIGIT_PATTERN.matcher(password);
        if (!digitMatcher.find()) {
            reasonStringBuilder.append(AT_LEAST_ONE_DIGIT_MESSAGE);
        }

        Matcher specialCharMatcher = SPECIAL_CHAR_PATTERN.matcher(password);
        if (!specialCharMatcher.find()) {
            reasonStringBuilder.append(AT_LEAST_ONE_SPECIAL_CHARACTER_MESSAGE);
        }

        Matcher upperCaseMatcher = UPPER_CASE_PATTERN.matcher(password);
        if (!upperCaseMatcher.find()) {
            reasonStringBuilder.append(AT_LEAST_ONE_UPPERCASE_MESSAGE);
        }

        if (reasonStringBuilder.isEmpty()) {
            return new Compliance(true, null);
        } else {
            return new Compliance(false, reasonStringBuilder.toString());
        }
    }
}
