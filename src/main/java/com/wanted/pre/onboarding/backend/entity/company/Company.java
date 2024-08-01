package com.wanted.pre.onboarding.backend.entity.company;

import java.util.List;

import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String country;

	private String city;

	@OneToMany(mappedBy = "company", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Recruitment> recruitments;
}
