package com.wanted.pre.onboarding.backend.entity.recruitment;

import com.wanted.pre.onboarding.backend.entity.company.Company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Recruitment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String position;

	private int compensation;

	private String content;

	private String skill;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
}
