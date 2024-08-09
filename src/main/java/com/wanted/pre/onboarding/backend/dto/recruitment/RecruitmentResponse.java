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
