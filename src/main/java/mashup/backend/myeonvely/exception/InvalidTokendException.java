package mashup.backend.myeonvely.exception;

import mashup.backend.myeonvely.exception.BaseException;
import mashup.backend.myeonvely.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidTokendException extends BaseException {

    public InvalidTokendException(String message){
        super(ErrorCode.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("[ InvalidTokenException ]\n" + message)
                .build());
    }
}
