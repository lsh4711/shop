package com.shop.domain.mart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.mart.entity.Mart;

public interface MartRepository extends JpaRepository<Mart, Long> {
    boolean existsByName(String name);

    List<Mart> findAllByMember_MemberId(long memberId);

    long countByMember_MemberId(long memberId);
}
