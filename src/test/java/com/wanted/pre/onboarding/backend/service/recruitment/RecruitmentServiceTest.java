package com.wanted.pre.onboarding.backend.service.recruitment;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentRequest;
import com.wanted.pre.onboarding.backend.entity.company.Company;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.repository.company.CompanyRepository;
import com.wanted.pre.onboarding.backend.repository.recruitment.RecruitmentRepository;

@ExtendWith(MockitoExtension.class)
class RecruitmentServiceTest {

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private RecruitmentRepository recruitmentRepository;

	@InjectMocks
	private RecruitmentService recruitmentService;

	private Company company;

	@BeforeEach
	void setUp() {
		company = new Company(1L, "원티드", "한국", "서울");
	}

	@Test
	@DisplayName("채용공고를 작성하면 정상적으로 저장된다.")
	void saveRecruitment() {
		// Given
		RecruitmentRequest request = new RecruitmentRequest(company.getId(), "백엔드 주니어 개발자", 1000, "자프링 개발자 모집합니다.", "Java");

		when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
		when(recruitmentRepository.save(any(Recruitment.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// When
		recruitmentService.saveRecruitment(request);

		// Then
		verify(companyRepository).findById(company.getId());
		verify(recruitmentRepository).save(any(Recruitment.class));
	}

	@Test
	@DisplayName("존재하지 않는 회사가 채용공고를 작성하면 예외가 발생한다.")
	void saveRecruitmentWithNonExistentCompany() {
		// Given
		Long nonExistentCompanyId = 2L;
		RecruitmentRequest request = new RecruitmentRequest(nonExistentCompanyId, "백엔드 주니어 개발자", 1000, "자프링 개발자 모집합니다.", "Java");

		when(companyRepository.findById(nonExistentCompanyId)).thenReturn(Optional.empty());

		// When & Than
		assertThatThrownBy(() -> recruitmentService.saveRecruitment(request))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("회사가 존재하지 않습니다.");

		verify(companyRepository).findById(nonExistentCompanyId);
		verify(recruitmentRepository, never()).save(any(Recruitment.class));
	}
}