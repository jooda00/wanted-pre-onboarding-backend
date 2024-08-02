package com.wanted.pre.onboarding.backend.dto.recruitment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruitmentUpdate {
	private String position;
	private int compensation;
	private String content;
	private String skill;
}
