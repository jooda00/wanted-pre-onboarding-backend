package com.wanted.pre.onboarding.backend.controller.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.pre.onboarding.backend.dto.application.ApplicationRequest;
import com.wanted.pre.onboarding.backend.dto.common.CommonResponse;
import com.wanted.pre.onboarding.backend.service.application.ApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApplicationController {
	private final ApplicationService applicationService;

	@PostMapping("/v1/applications")
	public ResponseEntity<CommonResponse> createApplication(@RequestBody ApplicationRequest request) {
		applicationService.saveApplication(request);
		CommonResponse response = new CommonResponse(HttpStatus.CREATED, request);
		return new ResponseEntity<>(response, response.getCode());
	}
}
