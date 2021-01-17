package com.dogukanyildirim.airlinesticketingsystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    @Value("${system.token.expiration}")
    long tokenExpiration;
    @Value("${system.token.secret}")
    String tokenSecret;

    public String generateJwtToken(String user, Object role) {
        return Jwts.builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes())
                .claim("userRole", role)
                .compact();
    }

    public Boolean isTokenExpired(String token) {
        return decodeToken(token).getExpiration().before(Date.from(Instant.now()));
    }

    private Claims decodeToken(String token) {
        return Jwts.parser().setSigningKey(tokenSecret.getBytes()).parseClaimsJws(token).getBody();
    }

    public Authentication createAuthenticationFromToken(String token) {
        final Claims claims = decodeToken(token);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(claims.get("userRole").toString()));
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "N/A", authorities);
    }
}
