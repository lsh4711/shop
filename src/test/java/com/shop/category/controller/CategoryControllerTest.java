package com.shop.category.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
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

import com.google.gson.Gson;
import com.shop.domain.category.dto.CategoryDto;
import com.shop.global.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final String baseUrl = "/api/categories";

    @Test
    @DisplayName("카테고리 등록")
    void createCategoryTest() throws Exception {
        CategoryDto.Post postDto = new CategoryDto.Post();
        postDto.setName("견과류");

        ResultActions actions = mockMvc.perform(post(baseUrl + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(postDto)));

        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location",
                    is(String.format("%s/%d", baseUrl, 11))))
                .andDo(document("카테고리 등록",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Category")
                            .description(
                                "카테고리 등록: 제품을 등록할 때, 제품에 알맞는 카테고리를 찾을 수 없다면 카테고리를 등록할 수 있습니다. 중복 이름을 허용하지 않습니다.")
                            .build())));
    }

    @Test
    @DisplayName("카테고리 목록 조회")
    void getBrandTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isOk())
                .andDo(document("카테고리 목록 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Category")
                            .description(
                                "카테고리 목록 조회: 카테고리 목록을 조회할 수 있습니다.")
                            .build())));
    }
}
