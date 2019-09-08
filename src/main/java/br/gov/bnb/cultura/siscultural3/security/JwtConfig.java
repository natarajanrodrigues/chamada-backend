package br.gov.bnb.cultura.siscultural3.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
//    @Value("${security.jwt.expiration:#{1*20}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;

    public String getUri() {
        return Uri;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

    public String extractUsername(String header) {

        String token = header.replace(getPrefix(), "");

        Claims claims = Jwts.parser()
                .setSigningKey(getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();



    }
}