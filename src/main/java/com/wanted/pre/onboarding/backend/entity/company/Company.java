package com.wanted.pre.onboarding.backend.entity.company;

import java.util.ArrayList;
import java.util.List;

import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long id;

	private String name;

	private String country;

	private String city;

	@OneToMany(mappedBy = "company", orphanRemoval = true)
	private List<Recruitment> recruitments = new ArrayList<>();

	public Company(Long id, String name, String country, String city) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
	}
}
