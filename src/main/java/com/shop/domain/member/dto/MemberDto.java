package com.shop.domain.member.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

public class MemberDto {

    @Getter
    @Setter
    public static class Post {
        @NotEmpty(message = "아이디를 입력해주세요.")
        @Pattern(regexp = "^\\S{1,16}$", message = "아이디는 공백을 제외한 16자 이하의 문자열이어야 합니다.")
        private String username;

        @NotEmpty(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 공백을 제외한 8~16자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String password;
    }

    public static class Patch {

    }
}
