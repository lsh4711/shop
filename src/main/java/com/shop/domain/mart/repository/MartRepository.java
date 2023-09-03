package com.shop.domain.mart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.mart.entity.Mart;

public interface MartRepository extends JpaRepository<Mart, Long> {
    boolean existsByName(String name);

    List<Mart> findAllByMember_MemberId(long memberId);

    Optional<Mart> findByMartIdAndMember_MemberId(long martId, long memberId);

    long countByMember_MemberId(long memberId);
}
