package com.wanted.pre.onboarding.backend.service.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.pre.onboarding.backend.dto.application.ApplicationRequest;
import com.wanted.pre.onboarding.backend.entity.application.Application;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;
import com.wanted.pre.onboarding.backend.entity.user.User;
import com.wanted.pre.onboarding.backend.repository.application.ApplicationRepository;
import com.wanted.pre.onboarding.backend.repository.recruitment.RecruitmentRepository;
import com.wanted.pre.onboarding.backend.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {
	private final ApplicationRepository applicationRepository;
	private final UserRepository userRepository;
	private final RecruitmentRepository recruitmentRepository;

	@Transactional
	public void saveApplication(ApplicationRequest request) {
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
		Recruitment recruitment = recruitmentRepository.findById(request.getRecruitmentId())
			.orElseThrow(() -> new IllegalArgumentException("해당 채용공고는 존재하지 않습니다."));
		if(applicationRepository.existsByUserAndRecruitment(user, recruitment)) throw new IllegalArgumentException("이미 지원한 공고입니다.");
		applicationRepository.save(new Application(user, recruitment));
	}
}
