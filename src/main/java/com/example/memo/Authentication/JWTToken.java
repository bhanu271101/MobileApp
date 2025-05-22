package com.example.memo.Authentication;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value; 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTToken {

    @Value("${jwt.secret}")
    private String SECRET;

    private final long EXPIRATION_TIME = 1000 * 60 *60*24*7;

    private Key getSigningKey() {
    byte[] keys=Base64.getDecoder().decode(SECRET);
    return Keys.hmacShaKeyFor(keys);
    }

    
    public String generateToken(String userId)
    {
        
        return Jwts.builder()
        .setSubject(userId)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256,getSigningKey())
        .compact();
    }

    public String extractUserId(String token)
    {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token)
    {
        
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token)
    {
        try{
            getClaims(token);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
