package com.shop.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.member.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);

    Member findByUsername(String username);
}
