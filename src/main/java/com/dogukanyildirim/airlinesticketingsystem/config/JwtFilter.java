package com.dogukanyildirim.airlinesticketingsystem.config;

import com.dogukanyildirim.airlinesticketingsystem.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws IOException, ServletException {
        String header = httpServletRequest.getHeader("Authorization");
        if (Objects.nonNull(header) && header.startsWith("Bearer ")) {
            String authToken = header.substring(7);
            boolean isValid = !jwtUtil.isTokenExpired(authToken);
            try {
                if (isValid && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(jwtUtil.createAuthenticationFromToken(authToken));
                    SecurityContextHolder.setContext(securityContext);
                }
            } catch (Exception ex) {
                LOGGER.error("Authentication başarısız oldu!", ex);
            }
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

}