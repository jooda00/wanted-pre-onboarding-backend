package com.wanted.pre.onboarding.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.pre.onboarding.backend.dto.common.CommonResponse;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentRequest;
import com.wanted.pre.onboarding.backend.service.recruitment.RecruitmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {
	private final RecruitmentService recruitmentService;

	@PostMapping("/v1/recruitments")
	public ResponseEntity<CommonResponse> createRecruitment(@RequestBody RecruitmentRequest request) {
		recruitmentService.saveRecruitment(request);
		CommonResponse response = new CommonResponse(HttpStatus.CREATED, request);
		return new ResponseEntity<>(response, response.getCode());
	}
}