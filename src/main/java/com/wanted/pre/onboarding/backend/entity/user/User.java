package com.wanted.pre.onboarding.backend.entity.user;

import java.util.ArrayList;
import java.util.List;

import com.wanted.pre.onboarding.backend.entity.application.Application;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Application> applications = new ArrayList<>();

	public User(Long id) {
		this.id = id;
	}
}
