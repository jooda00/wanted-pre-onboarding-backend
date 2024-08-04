package com.wanted.pre.onboarding.backend.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.pre.onboarding.backend.entity.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
