package com.wanted.pre.onboarding.backend.controller.recruitment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.pre.onboarding.backend.dto.common.CommonResponse;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentRequest;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentResponse;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentUpdate;
import com.wanted.pre.onboarding.backend.service.recruitment.RecruitmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {
	private final RecruitmentService recruitmentService;

	@GetMapping("/v1/recruitments")
	public ResponseEntity<CommonResponse> getRecruitment() {
		List<RecruitmentResponse> recruitmentResponses = recruitmentService.findRecruitmentList();
		CommonResponse response = new CommonResponse(HttpStatus.ACCEPTED, recruitmentResponses);
		return new ResponseEntity<>(response, response.getCode());
	}

	@PostMapping("/v1/recruitments")
	public ResponseEntity<CommonResponse> createRecruitment(@RequestBody RecruitmentRequest request) {
		recruitmentService.saveRecruitment(request);
		CommonResponse response = new CommonResponse(HttpStatus.CREATED, request);
		return new ResponseEntity<>(response, response.getCode());
	}

	@PutMapping("/v1/recruitments/{companyId}/{recruitmentId}")
	public ResponseEntity<CommonResponse> updateRecruitment(@PathVariable("companyId") Long companyId, @PathVariable("recruitmentId") Long recruitmentId,
		@RequestBody RecruitmentUpdate update) {
		recruitmentService.updateRecruitment(companyId, recruitmentId, update);
		CommonResponse response = new CommonResponse(HttpStatus.CREATED, update);
		return new ResponseEntity<>(response, response.getCode());
	}

	@DeleteMapping("/v1/recruitments/{companyId}/{recruitmentId}")
	public ResponseEntity<CommonResponse> deleteRecruitment(@PathVariable("companyId") Long companyId, @PathVariable("recruitmentId") Long recruitmentId) {
		recruitmentService.deleteRecruitment(companyId, recruitmentId);
		CommonResponse response = new CommonResponse(HttpStatus.OK, recruitmentId);
		return new ResponseEntity<>(response, response.getCode());
	}

	@GetMapping("/v1/recruitments/search")
	public ResponseEntity<CommonResponse> searchRecruitments(@RequestParam String keyword) {
		CommonResponse response = new CommonResponse(HttpStatus.OK, recruitmentService.findRecruitmentListByKeyword(keyword));
		return new ResponseEntity<>(response, response.getCode());
	}

	@GetMapping("/v1/recruitments/{id}")
	public ResponseEntity<CommonResponse> getRecruitmentDetail(@PathVariable("id") Long id) {
		CommonResponse response = new CommonResponse(HttpStatus.OK, recruitmentService.findRecruitmentDetail(id));
		return new ResponseEntity<>(response, response.getCode());
	}
}
