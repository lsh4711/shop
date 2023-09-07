package com.shop.member.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
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
import com.shop.domain.member.dto.MemberDto;
import com.shop.global.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private final String baseUrl = "/api/members";

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
                .andExpect(header().string("Location",
                    is(String.format("%s/%d", baseUrl, 11))))
                .andDo(document("회원 등록",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "회원 등록: 일반 사용자 권한으로 등록됩니다. 중복되는 아이디거나 올바른 형식이 아닌 경우는 허용되지 않습니다. 주소는 옵션입니다.")
                            .build())));
    }

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
                                "로그인: JWT Access 토큰 발급 후 Authorization 헤더로 전송됩니다.")
                            .build())));
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteMemberTest() throws Exception {

        ResultActions actions = mockMvc.perform(delete(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AuthUtils.getTestToken()));

        actions
                .andExpect(status().isNoContent())
                .andDo(document("회원 삭제",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(builder()
                            .tag("Member")
                            .description(
                                "회원 삭제: 토큰의 정보와 일치하는 회원을 삭제할 수 있습니다.")
                            .build())));
    }

}
