package com.wanted.pre.onboarding.backend.dto.recruitment;

import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;

import lombok.Getter;

@Getter
public class RecruitmentResponse {
	private Long recruitmentId;
	private String companyName;
	private String country;
	private String city;
	private String position;
	private int compensation;
	private String skill;

	// 두 테이블 각각 select 쿼리 나감 -> 총 2번의 select 쿼리
	public RecruitmentResponse(Recruitment recruitment) {
		this.recruitmentId = recruitment.getId();
		this.companyName = recruitment.getCompany().getName();
		this.country = recruitment.getCompany().getCountry();
		this.city = recruitment.getCompany().getCity();
		this.position = recruitment.getPosition();
		this.compensation = recruitment.getCompensation();
		this.skill = recruitment.getSkill();
	}
}
