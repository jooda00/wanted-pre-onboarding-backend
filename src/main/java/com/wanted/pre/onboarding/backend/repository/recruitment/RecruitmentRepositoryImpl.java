package com.wanted.pre.onboarding.backend.repository.recruitment;

import static com.wanted.pre.onboarding.backend.entity.recruitment.QRecruitment.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.pre.onboarding.backend.dto.recruitment.RecruitmentResponse;
import com.wanted.pre.onboarding.backend.entity.recruitment.QRecruitment;
import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentCustomRepository{

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<RecruitmentResponse> findRecruitmentsByKeyword(String keyword) {
		QRecruitment qRecruitment = recruitment;

		BooleanExpression predicate = recruitment.company.name.containsIgnoreCase(keyword)
			.or(recruitment.company.country.containsIgnoreCase(keyword))
			.or(recruitment.company.city.containsIgnoreCase(keyword))
			.or(recruitment.position.containsIgnoreCase(keyword))
			.or(recruitment.skill.containsIgnoreCase(keyword));

		List<Recruitment> recruitments = jpaQueryFactory
			.selectFrom(recruitment)
			.where(predicate)
			.fetch();

		return recruitments.stream()
			.map(recruitmentDto -> new RecruitmentResponse(recruitmentDto))
			.collect(Collectors.toList());
	}
}
