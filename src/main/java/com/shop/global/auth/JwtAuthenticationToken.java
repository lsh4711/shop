package com.shop.global.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private long memberId;
    private String username;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public JwtAuthenticationToken(long memberId, String username,
            Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.memberId = memberId;
        this.username = username;
        super.setAuthenticated(true);
    }

    @Override
    public Long getCredentials() {
        return memberId;
    }

    @Override
    public String getPrincipal() {
        return username;
    }
}
