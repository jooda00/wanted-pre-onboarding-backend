package com.wanted.pre.onboarding.backend.dto.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CommonResponse {
	private HttpStatus code;
	private Object data;

	public CommonResponse(HttpStatus code, Object data) {
		this.code = code;
		this.data = data;
	}
}
