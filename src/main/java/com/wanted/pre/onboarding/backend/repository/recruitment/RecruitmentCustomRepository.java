package com.wanted.pre.onboarding.backend.repository.recruitment;

import java.util.List;

import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentResponse;

public interface RecruitmentCustomRepository {
	List<RecruitmentResponse> findRecruitmentsByKeyword(String keyword);
}
