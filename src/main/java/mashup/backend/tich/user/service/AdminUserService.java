package mashup.backend.tich.user.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.UserDoseNotExistException;
import mashup.backend.tich.jwt.JwtProvider;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.domain.UserRepository;
import mashup.backend.tich.user.dto.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminUserService {

    private final UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public List<SimpleUser> showUsers() {
        List<User> users = userRepository.findAll();

        return SimpleUser.listOf(users);
    }

    public boolean adminByToken(String token) {
        User user = userRepository.findById(Long.valueOf(jwtProvider.getUserPk(token))).orElseThrow(UserDoseNotExistException::new);

        if (user != null && user.getRoleKey().equals("ROLE_ADMIN")) return true;
        return false;
    }
}
