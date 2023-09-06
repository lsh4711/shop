package com.shop.global.auth.details;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.shop.domain.member.entity.Member;

import lombok.Getter;

@Getter
public class MemberDetails extends Member implements UserDetails {
    public MemberDetails(Member member) {
        super(member.getMemberId(),
            member.getUsername(),
            member.getPassword(),
            member.getAddress(),
            member.getRole(),
            null,
            null,
            null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] roles = getRole().getRoles();

        return AuthorityUtils.createAuthorityList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
