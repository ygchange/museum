package com.museum.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtUtil {


    private static final String SIGNING_KEY = "MUSEUM_YG_KEY";


    public static Claims getClaim(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }

    public static String setClaim(String subject,boolean expired,int expiration){
        if (expired){
            return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
        }else {
            return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
        }
    }
}
