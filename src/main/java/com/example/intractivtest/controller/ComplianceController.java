package com.example.intractivtest.controller;

import com.example.intractivtest.dto.Compliance;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/compliance")
public interface ComplianceController {

    @Operation(description = "Return true if password is compliant or false and reasons if not, Complexity = at least 8 characters at least one digit at least one special character (!,#,$,%,&,@) at least one uppercase letter", tags={ "Compliance", })
    @GetMapping(path = "/password/{password}",produces = "application/json", consumes = "application/json")
    ResponseEntity<Compliance> isPasswordCompliant(@Parameter() @PathVariable(name = "password") String password);
}
