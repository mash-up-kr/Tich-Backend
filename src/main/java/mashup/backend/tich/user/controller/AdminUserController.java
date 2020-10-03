package mashup.backend.tich.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.user.domain.Role;
import mashup.backend.tich.user.domain.User;
import mashup.backend.tich.user.domain.UserRepository;
import mashup.backend.tich.user.dto.SignUpRequestDto;
import mashup.backend.tich.user.dto.SimpleUser;
import mashup.backend.tich.user.service.AdminUserService;
import mashup.backend.tich.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final UserRepository userRepository; /* 삭제 예정(관리자 확인용) */
    private final UserService userService;

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequestDto));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @ApiOperation("사용자 목록 조회")
    @GetMapping
    public ResponseEntity<List<SimpleUser>> showUsers(@RequestHeader String accessToken) {
        // 임시 코드 : 추후 수정
        User user = makeTempUser();
        // ToDo : user check (accessToken)

        List<SimpleUser> simpleUsers = adminUserService.showUsers();

        return ResponseEntity.status(HttpStatus.OK).body(simpleUsers);
    }

    /* 임시 코드 : 삭제 예정 */
    private User makeTempUser() {
        User user;
        try {
            user = userRepository.findByEmail("admin")
                    .orElseThrow(() -> new NoResultException());
        } catch (NoResultException e) {
            user = userRepository.save(User.builder()
                    .name("관리자")
                    .email("admin")
                    .picture("temp")
                    .role(Role.ADMIN)
                    .build());
        }
        return user;
    }
}
