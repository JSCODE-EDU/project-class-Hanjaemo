package jscode.jscodestudy.config.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private String secretKey;
    private Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String createToken(Long id, String email, long expTime) {
        if (expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야 합니다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        key = new SecretKeySpec(secretByteKey, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims();
        claims.put("id", id);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /*
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
    */
}