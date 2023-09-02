package com.shop.global.auth.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shop.global.auth.JwtAuthenticationToken;
import com.shop.global.auth.jwt.JwtTokenizer;
import com.shop.global.exception.ExceptionCode;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain)
        throws ServletException, IOException {

        // ### logger
        try {
            Map<String, Object> claims = verifyJws(request);
            saveAuthenticationToContext(claims);
        } catch (SignatureException se) {
            request.setAttribute("exceptionCode", ExceptionCode.SIGNATURE_INVALID);
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exceptionCode", ExceptionCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            request.setAttribute("exceptionCode", ExceptionCode.UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer")) {
            return true;
        }
        return false;
    }

    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        Map<String, Object> claims = jwtTokenizer.extractClaims(jws).getBody();

        return claims;
    }

    private void saveAuthenticationToContext(Map<String, Object> claims) {
        long id = Long.parseLong(claims.get("id").toString());
        String username = (String)claims.get("username");
        List<String> roles = (List)claims.get("roles");
        List<GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(roles.toArray(new String[0]));

        Authentication authentication = new JwtAuthenticationToken(id, username, authorities);

        // Authentication authentication = new UsernamePasswordAuthenticationToken(username, id,
        // authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
