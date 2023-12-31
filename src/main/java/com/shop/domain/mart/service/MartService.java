package com.shop.domain.mart.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        return martRepository.save(mart);
    }

    public void addMoney(Mart mart, long money) {
        mart.addMoney(money);
        martRepository.save(mart);
    }

    public Mart findMart(long martId) {
        return martRepository.findById(martId)
                .orElseThrow(() -> new CustomException(ExceptionCode.MART_NOT_FOUND));
    }

    public Page<Mart> findMarts(int page, int size) {
        Sort sort = Sort.by("martId").descending();

        return martRepository.findAll(PageRequest.of(page - 1, size, sort));
    }

    public List<Mart> findMartsOfSeller() {
        return martRepository.findAllByMember_MemberId(AuthUtils.getMemberId());
    }

    public Mart findByMartIdAndAuthId(long martId) {
        return martRepository.findByMartIdAndMember_MemberId(martId, AuthUtils.getMemberId())
                .orElseThrow(() -> new CustomException(ExceptionCode.MART_NOT_FOUND));
    }

    public void verifyName(String name) {
        boolean exists = martRepository.existsByName(name);

        if (exists) {
            throw new CustomException(ExceptionCode.MART_EXISTS);
        }

    }

    public void verifyMartCount() {
        long martCnt = martRepository.countByMember_MemberId(AuthUtils.getMemberId());

        if (martCnt >= 5) {
            throw new CustomException(ExceptionCode.MART_COUNT_MAXIMUM);
        }

    }
}
