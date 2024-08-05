package com.wanted.pre.onboarding.backend.entity.application;

import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "recruitment_id")
	private Recruitment recruitment;

	public Application(User user, Recruitment recruitment) {
		this.user = user;
		this.recruitment = recruitment;
	}
}
