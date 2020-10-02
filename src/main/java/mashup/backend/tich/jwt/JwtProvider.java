package mashup.backend.tich.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    private long tokenValidTime = 365 * 24 * 60 * 60 * 1000L; // 365일 토큰 유효
    public static final String HEADER_NAME = "TICH-TOKEN";

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk){
        Claims claims = Jwts.claims().setSubject(userPk);
        Date date = new Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(date).setExpiration(new Date(date.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAuthentication(String token){
        // 토큰에 대응되는 유저를 찾고, userDetails 를 생성하고, 권한과 유저정보를 담아 Authentication 반환
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserPk(token));
        // Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header 에서 token 파싱
    public String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(HEADER_NAME);
    }

    // Jwt 토큰 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e) {
            return false;
        }
    }

//    private SecretKey getSigningKey() {
//        return Keys.hmacShaKeyFor(secretKey.getBytes());
//    }
}