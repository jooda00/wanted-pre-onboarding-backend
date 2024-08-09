package com.wanted.pre.onboarding.backend.service.recruitment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentDetailResponse;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentRequest;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentResponse;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentUpdate;
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

	@Transactional
	public void updateRecruitment(Long companyId, Long recruitmentId, RecruitmentUpdate update) {
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new IllegalArgumentException("회사가 존재하지 않습니다."));
		Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
			.orElseThrow(() -> new IllegalArgumentException("해당 채용공고는 존재하지 않습니다."));
		if(!company.getRecruitments().contains(recruitment)) throw new IllegalArgumentException("귀사가 작성하지 않은 채용공고는 수정할 수 없습니다.");
		recruitment.updateRecruitment(update); // 변경 감지
	}

	@Transactional
	public void deleteRecruitment(Long companyId, Long recruitmentId) {
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new IllegalArgumentException("회사가 존재하지 않습니다."));
		Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
			.orElseThrow(() -> new IllegalArgumentException("해당 채용공고는 존재하지 않습니다."));
		if(!company.getRecruitments().contains(recruitment)) throw new IllegalArgumentException("귀사가 작성하지 않은 채용공고는 삭제할 수 없습니다.");
		recruitmentRepository.delete(recruitment);
	}

	public List<RecruitmentResponse> findRecruitmentList() {
		List<RecruitmentResponse> recruitments = recruitmentRepository.findAllRecruitmentWithCompany()
			.stream()
			.map(recruitment -> new RecruitmentResponse(recruitment))
			.collect(Collectors.toList());
		return recruitments;
	}

	public List<RecruitmentResponse> findRecruitmentListByKeyword(String keyword) {
		return recruitmentRepository.findRecruitmentsByKeyword(keyword);
	}

	public RecruitmentDetailResponse findRecruitmentDetail(Long id) {
		Recruitment recruitment = recruitmentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 채용공고는 존재하지 않습니다."));
		return new RecruitmentDetailResponse(recruitment);
	}
}
