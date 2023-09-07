package com.shop.coupon.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.shop.global.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CouponControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final String baseUrl = "/api/coupons";

    @Test
    @DisplayName("쿠폰 목록 조회")
    void getCouponsTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isOk())
                .andDo(document("쿠폰 목록 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Coupon")
                            .description(
                                "쿠폰 목록 조회: 가지고 있는 쿠폰 목록을 조회할 수 있습니다.")
                            .build())));
    }
}
