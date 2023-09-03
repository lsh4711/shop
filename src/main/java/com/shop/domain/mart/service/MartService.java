package com.shop.domain.mart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.domain.mart.entity.Mart;
import com.shop.domain.mart.repository.MartRepository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;
import com.shop.global.utils.AuthUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MartService {
    private final MartRepository martRepository;

    public Mart createMart(Mart mart) {
        verifyName(mart.getName());
        verifyMartCount();

        Mart savedMart = martRepository.save(mart);

        return savedMart;
    }

    public List<Mart> findMarts() {
        long memberId = AuthUtils.getMemberId();

        return martRepository.findAllByMember_MemberId(memberId);
    }

    private void verifyName(String name) {
        boolean exists = martRepository.existsByName(name);

        if (exists) {
            throw new CustomException(ExceptionCode.MART_EXISTS);
        }

    }

    private void verifyMartCount() {
        long memberId = AuthUtils.getMemberId();
        long martCnt = martRepository.countByMember_MemberId(memberId);

        if (martCnt >= 5) {
            throw new CustomException(ExceptionCode.MART_COUNT_MAXIMUM);
        }

    }
}
