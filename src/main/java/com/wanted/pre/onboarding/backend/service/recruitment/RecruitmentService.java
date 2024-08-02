package com.wanted.pre.onboarding.backend.service.recruitment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentRequest;
import com.wanted.pre.onboarding.backend.entity.company.Company;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.repository.company.CompanyRepository;
import com.wanted.pre.onboarding.backend.repository.recruitment.RecruitmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentService {
	private final RecruitmentRepository recruitmentRepository;
	private final CompanyRepository companyRepository;

	@Transactional
	public void saveRecruitment(RecruitmentRequest request) {
		Company company = companyRepository.findById(request.getCompanyId())
			.orElseThrow(() -> new IllegalArgumentException("회사가 존재하지 않습니다."));
		Recruitment recruitment = recruitmentRepository.save(new Recruitment(request.getPosition(), request.getCompensation(), request.getContent(), request.getSkill()));
		recruitment.setCompany(company);
	}
}
