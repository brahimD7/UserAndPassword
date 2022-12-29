package com.example.intractivtest.controller.impl;

import com.example.intractivtest.controller.ComplianceController;
import com.example.intractivtest.dto.Compliance;
import com.example.intractivtest.service.ComplianceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/compliance")
@AllArgsConstructor
public class ComplianceControllerImpl implements ComplianceController {

    private final ComplianceService complianceService;

    @Override
    public ResponseEntity<Compliance> isPasswordCompliant(String password) {
        return ResponseEntity.ok(complianceService.getPasswordCompliance(password));
    }
}
