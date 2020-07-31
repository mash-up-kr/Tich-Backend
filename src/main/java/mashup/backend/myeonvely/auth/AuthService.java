package mashup.backend.myeonvely.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import mashup.backend.myeonvely.exception.UserDoseNotExistException;
import mashup.backend.myeonvely.user.domain.User;
import mashup.backend.myeonvely.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final UserRepository userRepository;

    public User getUserByAccessToken(String accessToken){
        Claims claims = JwtTokenProvider.getInstance().decodingToken(accessToken, secretKey);
        Long userId = JwtTokenProvider.getInstance().getUserIdByClaims(claims, "AccessToken");

        return userRepository.findById(userId).orElseThrow(UserDoseNotExistException::new);
    }
}
