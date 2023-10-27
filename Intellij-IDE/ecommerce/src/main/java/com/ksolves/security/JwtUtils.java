package com.ksolves.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    public String getUserNameFromJwtToken(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(jwtToken).getBody().getSubject();
    }

    public String generateJwtTokenByUsername(String username){
        Map claims = new HashMap<>();
        return createToken(username, claims);
    }

    public String createToken(String username, Map claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isValidToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            System.out.println("Error in Token " + e.getMessage());
        }
        return false;
    }

    public Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode("SGVsbG8gQnJvdGhlci4gSG93IGFyZSB5b3UgZG9pbmc/");
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
