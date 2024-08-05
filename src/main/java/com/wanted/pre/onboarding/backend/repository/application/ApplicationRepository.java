package com.wanted.pre.onboarding.backend.repository.application;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.pre.onboarding.backend.entity.application.Application;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.entity.user.User;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	boolean existsByUserAndRecruitment(User user, Recruitment recruitment);
}
