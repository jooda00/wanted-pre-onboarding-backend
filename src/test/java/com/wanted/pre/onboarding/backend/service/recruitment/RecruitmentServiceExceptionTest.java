package com.wanted.pre.onboarding.backend.service.recruitment;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentRequest;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentUpdate;
import com.wanted.pre.onboarding.backend.entity.company.Company;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.repository.company.CompanyRepository;
import com.wanted.pre.onboarding.backend.repository.recruitment.RecruitmentRepository;

@ExtendWith(MockitoExtension.class)
public class RecruitmentServiceExceptionTest {

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private RecruitmentRepository recruitmentRepository;

	@InjectMocks
	private RecruitmentService recruitmentService;

	@Test
	@DisplayName("존재하지 않는 회사가 채용공고를 작성하면 예외가 발생한다.")
	void saveRecruitmentWithNonExistentCompany() {
		// Given
		Long nonExistentCompanyId = 1L;
		RecruitmentRequest request = new RecruitmentRequest(nonExistentCompanyId, "백엔드 주니어 개발자", 1000, "자프링 개발자 모집합니다.", "Java");

		when(companyRepository.findById(nonExistentCompanyId)).thenReturn(Optional.empty());

		// When & Than
		assertThatThrownBy(() -> recruitmentService.saveRecruitment(request))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("회사가 존재하지 않습니다.");

		verify(companyRepository).findById(nonExistentCompanyId);
		verify(recruitmentRepository, never()).save(any(Recruitment.class));
	}


	@Test
	@DisplayName("존재하지 않는 채용공고를 수정하면 예외가 발생한다.")
	void updateRecruitmentWithNonExistentRecruitment() {
		// Given
		Company company = new Company();
		Long nonExistentRecruitmentId = 1L;
		RecruitmentUpdate update = new RecruitmentUpdate("백엔드 시니어 개발자", 3000000, "10년 이상 백엔드 개발자 모집합니다.", "Java");

		when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
		when(recruitmentRepository.findById(nonExistentRecruitmentId)).thenReturn(Optional.empty());

		// When & Than
		assertThatThrownBy(() -> recruitmentService.updateRecruitment(company.getId(), nonExistentRecruitmentId, update))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 채용공고는 존재하지 않습니다.");

		verify(companyRepository).findById(company.getId());
		verify(recruitmentRepository).findById(nonExistentRecruitmentId);
		verify(recruitmentRepository, never()).save(any(Recruitment.class));
	}
}
