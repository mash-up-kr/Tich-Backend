package mashup.backend.tich.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.exception.DuplicateException;
import mashup.backend.tich.exception.FailToSignUp;
import mashup.backend.tich.exception.InvalidTokendException;
import mashup.backend.tich.user.dto.SignUpRequestDto;
import mashup.backend.tich.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequestDto));
        } catch (DuplicateException e) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.loginWithoutToken(signUpRequestDto.getEmail()));
        } catch (Exception e) {
            throw new FailToSignUp(e.toString());
        }
    }

    @ApiOperation("로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestHeader("TICH-TOKEN") String token) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.loginByToken(token));
            /*
            if(token != null){
                return ResponseEntity.status(HttpStatus.OK).body(userService.loginByToken(token));
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(userService.loginByOauth(signInRequestDto));
            }
             */
        } catch (Exception e) {
            throw new InvalidTokendException(e.toString());
        }
    }

    @ApiOperation("탈퇴")
    @DeleteMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestHeader("TICH-TOKEN") String token) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.withdraw(token));
        } catch (Exception e) {
            throw new InvalidTokendException(e.toString());
        }
    }
}
