package com.shop.mart.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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

import com.epages.restdocs.apispec.SimpleType;
import com.google.gson.Gson;
import com.shop.domain.mart.dto.MartDto;
import com.shop.global.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class MartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final String baseUrl = "/api/marts";

    @Test
    @DisplayName("마트 등록")
    void createMartTest() throws Exception {
        MartDto.Post postDto = new MartDto.Post();
        postDto.setName("살림 마트");
        postDto.setAddress("무슨동 무슨길 1");

        ResultActions actions = mockMvc.perform(post(baseUrl + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(postDto)));

        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location",
                    is(String.format("%s/%d", baseUrl, 21))))
                .andDo(document("마트 등록",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Mart")
                            .description(
                                "[마트] 마트 등록: 판매자(SELLER) 권한을 가진 회원만 마트 등록을 할 수 있습니다. 중복되는 마트 이름은 허용되지 않습니다. 개인 당 최대 5개의 마트를 소유할 수 있습니다.")
                            .build())));
    }

    @Test
    @DisplayName("등록된 마트 목록 조회")
    void getPublicMartsTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl + "/public")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "1")
                .param("size", "5"));

        actions
                .andExpect(status().isOk())
                .andDo(document("등록된 마트 목록 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Mart")
                            .description(
                                "[공개] 등록된 마트 목록 조회: 마트 목록을 조회할 수 있습니다. 페이징이 적용되어 있고 비회원도 이용 가능합니다.")
                            .requestParameters(
                                parameterWithName("page").description("조회할 페이지의 번호입니다.")
                                        .type(SimpleType.INTEGER),
                                parameterWithName("size").description("한 페이지에 조회할 마트의 수입니다.")
                                        .type(SimpleType.INTEGER))
                            .build())));
    }

    @Test
    @DisplayName("본인 소유의 마트 목록 조회")
    void getPrivateMartsTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl + "/private")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isOk())
                .andDo(document("본인 소유의 마트 목록 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Mart")
                            .description(
                                "[마트] 본인 소유의 마트 목록 조회: 판매자 회원이 본인 소유의 마트 목록을 조회할 수 있습니다. 현재 정산금도 확인 가능합니다.")
                            .build())));
    }
}
