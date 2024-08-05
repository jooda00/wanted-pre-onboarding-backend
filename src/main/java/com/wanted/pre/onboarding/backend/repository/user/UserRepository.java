package com.wanted.pre.onboarding.backend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.pre.onboarding.backend.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
