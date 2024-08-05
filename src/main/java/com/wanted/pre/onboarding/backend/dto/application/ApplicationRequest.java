package com.wanted.pre.onboarding.backend.dto.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationRequest {
	private Long userId;
	private Long recruitmentId;
}
