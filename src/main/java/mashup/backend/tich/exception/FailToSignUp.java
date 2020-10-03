package mashup.backend.tich.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FailToSignUp extends BaseException {

    public FailToSignUp(String message){
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("[ Fail to sign-up ]\n" + message)
                .build());
    }
}