package mashup.backend.tich.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.NoAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    // Request로 들어오는 Jwt Token의 유효성을 검증
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(((HttpServletRequest)request).getRequestURL());
        String token = jwtProvider.getTokenFromHeader((HttpServletRequest)request);
        if(token != null && jwtProvider.validateToken(token)){
            // 토큰을 가지고 있고, 유효할 때, Authentication 객체를 얻어서 통과시켜줌
            Authentication authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
//        else if(token != null && !jwtProvider.validateTokenIssuedDate(token)){
//            // 가지고 있는 토큰이 expired 됐을 때
//            // refresh token을 달라는 response 보내기
//        }
        else {
            // 유효하지 않은 토큰일 때 오류메시지 보내기
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("utf8");
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("토큰이 올바르지 않습니다."));
            httpServletResponse.getWriter().write(result);
            return;
        }
        chain.doFilter(request, response);
    }
}