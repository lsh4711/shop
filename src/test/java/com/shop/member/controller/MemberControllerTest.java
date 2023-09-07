package com.shop.member.controller;

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

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.shop.domain.coupon.dto.CouponDto;
import com.shop.domain.member.dto.CartItemDto;
import com.shop.domain.member.dto.MemberDto;
import com.shop.global.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final String baseUrl = "/api/members";

    @Order(1)
    @Test
    @DisplayName("회원 등록")
    void createMemberTest() throws Exception {
        MemberDto.Post postDto = new MemberDto.Post();
        postDto.setUsername("member11");
        postDto.setPassword("member1234!");
        postDto.setAddress("무슨동 무슨길 1");

        // Member member = new Member();
        // member.setMemberId(11L);

        // given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        ResultActions actions = mockMvc.perform(post(baseUrl + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(postDto)));

        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(String.format("%s/%d", baseUrl, 11))))
                .andDo(document("회원 등록",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[공개] 회원 등록: 일반 사용자 권한으로 등록됩니다. 중복되는 아이디거나 올바른 형식이 아닌 경우는 허용되지 않습니다.")
                            .build())));
    }

    @Order(1)
    @Test
    @DisplayName("로그인")
    void loginTest() throws Exception {
        MemberDto.Post postDto = new MemberDto.Post();
        postDto.setUsername("member1");
        postDto.setPassword("member1");

        ResultActions actions = mockMvc.perform(post(baseUrl + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(postDto)));

        actions
                .andExpect(status().isOk())
                .andDo(document("로그인",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[공개] 로그인: JWT Access 토큰 발급 후 Authorization 헤더로 전송됩니다.")
                            .build())));
    }

    @Order(99)
    @Test
    @DisplayName("회원 삭제")
    void deleteMemberTest() throws Exception {
        ResultActions actions = mockMvc.perform(delete(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getDeleteToken()));

        actions
                .andExpect(status().isNoContent())
                .andDo(document("회원 삭제",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[사용자] 회원 삭제: 토큰의 정보와 일치하는 회원을 삭제할 수 있습니다.")
                            .build())));
    }

    @Order(1)
    @Test
    @DisplayName("장바구니 추가")
    void addCartItemTest() throws Exception {
        CartItemDto.Post postDto = new CartItemDto.Post();
        postDto.setAmount(25);
        postDto.setItemId(5L);

        ResultActions actions = mockMvc.perform(post(baseUrl + "/cart/items/add")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(postDto)));

        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location",
                    is(String.format("%s/%d/%s/%d", baseUrl, 1L, "cart/items", 185))))
                .andDo(document("장바구니 추가",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[사용자] 장바구니 추가: 구매할 상품을 장바구니에 추가할 수 있습니다. 이미 추가된 상품인 경우 수량이 증가합니다.")
                            .build())));
    }

    @Order(1)
    @Test
    @DisplayName("장바구니 상품 수량 변경")
    void patchCartItemTest() throws Exception {
        CartItemDto.Patch patchDto = new CartItemDto.Patch();
        patchDto.setAmount(77);

        ResultActions actions = mockMvc.perform(patch(baseUrl + "/cart/items/{cartItemId}/edit", 186)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(patchDto)));

        actions
                .andExpect(status().isOk())
                .andDo(document("장바구니 상품 수량 변경",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[사용자] 장바구니 상품 수량 변경: 장바구니에 저장된 상품의 수량을 변경할 수 있습니다.")
                            .build())));
    }

    @Order(1)
    @Test
    @DisplayName("장바구니 조회")
    void getCartItemsTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(baseUrl + "/cart/items")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isOk())
                .andDo(document("장바구니 조회",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[사용자] 장바구니 조회: 장바구니에 저장된 상품 목록과 결제 금액을 확인할 수 있습니다.")
                            .build())));
    }

    @Order(97)
    @Test
    @DisplayName("장바구니 상품 삭제")
    void deleteCartItemTest() throws Exception {
        ResultActions actions = mockMvc.perform(delete(baseUrl + "/cart/items/{cartItemId}", 188)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isNoContent())
                .andDo(document("장바구니 상품 삭제",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[사용자] 장바구니 상품 삭제: 장바구니의 상품을 삭제할 수 있습니다.")
                            .build())));
    }

    @Order(98)
    @Test
    @DisplayName("장바구니 비우기")
    void deleteCartItemsTest() throws Exception {
        ResultActions actions = mockMvc.perform(delete(baseUrl + "/cart/items")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getDeleteToken()));

        actions
                .andExpect(status().isNoContent())
                .andDo(document("장바구니 비우기",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[사용자] 장바구니 비우기: 장바구니의 모든 상품을 삭제할 수 있습니다.")
                            .build())));
    }

    @Order(1)
    @Test
    @DisplayName("장바구니에 쿠폰 적용")
    void discountCartItemTest() throws Exception {
        CouponDto couponDto = new CouponDto();
        couponDto.setCouponIds(List.of(1L, 4L, 5L));

        ResultActions actions = mockMvc.perform(patch(baseUrl + "/cart/items/discount")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken())
                .content(gson.toJson(couponDto)));

        actions
                .andExpect(status().isOk())
                .andDo(document("장바구니에 쿠폰 적용",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "[사용자] 장바구니에 쿠폰 적용: 쿠폰 적용 후 가격을 조회할 수 있습니다. 쿠폰 적용 전, 후의 가격을 모두 확인 가능합니다.")
                            .build())));
    }

}
