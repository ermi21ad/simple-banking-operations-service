package com.ermias.bankingservice.bankingservice.controller;

import com.ermias.bankingservice.bankingservice.dto.request.ApplicationSubmitRequest;
import com.ermias.bankingservice.bankingservice.dto.response.ApplicationResponse;
import com.ermias.bankingservice.bankingservice.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @PostMapping("/submit")
    public ResponseEntity<ApplicationResponse> submitApplication(
            @Valid @RequestBody ApplicationSubmitRequest request) {

        ApplicationResponse response =
                applicationService.submitApplication(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
