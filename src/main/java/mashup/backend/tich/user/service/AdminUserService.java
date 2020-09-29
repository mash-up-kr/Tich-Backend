package mashup.backend.tich.user.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.domain.UserRepository;
import mashup.backend.tich.user.dto.SimpleUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminUserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<SimpleUser> showUsers() {
        List<User> users = userRepository.findAll();

        return SimpleUser.listOf(users);
    }
}
