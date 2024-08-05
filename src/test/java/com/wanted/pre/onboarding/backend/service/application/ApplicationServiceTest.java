package com.wanted.pre.onboarding.backend.service.application;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wanted.pre.onboarding.backend.dto.application.ApplicationRequest;
import com.wanted.pre.onboarding.backend.entity.application.Application;
import com.wanted.pre.onboarding.backend.entity.company.Company;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.entity.user.User;
import com.wanted.pre.onboarding.backend.repository.application.ApplicationRepository;
import com.wanted.pre.onboarding.backend.repository.recruitment.RecruitmentRepository;
import com.wanted.pre.onboarding.backend.repository.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

	@Mock
	private ApplicationRepository applicationRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RecruitmentRepository recruitmentRepository;

	@InjectMocks
	private ApplicationService applicationService;

	@Test
	@DisplayName("사용자가 채용공고에 지원하면 정상적으로 지원된다.")
	void saveApplicationByUser() {
		// Given
		User user = new User(1L);
		Company company = new Company(1L, "원티드", "한국", "서울");
		Recruitment recruitment = new Recruitment(1L, "백엔드 주니어 개발자", 1000, "자프링 개발자 모집합니다.", "Java");
		recruitment.setCompany(company);

		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(recruitmentRepository.findById(recruitment.getId())).thenReturn(Optional.of(recruitment));

		// When
		applicationService.saveApplication(new ApplicationRequest(user.getId(), recruitment.getId()));

		// Then
		verify(applicationRepository).save(any(Application.class));
		verify(applicationRepository).existsByUserAndRecruitment(user, recruitment);
	}
}
