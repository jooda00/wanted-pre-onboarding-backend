package com.wanted.pre.onboarding.backend.repository.recruitment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wanted.pre.onboarding.backend.entity.recruitment.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, RecruitmentCustomRepository {
	@Query("select r from Recruitment r join fetch r.company c")
	List<Recruitment> findAllRecruitmentWithCompany();

	@Query("select r from Recruitment r join fetch r.company c left join fetch c.recruitments where r.id = :id")
	Recruitment findRecruitmentByIdWithCompany(Long id);
}
