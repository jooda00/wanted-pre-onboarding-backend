package com.wanted.pre.onboarding.backend.entity.recruitment;

import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentUpdate;
import com.wanted.pre.onboarding.backend.entity.company.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Recruitment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recruitment_id")
	private Long id;

	private String position;

	private int compensation;

	private String content;

	private String skill;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public Recruitment(String position, int compensation, String content, String skill) {
		this.position = position;
		this.compensation = compensation;
		this.content = content;
		this.skill = skill;
	}

	public Recruitment(Long id, String position, int compensation, String content, String skill) {
		this.id = id;
		this.position = position;
		this.compensation = compensation;
		this.content = content;
		this.skill = skill;
	}

	public void setCompany(Company company) {
		if(this.company != null) this.company.getRecruitments().remove(this);
		this.company = company;
		if(company != null) company.getRecruitments().add(this);
	}

	public void updateRecruitment(RecruitmentUpdate update) {
		this.position = update.getPosition();
		this.compensation = update.getCompensation();
		this.content = update.getContent();
		this.skill = update.getSkill();
	}
}
