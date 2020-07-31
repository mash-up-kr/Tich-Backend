package mashup.backend.myeonvely.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserDoseNotExistException extends BaseException {

    public UserDoseNotExistException(){
        this("User does not exist.");
    }

    public UserDoseNotExistException(String message){
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("[ UserNotFoundException]\n" + message)
                .build());
    }
}
