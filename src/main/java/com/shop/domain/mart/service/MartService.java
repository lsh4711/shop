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
        return martRepository.save(mart); // Mapper에서 검증
    }

    public List<Mart> findMarts() {
        long memberId = AuthUtils.getMemberId();

        return martRepository.findAllByMember_MemberId(memberId);
    }

    public Mart findByMartIdAndAuthId(long martId) {
        Mart foundMart = martRepository.findByMartIdAndMember_MemberId(martId,
            AuthUtils.getMemberId());

        if (foundMart == null) {
            throw new CustomException(ExceptionCode.MART_NOT_FOUND);
        }

        return foundMart;
    }

    public void verifyName(String name) {
        boolean exists = martRepository.existsByName(name);

        if (exists) {
            throw new CustomException(ExceptionCode.MART_EXISTS);
        }

    }

    public void verifyMartCount() {
        long memberId = AuthUtils.getMemberId();
        long martCnt = martRepository.countByMember_MemberId(memberId);

        if (martCnt >= 5) {
            throw new CustomException(ExceptionCode.MART_COUNT_MAXIMUM);
        }

    }
}
