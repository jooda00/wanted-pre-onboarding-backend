package com.wanted.pre.onboarding.backend.dto.recruitment;

import java.util.ArrayList;
import java.util.List;

import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;

import lombok.Getter;

@Getter
public class RecruitmentDetailResponse {
	private Long recruitmentId;
	private String companyName;
	private String country;
	private String city;
	private String position;
	private int compensation;
	private String skill;
	private String content;
	private List<Long> otherRecruitmentsIds = new ArrayList<>();

	public RecruitmentDetailResponse(Recruitment recruitment) {
		this.recruitmentId = recruitment.getId();
		this.companyName = recruitment.getCompany().getName();
		this.country = recruitment.getCompany().getCountry();
		this.city = recruitment.getCompany().getCity();
		this.position = recruitment.getPosition();
		this.compensation = recruitment.getCompensation();
		this.skill = recruitment.getSkill();
		this.content = recruitment.getContent();
		for (Recruitment r : recruitment.getCompany().getRecruitments()) {
			if(r.getId().equals(recruitmentId)) continue;
			otherRecruitmentsIds.add(r.getId());
		}
	}
}
