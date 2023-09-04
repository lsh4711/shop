package com.shop.domain.mart.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.mart.dto.MartDto;
import com.shop.domain.mart.dto.MartResponse;
import com.shop.domain.mart.entity.Mart;
import com.shop.domain.mart.mapper.MartMapper;
import com.shop.domain.mart.service.MartService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/marts")
public class MartController {
    private final MartService martService;
    private final MartMapper martMapper;

    @PostMapping("/write")
    public ResponseEntity postMart(@Valid @RequestBody MartDto.Post postDto) {
        Mart mart = martMapper.postDtoToMart(postDto);
        Mart savedMart = martService.createMart(mart);

        URI location = UriCreator.createUri("/marts", savedMart.getMartId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/public")
    public ResponseEntity getPublicMarts() {
        List<Mart> mart = martService.findMarts();
        List<MartResponse> martResponses = martMapper.martsToMartResponse(mart);

        return ResponseEntity.ok(martResponses);
    }

    @GetMapping("/private")
    public ResponseEntity getMartsOfSeller() {
        List<Mart> mart = martService.findMartsOfSeller();
        List<MartResponse> martResponses = martMapper.martsToMartResponse(mart);

        return ResponseEntity.ok(martResponses);
    }

}
