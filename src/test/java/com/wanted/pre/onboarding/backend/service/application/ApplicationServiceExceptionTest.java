package com.wanted.pre.onboarding.backend.service.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wanted.pre.onboarding.backend.dto.application.ApplicationRequest;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.entity.user.User;
import com.wanted.pre.onboarding.backend.repository.application.ApplicationRepository;
import com.wanted.pre.onboarding.backend.repository.recruitment.RecruitmentRepository;
import com.wanted.pre.onboarding.backend.repository.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceExceptionTest {

	@Mock
	private ApplicationRepository applicationRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RecruitmentRepository recruitmentRepository;

	@InjectMocks
	private ApplicationService applicationService;

	@Test
	@DisplayName("존재하지 않는 사용자가 채용공고에 지원하면 예외가 발생한다.")
	void saveApplicationWithNonExistenceUser() {
		// Given
		Long nonExistenceId = 1L;
		ApplicationRequest request = new ApplicationRequest(nonExistenceId, 1L);

		when(userRepository.findById(request.getUserId())).thenReturn(Optional.empty());
		// When & Then
		assertThatThrownBy(() -> applicationService.saveApplication(request))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("사용자가 존재하지 않습니다.");

		verify(userRepository).findById(request.getUserId());
		verify(recruitmentRepository, never()).findById(anyLong());
		verify(applicationRepository, never()).existsByUserAndRecruitment(any(), any());
		verify(applicationRepository, never()).save(any());
	}

	@Test
	@DisplayName("존재하지 않는 채용공고에 지원하면 예외가 발생한다.")
	void saveApplicationWithNonExistenceRecruitment() {
		// Given
		User user = new User(1L);
		Long nonExistenceRecruitmentId = 1L;
		ApplicationRequest request = new ApplicationRequest(user.getId(), nonExistenceRecruitmentId);

		when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(user));
		when(recruitmentRepository.findById(request.getRecruitmentId())).thenReturn(Optional.empty());

		// When & Then
		assertThatThrownBy(() -> applicationService.saveApplication(request))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 채용공고는 존재하지 않습니다.");

		verify(userRepository).findById(request.getUserId());
		verify(recruitmentRepository).findById(request.getRecruitmentId());
		verify(applicationRepository, never()).existsByUserAndRecruitment(any(), any());
		verify(applicationRepository, never()).save(any());
	}

	@Test
	@DisplayName("이미 지원한 채용공고에 지원하면 예외가 발생한다.")
	void saveDuplicatedApplication() {
		// Given
		User user = new User(1L);
		Recruitment recruitment = new Recruitment(1L, "백엔드 주니어 개발자", 1000, "자프링 개발자 모집합니다.", "Java");
		ApplicationRequest request = new ApplicationRequest(user.getId(), recruitment.getId());

		when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(user));
		when(recruitmentRepository.findById(request.getRecruitmentId())).thenReturn(Optional.of(recruitment));
		when(applicationRepository.existsByUserAndRecruitment(user, recruitment)).thenReturn(true);

		// When & Then
		assertThatThrownBy(() -> applicationService.saveApplication(request))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이미 지원한 공고입니다.");

		verify(userRepository).findById(request.getUserId());
		verify(recruitmentRepository).findById(request.getRecruitmentId());
		verify(applicationRepository).existsByUserAndRecruitment(user, recruitment);
		verify(applicationRepository, never()).save(any());
	}
}
