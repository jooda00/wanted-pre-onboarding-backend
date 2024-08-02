package com.wanted.pre.onboarding.backend.repository.recruitment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

}
