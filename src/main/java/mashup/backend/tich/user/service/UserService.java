package mashup.backend.tich.user.service;

import lombok.RequiredArgsConstructor;
import mashup.backend.tich.device.service.DeviceService;
import mashup.backend.tich.exception.DuplicateException;
import mashup.backend.tich.exception.InvalidTokendException;
import mashup.backend.tich.exception.UserDoseNotExistException;
import mashup.backend.tich.jwt.JwtProvider;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.domain.UserRepository;
import mashup.backend.tich.user.dto.SignInResponseDto;
import mashup.backend.tich.user.dto.SignUpRequestDto;
import mashup.backend.tich.user.dto.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DeviceService deviceService;

    @Autowired
    private JwtProvider jwtProvider;

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        if ("".equals(signUpRequestDto.getToken().trim())) {
            throw new InvalidTokendException("Empty token");
        }
        if (userRepository.findByEmail(signUpRequestDto.getEmail()).isPresent()) {
            throw new DuplicateException("this email is already exist.");
        }
        User user = userRepository.save(signUpRequestDto.toEntity());
        user.setDevices(deviceService.createDevice());

        String token = jwtProvider.createToken(String.valueOf(user.getId()));
        return new SignUpResponseDto(user.getId(), token, user.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(UserDoseNotExistException::new);
        if (!user.getId().equals(Long.valueOf(id))) {
            throw new UsernameNotFoundException("Invalid Request");
        }
        return org.springframework.security.core.userdetails.User.builder().username(id).password("").roles("").build();
    }

    public SignInResponseDto loginByToken(String token) {
        if (jwtProvider.validateToken(token)) {
            User user = userRepository.findById(Long.valueOf(jwtProvider.getUserPk(token))).orElseThrow(UserDoseNotExistException::new);
            return new SignInResponseDto(user.getId(), token, user.getName());
        } else {
            throw new InvalidTokendException("Expired Token");
        }
    }

    public SignInResponseDto loginWithoutToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserDoseNotExistException::new);
        String token = jwtProvider.createToken(String.valueOf(user.getId()));
        return new SignInResponseDto(user.getId(), token, user.getName());
    }

    public User findUserByToken(String token) {
        return userRepository.findById(Long.valueOf(jwtProvider.getUserPk(token))).orElseThrow(UserDoseNotExistException::new);
    }

    public String withdraw(String token) {
        if (jwtProvider.validateToken(token)) {
            User user = userRepository.findById(Long.valueOf(jwtProvider.getUserPk(token))).orElseThrow(UserDoseNotExistException::new);
            deviceService.deleteDevices(user);
            userRepository.delete(user);

            return "delete success";
        } else {
            throw new InvalidTokendException("Expired Token");
        }
    }

    /*
    public SignInResponseDto loginByOauth(SignInRequestDto signInRequestDto) throws Exception {
        User user = userRepository.findByEmail(signInRequestDto.)
        if(user == null){
            throw new Exception("need to sign up");
        }
        String token = jwtProvider.createToken(String.valueOf(user.getId()));
        return new SignInResponseDto(user.getId(), token, user.getName());
    }
    */
}
