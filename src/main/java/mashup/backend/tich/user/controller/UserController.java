package mashup.backend.tich.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.backend.tich.jwt.JwtProvider;
import mashup.backend.tich.user.dto.SignInResponseDto;
import mashup.backend.tich.user.dto.SignUpRequestDto;
import mashup.backend.tich.user.dto.SignUpResponseDto;
import mashup.backend.tich.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

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

    @ApiOperation("로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestHeader("TICH-TOKEN") String token){
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
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

}