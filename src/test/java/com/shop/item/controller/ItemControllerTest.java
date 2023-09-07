package com.shop.item.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import com.shop.domain.item.dto.ItemDto;
import com.shop.global.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final String baseUrl = "/api/items";

    @Test
    @DisplayName("상품 등록")
    void createItemTest() throws Exception {
        ItemDto.Post postDto = new ItemDto.Post();
        postDto.setPrice(8500);
        postDto.setMartId(1L);
        postDto.setProductId(10L);

        ResultActions actions = mockMvc.perform(post(baseUrl + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(postDto)));

        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location",
                    is(String.format("%s/%d", baseUrl, 10))))
                .andDo(document("상품 등록",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Item")
                            .description(
                                "상품 등록: 서버에 정보가 등록되어 있는 제품(Product)을 본인 마트에 상품(Item)으로 등록할 수 있습니다. 같은 제품을 중복해서 등록할 수 없고 상품 등록 시 가격 기록 테이블에 가격이 기록됩니다. 새로운 제품을 팔기 위해선 제품 정보를 먼저 등록 해야합니다.")
                            .build())));
    }

    @Test
    @DisplayName("상품 가격 수정")
    void patchItemTest() throws Exception {
        ItemDto.Patch patchDto = new ItemDto.Patch();
        patchDto.setMartId(1L);
        patchDto.setPrice(4500);

        ResultActions actions = mockMvc.perform(patch(baseUrl + "/{itemId}/edit", 7)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(patchDto)));

        actions
                .andExpect(status().isOk())
                .andDo(document("상품 가격 수정",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Item")
                            .description(
                                "상품 가격 수정: 본인 마트의 상품 가격을 수정할 수 있습니다. 가격 수정 시 가격 기록 테이블에 기록됩니다.")
                            .build())));
    }

    @Test
    @DisplayName("상품 목록 조회")
    void getItemsTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .param("martId", "1"));

        actions
                .andExpect(status().isOk())
                .andDo(document("상품 목록 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Item")
                            .description(
                                "상품 목록 조회: 특정 마트의 상품 목록을 조회할 수 있습니다. 비회원도 이용 가능합니다.")
                            .build())));
    }

    @Test
    @DisplayName("상품 정보 조회")
    void getItemTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl + "/{itemId}", 7)
                .contentType(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andDo(document("상품 정보 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Item")
                            .description(
                                "상품 정보 조회: 특정 상품의 정보를 조회할 수 있습니다. 비회원도 이용 가능합니다.")
                            .build())));
    }

    @Test
    @DisplayName("상품 삭제")
    void deleteItemTest() throws Exception {
        ResultActions actions = mockMvc.perform(delete(baseUrl + "/{itemId}", 7)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isNoContent())
                .andDo(document("상품 삭제",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Item")
                            .description(
                                "상품 삭제: 본인 마트의 상품을 삭제할 수 있습니다.")
                            .build())));
    }

    @Test
    @DisplayName("가격 기록 조회")
    void getPriceHistoryTest() throws Exception {
        LocalDateTime startOfTomorrow = LocalDate.now().plusDays(1).atStartOfDay();

        ResultActions actions = mockMvc
                .perform(get(baseUrl + "/{itemId}/price/histories/search", 7)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", startOfTomorrow.toString()));

        actions
                .andExpect(status().isOk())
                .andDo(document("가격 기록 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Item")
                            .description(
                                "가격 기록 조회: 특정 시점의 상품 가격을 조회할 수 있습니다.")
                            .build())));
    }

    @Test
    @DisplayName("최근 가격 차트 조회")
    void getPriceHistoriesTest() throws Exception {
        LocalDateTime startOfTomorrow = LocalDate.now().plusDays(1).atStartOfDay();

        ResultActions actions = mockMvc
                .perform(get(baseUrl + "/{itemId}/price/histories", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("days", "90"));

        actions
                .andExpect(status().isOk())
                .andDo(document("최근 가격 차트 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Item")
                            .description(
                                "최근 가격 차트 조회: 최대 90일 전부터 현재까지의 가격 정보를 조회할 수 있습니다. 하루 단위로 구분되며 각 날짜의 가격은 그날 가장 마지막으로 변경된 가격입니다.")
                            .build())));
    }
}
