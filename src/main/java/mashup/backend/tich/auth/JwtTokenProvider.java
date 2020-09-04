package mashup.backend.tich.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import mashup.backend.tich.auth.dto.AccessToken;
import mashup.backend.tich.exception.InvalidTokendException;
import mashup.backend.tich.user.domain.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {
    private JwtTokenProvider(){}

    private static class InnerInstance{
        private static final JwtTokenProvider instance = new JwtTokenProvider();
    }

    public static JwtTokenProvider getInstance(){
        return InnerInstance.instance;
    }

    public AccessToken generateAccessKey(User user, String secretKey){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId().toString());

        return generateAccessKey(claims, secretKey);
    }

    public AccessToken generateAccessKey(Map<String, Object> claims, String secretKey){
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject("AccessToken")
                .setIssuedAt(now)
                .signWith(getSigningKey(secretKey))
                .compact();

        return new AccessToken(token);
    }

    public Long getUserIdByClaims(Claims claims, String subject) {
        if(!(claims.getSubject().equals(subject)))
            throw new InvalidTokendException("token subject do not match.");

        return Long.parseLong(claims.get("userId").toString());
    }

    public Claims decodingToken(String token, String secretKey){
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey(secretKey))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException e){
            throw new InvalidTokendException("Token is invalid.");
        }
    }

    private SecretKey getSigningKey(String secretKey){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
