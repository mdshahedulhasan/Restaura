package com.restaura.apigateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;


@Component
public class JWTUtils {


    @Value("${security.jwt.secret-key}")
    private static String secreteString ;


    @Value("${security.jwt.expiration-time}")
    private  static  long EXPIRATION_TIME ;


    private Key getSigningKey() {
        byte []  keyBytes = Decoders.BASE64.decode(secreteString);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
    }


}
