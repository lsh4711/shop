package com.shop.order.controller;

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

import java.util.List;

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
import com.shop.domain.order.dto.OrderDto;
import com.shop.domain.order.entity.Order.Payment;
import com.shop.global.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final String baseUrl = "/api/orders";

    @Test
    @DisplayName("장바구니 결제")
    void createOrderTest() throws Exception {
        OrderDto.Post postDto = new OrderDto.Post();
        postDto.setPayment(Payment.CARD);
        postDto.setCouponIds(List.of(1L, 4L, 5L));

        ResultActions actions = mockMvc.perform(post(baseUrl + "/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(postDto)));

        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(String.format("%s/%d", baseUrl, 21))))
                .andDo(document("장바구니 결제",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Order")
                            .description(
                                "장바구니 결제: 장바구니에 저장된 상품을 결제 및 주문할 수 있습니다. 쿠폰은 최대 5개 까지만 사용 가능하며 결제 후 사용된 쿠폰은 삭제되고 장바구니는 비워집니다. 상품을 판매한 마트에 정산금이 추가됩니다.")
                            .build())));
    }

    @Test
    @DisplayName("주문 내역 조회")
    void getOrdersTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isOk())
                .andDo(document("주문 내역 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Order")
                            .description(
                                "주문 내역 조회: 지난 주문 내역과 결제 정보를 확인할 수 있습니다.")
                            .build())));
    }

    @Test
    @DisplayName("주문 상세 정보 조회")
    void getOrderTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl + "/{orderId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isOk())
                .andDo(document("주문 상세 정보 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Order")
                            .description(
                                "주문 상세 정보 조회: 해당 주문의 상품 정보 등 주문 상세 정보를 조회할 수 있습니다.")
                            .build())));
    }
}
